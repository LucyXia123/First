package com.tsinghuadtv.www.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.tflx.enso.service.activity.json.ActivityParticipant;
import com.tflx.enso.service.activity.json.QualifiedParticipant;
import com.tflx.enso.service.activity.json.SchoolActivity;
import com.tflx.enso.service.bonus.json.BonusAccount;
import com.tflx.enso.service.bonus.json.BonusSettlement;
import com.tsinghuadtv.www.entity.ActivityMaterials;
import com.tsinghuadtv.www.entity.AppActivityMaterials;
import com.tsinghuadtv.www.entity.BigEvent;
import com.tsinghuadtv.www.entity.Comment;
import com.tsinghuadtv.www.entity.Student;
import com.tsinghuadtv.www.entity.UserInfo;
import com.tsinghuadtv.www.service.ISysUserService;
import com.tsinghuadtv.www.util.ConfigurationDir;
import com.tsinghuadtv.www.util.ResponseUtil;
import com.tsinghuadtv.www.util.encrypt.PasswordEncryption;
import com.tsinghuadtv.www.util.sms.SMS;
import com.tsinghuadtv.www.util.sms.Status;
import com.tsinghuadtv.www.util.Constants;
import com.tsinghuadtv.www.util.DefaultAvatarImage;
import com.tsinghuadtv.www.util.Score;
import com.tsinghuadtv.www.util.Sequnce4StudentUtil;
import com.tsinghuadtv.www.util.WebAppException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpStatusCodeException;

@Controller
public class SysUserController {
	
	private ResponseUtil responseUtil = new ResponseUtil();
	private Gson g = new Gson();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private DateTimeFormatter dateTimeFormatter0 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss.SSSSSS");
	private static final String ZHI_CHU = "SPENDING";
    private static final String SHOU_RU = "REVENUE";
    @Resource
	ISysUserService service;
	// mobile number regexp
	String phonePattern = "^(13[0-9]\\d{8}|15[0-35-9]\\d{8}|18[0-9]\\d{8}|17[0-9]\\d{8}|14[57]\\d{8})$";
    /**
	 * 注册提交 handler
	 * @param student
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody 
	@RequestMapping(value = "/student/save", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public String save(Student student,HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.printf("%s<---->%s<--->%s","new register customer info",request.getHeader("User-Agent"),student.toString());
		ResponseUtil responseUtil=null;
		try 
        {
			responseUtil=new ResponseUtil();
            //设置密码和usernumber
            String salt = PasswordEncryption.generateSalt();
            String ciphertext = PasswordEncryption.getEncryptedPassword(student.getPwd(), salt);
            student.setPwd(ciphertext);
            student.setSalt(salt);
            student.setUsernumber(Sequnce4StudentUtil.getMerchantSeq());
            System.out.println("1---->"+(!student.getType().equals("学生")));
            System.out.println("====>type:"+new String(student.getType().getBytes(),"gb18030"));
            if (!student.getType().equals("学生"))
                student.setRealname(String.format("%s%s",student.getType(),LocalDateTime.now().format(dateTimeFormatter0)));
			//注册用户
            System.out.println("2---->"+student.toString());
            service.save(student);
            System.out.printf("%s-->%s-->%s\n","register","register is success.",student.getUsername());
            //初始积分账户
            service.initBonusAccount(student.getUsernumber(), "积分");
            System.out.printf("%s-->%s\n","register","init bonus account.");
            BonusAccount bonusAccount = service.queryBonusAmountById(student.getUsernumber());
            //增加
            if (bonusAccount != null)
                service.changeBonus(bonusAccount.id,SHOU_RU , Score.SCORE_REGISTER, "注册用户", "注册用户增加5分");
			System.out.printf("%s-->%s\n","register","register is add +10");
            service.recordLogger(student.getUsernumber(), 
                                         getAddr(request),
                                         request.getHeader("User-Agent") ,
                                         request.getRequestURI(), 
                                         "注册增分",
                                         HttpStatus.OK+"", 
                                         "用户注册加10分");
            responseUtil.setResult(1);
			return g.toJson(responseUtil);
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
			responseUtil.setResult(0);
			responseUtil.setCause("注册失败");
			return g.toJson(responseUtil);
		}
	}
    
    /**
     * 新注册接口，主要改进了在提交时进行验证提示。
     * @param student
     * @param request
     * @return 
     */
    @ResponseBody 
	@RequestMapping(value = "/customerRegisterInterface", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String CustomerRegisterInterface(Student student,HttpServletRequest request)
    {
        try
        {
            System.out.printf("%s<---->%s<--->%s","new register customer info",request.getHeader("User-Agent"),student.toString());
            //验证用户名是否存在
            boolean isUsernameExsit = service.selectCustomerInfoByUsername(student.getUsername());
            boolean isTeleponeExsit = service.selectCustomerInfoByTelephone(student.getMobile());
            if (!isUsernameExsit && !isTeleponeExsit)
            {
                String salt = PasswordEncryption.generateSalt();
                String ciphertext = PasswordEncryption.getEncryptedPassword(student.getPwd(), salt);
                student.setPwd(ciphertext);
                student.setSalt(salt);
                student.setUsernumber(Sequnce4StudentUtil.getMerchantSeq());
                System.out.println("1---->"+(!student.getType().equals("学生")));
                if (!student.getType().equals("学生"))
                    student.setRealname(String.format("%s%s",student.getType(),LocalDateTime.now().format(dateTimeFormatter0))); 
                service.save(student);
                //初始积分账户
                service.initBonusAccount(student.getUsernumber(), "积分");
                System.out.printf("%s-->%s\n","register","init bonus account.");
                BonusAccount bonusAccount = service.queryBonusAmountById(student.getUsernumber());
                //增加
                if (bonusAccount != null)
                    service.changeBonus(bonusAccount.id,SHOU_RU , Score.SCORE_REGISTER, "注册用户", "注册用户增加5分");
                System.out.printf("%s-->%s\n","register","register is add +10");
                service.recordLogger(student.getUsernumber(), 
                                             getAddr(request),
                                             request.getHeader("User-Agent") ,
                                             request.getRequestURI(), 
                                             "注册增分",
                                             HttpStatus.OK+"",  
                                             "用户注册加10分");
                return toRegisterJSONString(200,"注册成功！");
                
            }
            else
            {
                if (isUsernameExsit && isTeleponeExsit)
                {
                    return toRegisterJSONString(406,"用户名和手机号已存在！");
                }
                else if (isUsernameExsit)
                {
                    return toRegisterJSONString(407,"用户名已存在！");
                }
                else if (isTeleponeExsit)
                {
                    return toRegisterJSONString(408,"手机号已存在！");
                }
                else
                {
                    return toRegisterJSONString(409,"发生其他错误！");
                }
            }
        }
        catch (Exception ex)
        {
           ex.printStackTrace();
           return toRegisterJSONString(500,"注册失败，请重新尝试 错误信息："+ex.getMessage());
        }
    }
	//这是什么接口？
	@ResponseBody 
	@RequestMapping(value = "/student/selectByUsername", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public String selectByUsername(Student student) throws UnsupportedEncodingException {
		ResponseUtil responseUtil=null;
		try {
            System.out.println("===========/student/selectByUsername==============");
			responseUtil=new ResponseUtil();
			service.save(student);
			responseUtil.setResult(1);
			return g.toJson(responseUtil);
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(0);
			responseUtil.setCause("注册失败");
			return g.toJson(responseUtil);
		}
	}

	/**
     * 通过Usernumber查询用户数据
     * @param userid 用户号
     * @return
     * @throws UnsupportedEncodingException 
     */
	@RequestMapping(value = "/student/getuser")
	public @ResponseBody Student getuser(@RequestParam String userid)throws WebAppException
    {
		Student student=null;
		try 
        {
			student = service.selectByUserid(userid);
			return student;
		} 
        catch (EmptyResultDataAccessException ex)
        {
            ex.printStackTrace();
            return new Student();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
	}
	// login handler (3次登陆失败 第4次尝试登录时候验证验证码)
	@RequestMapping(value ="/student/login", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public String check(
			@RequestParam String username, 
			@RequestParam String pwd, 
			@RequestParam String captcha,
			@RequestParam int count,
			HttpSession session,
            HttpServletRequest request) 
			throws UnsupportedEncodingException {	
		Student user;
		boolean result;
        System.out.printf("%s-->%s,%s,%s,%s\n","login",username,pwd,captcha,count);
		// 手机号登录		
		boolean isMatch = Pattern.matches(phonePattern, username);
		
		try {
            
			if (isMatch)
            {
				user = service.selectByPhone(username);
			}
            else 
            {
				user = service.selectByUserName(username);
			}		
            if (user == null)
            {
                responseUtil.setResult(0);
                responseUtil.setCause("该用户名或手机号不存在!");
                return g.toJson(responseUtil);
            }
			result = PasswordEncryption.authenticate(pwd,user.getPwd(), user.getSalt());
			if (result) 
            {
				responseUtil.setResult(1);	
				session.setAttribute("username", user.getUsername());
				session.setAttribute("userid", user.getUsernumber());
				session.setAttribute("school", user.getSchool());
//				System.out.println(user.getSchool());
				
				ServletContext ContextA =session.getServletContext();  
				ContextA.setAttribute("session", session );
				String area = user.getArea();
				session.setAttribute("area", area);
                session.setAttribute("type", user.getType());
                //登录次数
                int frequency = service.queryLoggerByConditional(user.getUsernumber(), request.getRequestURI(), HttpStatus.OK+"", LocalDate.now());
                System.out.printf("%s-->%s\n", "login count:",frequency+"");
                if (frequency == 0)
                {
                    //奖励2分
                    BonusAccount bonusAccount = service.queryBonusAmountById(user.getUsernumber());
                    if (bonusAccount != null)
                        service.changeBonus(bonusAccount.id,SHOU_RU , Score.SCORE_LOGIN, "用户登录", "用户登录增加2分");
//                    System.out.printf("%s%d-->%s\n", "login account id:",bonusAccount.id,"login is add +2");
                    //加日志
                    service.recordLogger(user.getUsernumber(), 
                                         getAddr(request),
                                         request.getHeader("User-Agent") ,
                                         request.getRequestURI(), 
                                         "用户登录增分",
                                         HttpStatus.OK+"", 
                                         "用户登录增加2分");
                }
                else
                {
                    System.out.printf("%s-->%s\n", "login","login is no add +2");
                    service.recordLogger(user.getUsernumber(), 
                                         getAddr(request),
                                         request.getHeader("User-Agent") ,
                                         request.getRequestURI(), 
                                         "用户登录增分",
                                         HttpStatus.INTERNAL_SERVER_ERROR+"", 
                                         "用户登录增加2分");
                }
			} 
            else 
            {
				responseUtil.setResult(0);
				responseUtil.setCause("密码错误！");
			}
			return g.toJson(responseUtil);
		}
        catch (java.net.ConnectException e) 
        {
			responseUtil.setResult(0);
			responseUtil.setCause("连接数据库失败!");
			return g.toJson(responseUtil);
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
			responseUtil.setResult(0);
			responseUtil.setCause("该用户名或手机号不存在!");
			return g.toJson(responseUtil);
		}
	}
    
    /**
     * app 修改用户信息
     * @param resource
     * @return 
     */
    @RequestMapping(value="/updateCustomInfo", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String updateCustomInfo(@RequestBody String resource)
    {
        try
        {
            System.out.println("string:"+resource);
            JSONObject json = new JSONObject(resource);
            Student student = new Student();
            student.setRealname(json.getString("realname"));
            student.setArea(json.getString("area"));
            student.setClassname(json.getString("classname"));
            student.setEmail(json.getString("email"));
            student.setGrade(json.getString("grade"));
            student.setIdnum(json.getString("idnum"));
            student.setSchool(json.getString("school"));
            student.setUsername(json.getString("username"));
            student.setVersion(json.getString("version"));
            student.setUsernumber(json.getString("usernumber"));
            student.setType(json.getString("type"));
            student.setMobile(json.getString("mobile"));
            boolean isUsernameExit = service.selectStudentInfoByUsernumberAndUsername(student.getUsernumber(),student.getUsername());
            boolean isTeleponeExit = service.selecStudentInfoByUsernumberAndTelephone(student.getUsernumber(), student.getMobile());
            if (isUsernameExit)
            {
                return "{\"status\":409,\"msg\":\"用户名已存在！\"}";
            }
            else if (isTeleponeExit)
            {
                return "{\"status\":409,\"msg\":\"手机号已存在！\"}";
            }  
            service.updateCustomerInfo(student);
            return "{\"status\":200,\"msg\":\"修改成功！\"}";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return toMsgJSONString(500, "修改失败", ex.getMessage());
        }
    }
    
    /**
     * 
     * @param activityId
     * @param flag
     * @param pageNumber
     * @param pageSize
     * @return 
     */
    @RequestMapping(value="/activity/queryActivityParticipant", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String queryStudentActivityData(@RequestParam("activityId") int activityId,
                                           @RequestParam("flag")       int flag,
                                           @RequestParam("pageNumber") int pageNumber,
                                           @RequestParam("pageSize")   int pageSize)
    {
        try
        {
            System.out.println("activityId=======>"+activityId);
            System.out.println("flag=======>"+flag);
            System.out.println("pageNumber=======>"+pageNumber);
            System.out.println("pageSize=======>"+pageSize);
            List<ActivityParticipant> list = (flag == 0)?service.queryActivityParticipantStudentsInfo(activityId, false ,pageNumber<=0?1:pageNumber, pageSize<=0?10:pageSize):service.queryActivityParticipantStudentsInfo(activityId, true,pageNumber<=0?1:pageNumber, pageSize<=0?10:pageSize);
            Map<String,List<Student>> students = service.queryStudents().stream().collect(Collectors.groupingBy(l->l.getUsernumber()));
            StringWriter text= new StringWriter();
            JSONWriter writer = new JSONWriter(text);
            writer.array();
            for (ActivityParticipant ap : list)
            {
                writer.object();
                writer.key("id").value(ap.id);
                writer.key("userNumber").value(ap.userNumber);
                if (students.containsKey(ap.userNumber))
                {
                    Student student = students.get(ap.userNumber).get(0);
                    writer.key("classname").value(student.getClassname());
                    writer.key("grade").value(student.getGrade());
                    writer.key("school").value(student.getSchool());
                    writer.key("area").value(student.getArea());
                    writer.key("realname").value(student.getRealname());
                    writer.key("type").value(student.getType());
                }
                else
                {
                    writer.key("classname").value("未知");
                    writer.key("grade").value("未知");
                    writer.key("school").value("未知");
                    writer.key("area").value("未知");
                    writer.key("realname").value("未知");
                    writer.key("type").value("未知");
                }
                writer.key("activityId").value(ap.activityId);
                writer.key("avatarUrl").value(ap.avatarUrl);
                writer.key("dislikes").value(ap.dislikes);
                writer.key("instructor").value(ap.instructor);
                writer.key("likes").value(ap.likes);
                writer.key("slogan").value(ap.slogan);
                writer.key("status").value(ap.status);
                writer.key("submissionId").value(ap.submissionId);
                writer.key("votes").value(ap.votes);
                writer.endObject();
            }
            writer.endArray();
            return text.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return toMsgJSONString(500, "查询失败", ex.getMessage());
        }
    }
    
    @RequestMapping(value="/activity/listActivityParticipants", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String listActivityParticipants(@RequestParam("activityId") int activityId,
                                           @RequestParam("school")     String school,
                                           @RequestParam("grade")      String grade,
                                           @RequestParam("classname")  String classname,
                                           @RequestParam("realname")   String realname,
                                           @RequestParam("pageNumber") int pageNumber,
                                           @RequestParam("pageSize")   int pageSize)
    {
        try
        {
            System.out.printf("%s--%d--%s--%s--%s--%s--%d--%d\n","no student is check:",
                                                        activityId,
                                                        school,
                                                        grade,
                                                        classname,
                                                        realname,
                                                        pageNumber,
                                                        pageSize);
            List<ActivityParticipant> list = service.listActivityParticipants(activityId, 
                                                                              school.equals("")?null:school, 
                                                                              grade.equals("")?null:grade, 
                                                                              classname.equals("")?null:classname, 
                                                                              realname.equals("")?null:realname, 
                                                                              pageSize<=0?10:pageSize,
                                                                              pageNumber<=0?1:pageNumber);
                    
            if (list == null || list.isEmpty())
                return "[]";
            Map<String,List<Student>> students = service.queryStudents().stream().collect(Collectors.groupingBy(l->l.getUsernumber()));
            StringWriter text= new StringWriter();
            JSONWriter writer = new JSONWriter(text);
            writer.array();
            for (ActivityParticipant ap : list)
            {
                writer.object();
                writer.key("id").value(ap.id);
                writer.key("userNumber").value(ap.userNumber);
                if (students.containsKey(ap.userNumber))
                {
                    Student student = students.get(ap.userNumber).get(0);
                    writer.key("classname").value(student.getClassname());
                    writer.key("grade").value(student.getGrade());
                    writer.key("school").value(student.getSchool());
                    writer.key("area").value(student.getArea());
                    writer.key("realname").value(student.getRealname());
                    writer.key("type").value(student.getType());
                }
                else
                {
                    writer.key("classname").value("未知");
                    writer.key("grade").value("未知");
                    writer.key("school").value("未知");
                    writer.key("area").value("未知");
                    writer.key("realname").value("未知");
                    writer.key("type").value("未知");
                }
                writer.key("activityId").value(ap.activityId);
                writer.key("avatarUrl").value(ap.avatarUrl);
                writer.key("dislikes").value(ap.dislikes);
                writer.key("instructor").value(ap.instructor);
                writer.key("likes").value(ap.likes);
                writer.key("slogan").value(ap.slogan);
                writer.key("status").value(ap.status);
                writer.key("submissionId").value(ap.submissionId);
                writer.key("votes").value(ap.votes);
                writer.endObject();
            }
            writer.endArray();
            return text.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return toMsgJSONString(500, "查询失败！", ex.getMessage());
            
        }
    }
     /**
      * 查询用户积分信息
      * @param usernumber
      * @return
      * @throws Exception 
      */
     @RequestMapping(value="/bonus/bonusAmount", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
     public String queryBonusAmountById(@RequestParam String usernumber)throws Exception
     {
         try
         {
             System.out.printf("%s\n","check bonus account balance");
             BonusAccount account = service.queryBonusAmountById(usernumber);
             if (account == null)
                return "{}";      
             StringWriter writer = new StringWriter(0);
             JSONWriter json = new JSONWriter(writer);
             json.object();
             json.key("id").value(account.id);
             json.key("type").value(account.type);
             json.key("status").value(account.status);
             json.key("balance").value(account.balance);
             json.endObject();
             System.out.printf("%s->%s->%s\n","check bonus account balance","result:",writer.toString());
             return writer.toString();
         }
         catch (Exception ex)
         {
             ex.printStackTrace();
             return toMsgJSONString(500, "查询失败！", ex.getMessage());
         }
     }
    /**
     * 查询用户的账单
     * @param accountId 用户号
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/bonus/bonusSettlement", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String queryBonusById(@RequestParam("accountId") int accountId,
                                                                        @RequestParam("pageNum")   int pageNum,
                                                                        @RequestParam("pageSize")  int pageSize)throws Exception
    {
        try
        {
            System.out.println("accoutId:"+accountId+",pageNum:"+pageNum+",pageSize:"+pageSize);
            List<BonusSettlement> list = service.queryBonusSettlementList(accountId, pageSize, pageNum);
            StringWriter writer = new StringWriter();
            JSONWriter json = new JSONWriter(writer);
            json.array();
            for (BonusSettlement bs : list)
            {
                json.object();
                json.key("id").value(bs.id);
                json.key("title").value(bs.title);
                json.key("createTime").value(bs.createTime);
                json.key("accountId").value(bs.accountId);
                json.key("accountAmount").value(bs.accountAmount);
                json.key("accountType").value(bs.accountType);
                json.key("balance").value(bs.balance);
                json.endObject();
            }
            json.endArray();
            return writer.toString();
        }
        catch (Exception ex)
        {
            return toMsgJSONString(500, "查询失败！", ex.getMessage());
        }
    }
    
    /**
     * 通过手机号或用户名查询用户信息
     * @param identifier
     * @return 
     */
    @RequestMapping(value="/queryUserInfoByIdentifier", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String queryUserInfoByIdentifier(@RequestParam String identifier)
    {
        try
        {
            System.out.println("identifier:"+identifier);
            boolean isMatch = Pattern.matches(phonePattern, identifier);
            UserInfo userInfo = service.queryUserInfoByIndentifier(identifier,isMatch);
            if (userInfo == null)
                return "{}";
            return g.toJson(userInfo);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return toMsgJSONString(500, "查询失败！", ex.getMessage());
        }
    }
    
    /**
     * 通过活动id查询活动事件
     * @param id
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/querySchoolActivity", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String querySchoolActivity(@RequestParam int id)throws Exception
    {
        try
        {
            SchoolActivity activity = service.querySchoolActivity(id);
            if (activity == null)
                return "{}";
            StringWriter writer = new StringWriter();
            JSONWriter json = new JSONWriter(writer);
            json.object();
            json.key("id").value(activity.id);
            json.key("name").value(activity.name);
            json.key("status").value(activity.status);
            json.key("description").value(activity.description);
            json.key("startDate").value(activity.startDate);
            json.key("finishDate").value(activity.finishDate);
            json.endObject();
            return writer.toString();
        }
        catch(Exception ex)
        {
            return toMsgJSONString(500, "查询失败！", ex.getMessage());
        }
    }
    
    /**
     * 上传报名信息
     * @param file 头像
     * @param activityId 活动事件id
     * @param userNumber 用户号
     * @param instructor 指导老师
     * @param slogan 选题
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/insertActivityParticipants", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String insertActivityParticipants(@RequestParam("files") MultipartFile file,
                                             @RequestParam("activityId") int activityId,
                                             @RequestParam("userNumber") String userNumber,
                                             @RequestParam("instructor") String instructor,
                                             @RequestParam("slogan") String slogan,
                                             HttpServletRequest request)throws Exception
    {
        try
        {
            /**
             * 1、上传图片到服务。但是没有考虑数据的完成性，上传图片和报名活动应该是一个原子操作
             * 2、上传信息。
             */
            System.out.println("file------>"+file+"==="+(file.isEmpty()));
            System.out.println("pc=================upload bao ming info====================");
            
            ActivityParticipant ap = service.queryActivityParticipantsByActivityIdAndUsernumber(activityId, userNumber);
            
            if (ap != null)
            {
                ActivityParticipant activityParticipant = new ActivityParticipant();
                activityParticipant.activityId = activityId;
                activityParticipant.userNumber = userNumber;
                activityParticipant.avatarUrl = file.isEmpty()?ap.avatarUrl:uploadAvatarImage(ConfigurationDir.dir, file, userNumber, ConfigurationDir.httpUrl);
                activityParticipant.instructor = instructor;
                activityParticipant.slogan = slogan;
                System.out.printf("%d-%s-%s-%s-%s\n",activityId,userNumber,instructor,slogan,activityParticipant.avatarUrl);
                System.out.printf("%s---%s\n", userNumber,"upload bao ming material of first no add +5");
                System.out.println("update huo dong su cai");
                activityParticipant.id = ap.id;
                activityParticipant.status = ap.status;
                service.updateActivityParticipantsById(activityParticipant);
            }
            else
            {
                String avatarUrl = uploadAvatarImage(ConfigurationDir.dir, file, userNumber, ConfigurationDir.httpUrl);
                if (avatarUrl == null)
                {
                    avatarUrl = DefaultAvatarImage.produceDefaultAvatarImage(ConfigurationDir.dir, 
                                                                             userNumber, 
                                                                             ConfigurationDir.httpUrl, 
                                                                             dateTimeFormatter);
                }
                System.out.printf("%d-%s-%s-%s-%s\n",activityId,userNumber,instructor,slogan,avatarUrl);
                ActivityParticipant activityParticipant = new ActivityParticipant();
                activityParticipant.activityId = activityId;
                activityParticipant.userNumber = userNumber;
                activityParticipant.avatarUrl = avatarUrl;
                activityParticipant.instructor = instructor;
                activityParticipant.slogan = slogan;
                System.out.printf("%s---%s\n", userNumber,"upload bao ming material of first  add +5");
                ActivityParticipant activity = service.insertActivityParticipants(activityParticipant);
                BonusAccount bonusAccount = service.queryBonusAmountById(userNumber);
                if (bonusAccount != null);
                    service.changeBonus(bonusAccount.id, SHOU_RU, Score.SCORE_UPLOAD_HEADER_IMG, "报名上传图片","报名上传上传头像增加5分");
                service.recordLogger(userNumber, 
                            getAddr(request),
                            request.getHeader("User-Agent") ,
                            request.getRequestURI(), 
                            "上传报名头像",
                            HttpStatus.OK+"", 
                            "报名上传上传头像增加5分");
            }
            ActivityParticipant activity = service.queryActivityParticipantsByActivityIdAndUsernumber(activityId, userNumber);
            StringWriter text = new StringWriter();
            JSONWriter writer = new JSONWriter(text);
            writer.object();
            writer.key("id").value(activity.id);
            writer.key("userNumber").value(activity.userNumber);
            writer.key("activityId").value(activity.activityId);
            writer.key("avatarUrl").value(activity.avatarUrl);
            writer.key("dislikes").value(activity.dislikes);
            writer.key("likes").value(activity.likes);
            writer.key("instructor").value(activity.instructor);
            writer.key("status").value(activity.status);
            writer.key("slogan").value(activity.slogan);
            writer.endObject();
            System.out.println("text.toString():"+text.toString());
            return text.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return toMsgJSONString(500, "上传头像失败！", ex.getMessage());
        }
    }
    
    /**
     * 通过活动事件id和用户号查询是否已经报名
     * @param activityId
     * @param usernumber
     * @return 
     */
    @RequestMapping(value="/queryActivityParticipantsByActivityIdAndUsernumber", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String queryActivityParticipantsByActivityIdAndUsernumber(@RequestParam int activityId,
                                                                     @RequestParam String usernumber)
    {   
        try
        {
            ActivityParticipant activityParticipant =service.queryActivityParticipantsByActivityIdAndUsernumber(activityId, usernumber);
            if (activityParticipant != null)
                return toJSONString(activityParticipant);
            else
                return "{}";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new WebAppException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
    
    /**
     * App端
     * @param resource
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/insertActivityParticipantsByApp", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String insertActivityParticipantsByApp(@RequestBody String resource,
                                                  HttpServletRequest request)throws Exception
    {
        try
        {
            System.out.println("app 上传报名信息：resource:"+resource);
            JSONObject object = new JSONObject(resource);
            ActivityParticipant activityParticipant = new ActivityParticipant();
            activityParticipant.activityId = object.getInt("activityId");
            activityParticipant.userNumber = object.getString("userNumber");
            activityParticipant.avatarUrl = object.getString("avatarUrl");
            activityParticipant.instructor = object.getString("instructor");
            activityParticipant.slogan = object.getString("slogan");
            System.out.println("activityId-->:"+object.getInt("activityId")+",userNumber:"+object.getString("userNumber"));
            ActivityParticipant ap = service.queryActivityParticipantsByActivityIdAndUsernumber(object.getInt("activityId"), object.getString("userNumber"));
//            System.out.println(ap.activityId+","+ap.instructor);
            if (ap != null)
            {
                System.out.printf("%s--%s--%s\n",activityParticipant.userNumber, "app","upload bao ming info of first add +5");
                activityParticipant.id = ap.id;
                activityParticipant.status = ap.status;
                service.updateActivityParticipantsById(activityParticipant);
            }
            else
            {
                System.out.printf("%s\n", "upload bao ming info of first add +5");
                ActivityParticipant activity = service.insertActivityParticipants(activityParticipant);
                BonusAccount bonusAccount = service.queryBonusAmountById(object.getString("userNumber"));
                if (bonusAccount != null)
                    service.changeBonus(bonusAccount.id, SHOU_RU, Score.SCORE_UPLOAD_HEADER_IMG, "报名上传图片","报名上传上传头像增加5分");
                service.recordLogger(activityParticipant.userNumber, 
                            getAddr(request),
                            request.getHeader("User-Agent") ,
                            request.getRequestURI(), 
                            "App端上传报名头像",
                            HttpStatus.OK+"", 
                            "报名上传头像增加5分");
            }
            ActivityParticipant activity = service.queryActivityParticipantsByActivityIdAndUsernumber(activityParticipant.activityId, activityParticipant.userNumber);
            StringWriter text = new StringWriter();
            JSONWriter writer = new JSONWriter(text);
            writer.object();
            writer.key("id").value(activity.id);
            writer.key("userNumber").value(activity.userNumber);
            writer.key("activityId").value(activity.activityId);
            writer.key("avatarUrl").value(activity.avatarUrl);
            writer.key("dislikes").value(activity.dislikes);
            writer.key("likes").value(activity.likes);
            writer.key("instructor").value(activity.instructor);
            writer.key("status").value(activity.status);
            writer.key("slogan").value(activity.slogan);
            writer.endObject();
            System.out.println("text.toString():"+text.toString());
            return text.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return "{\"status\":500,\"msg\":\"上传失败！\",\"error\":\""+ex.getMessage()+"\"}";
        }
    }
    
    /**
     *App 上传素材
     * @param resouces
     * @param request
     * @return
     * @throws HttpStatusCodeException 
     */
    @RequestMapping(value="/uploadActivityMaterialsByApp", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String uploadActivityMaterialsOfFileByApp(@RequestBody String resouces,
                                                     HttpServletRequest request)throws HttpStatusCodeException
    {
        try
        {
            System.out.println("app upload material resource:"+resouces);
            AppActivityMaterials appActivityMaterials = parseAppUploadMaterial(resouces);
            System.out.println("appActivityMaterials:"+appActivityMaterials.getUsernumber());
            System.out.println("app upload materials add +20");
            //此处的做的不够严谨，现在只有一个积分账户，如果以后多了，就不对了
            ActivityParticipant activityParticipant = service.queryActivityParticipantsById(appActivityMaterials.getParticipantId());
            if (activityParticipant != null && activityParticipant.submissionId == 0)
            {
                System.out.println("activityParticipant.submissionId:"+activityParticipant.submissionId);
                BonusAccount bonusAccount = service.queryBonusAmountById(appActivityMaterials.getUsernumber());
                if (bonusAccount != null)
                    service.changeBonus(bonusAccount.id,SHOU_RU , Score.SCORE_UPLOAD_MATERIAL, "上传素材", "上传作品加20分,App端");
                service.recordLogger(activityParticipant.userNumber, 
                            getAddr(request),
                            request.getHeader("User-Agent") ,
                            request.getRequestURI(), 
                            "App端上传素材",
                            HttpStatus.OK+"", 
                            "上传素材增加20分");
            }
            service.uploadFileMaterials(appActivityMaterials.getParticipantId(), appActivityMaterials.getTitle(), appActivityMaterials.getWorks());
            ActivityParticipant newActivityParticipant = service.queryActivityParticipantsById(appActivityMaterials.getParticipantId());
            return "{\"status\":200,\"msg\":\"success\",\"submissionsId\":"+newActivityParticipant.submissionId+"}";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return "{\"status\":500,\"msg\":\"上传失败！\",\"error\":\""+ex.getMessage()+"\"}";
                    
        }
    }
    
    /**
     * PC 上传文件素材包括图片和文件
     * @param file
     * @param participantId 报名信息id
     * @param title 报名选题
     * @param usernumber 用户号
     * @param flag 标记 0表示没有上传图片或文件；1表示有上传图片或文件
     * @param request
     * @return 
     */
    @RequestMapping(value="/uploadActivityMaterialsOfFile", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String uploadActivityMaterialsOfFile(@RequestParam("files")         MultipartFile[] file,
                                                @RequestParam("participantId") int  participantId,
                                                @RequestParam("title")         String title,
                                                @RequestParam("usernumber")    String usernumber,
                                                @RequestParam("flag")          int flag,
                                                HttpServletRequest request)
                                           
                                           
    {
        try 
        {
            System.out.println("pc file:"+file.length);
            System.out.println("pc participantId:"+participantId);
            System.out.println("pc title:"+title);
            System.out.println("pc usernumber:"+usernumber);
            System.out.println("pc flag:"+flag);
            QualifiedParticipant qualifiedParticipant = service.queryStudentActivityWorksById(participantId);
            if(qualifiedParticipant == null && flag==0)
            {
                return "{\"status\":406,\"msg\":\"请上传素材！\"}";
            }
            //判断是否有上传素材
            if (qualifiedParticipant == null)
            {
                String works = uploadFileMaterials(ConfigurationDir.dir,file, usernumber,ConfigurationDir.httpUrl);
                System.out.println("works:"+works);
                System.out.println("upload zuo ping add +20");
                service.uploadFileMaterials(participantId, title, works);
                BonusAccount bonusAccount = service.queryBonusAmountById(usernumber);
                if (bonusAccount != null)
                    service.changeBonus(bonusAccount.id,SHOU_RU , Score.SCORE_UPLOAD_MATERIAL, "上传素材", "上传作品加20分，PC端");
                service.recordLogger(usernumber, 
                                    getAddr(request),
                                    request.getHeader("User-Agent") ,
                                    request.getRequestURI(), 
                                    "PC端上传素材",
                                    HttpStatus.OK+"", 
                                    "上传素材增加20分");
            }
            else
            {
                System.out.println("update works");
                String works = (flag==0)?qualifiedParticipant.submissionWorks:uploadFileMaterials(ConfigurationDir.dir,file, usernumber,ConfigurationDir.httpUrl);
                System.out.println("update works ==:"+works);
                service.uploadFileMaterials(participantId, title, works);
            }
            
            ActivityParticipant newActivityParticipant = service.queryActivityParticipantsById(participantId);
            return "{\"status\":200,\"msg\":\"上传成功！\",\"submissionsId\":"+newActivityParticipant.submissionId+"}";
        }
        catch(Exception ex)
        {
           ex.printStackTrace();
           return "{\"status\":500,\"msg\":\"上传失败！\",\"error\":\""+ex.getMessage()+"\"}";
        }
    }
    
    /**
     * PC 上传手写素材，并保存.html文件
     * @param activityMaterials
     * @param request
     * @return 
     */
    @RequestMapping(value="/uploadActivityMaterialsOfContent", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String uploadActivityMaterialsOfContent(@RequestBody ActivityMaterials activityMaterials,
                                                   HttpServletRequest request)
                                           
                                           
    {
        try 
        {
            System.out.println("pc activityMaterials.content:"+activityMaterials.getContent());
            String works = uploadContentMaterials(ConfigurationDir.dir, activityMaterials.getContent(), activityMaterials.getUsernumber(),ConfigurationDir.httpUrl);
            System.out.println("work:"+works);
            ActivityParticipant activityParticipant = service.queryActivityParticipantsById(activityMaterials.getParticipantId());
            //判断是否有上传素材
            if (activityParticipant != null && activityParticipant.submissionId == 0)
            {
                BonusAccount bonusAccount = service.queryBonusAmountById(activityMaterials.getUsernumber());
                if (bonusAccount != null)
                    service.changeBonus(bonusAccount.id,SHOU_RU , Score.SCORE_UPLOAD_MATERIAL, "上传素材", "上传作品加20分，PC端");
                service.recordLogger(activityParticipant.userNumber, 
                            getAddr(request),
                            request.getHeader("User-Agent") ,
                            request.getRequestURI(), 
                            "PC端上传素材",
                            HttpStatus.OK+"", 
                            "上传素材增加20分(手写文字)");
                
            }
            service.uploadFileMaterials(activityMaterials.getParticipantId(), activityMaterials.getTitle(), works);
            ActivityParticipant newActivityParticipant = service.queryActivityParticipantsById(activityMaterials.getParticipantId());
            return "{\"status\":200,\"msg\":\"success\",\"submissionsId\":"+newActivityParticipant.submissionId+"}";
        }
        catch(Exception ex)
        {
           ex.printStackTrace();
           return "{\"status\":500,\"msg\":\"上传失败！\",\"error\":\""+ex.getMessage()+"\"}";
        }
    }
    
    /**
     * 查询作品
     * @param count 指定作品人数
     * @param area 地域
     * @return 
     */
    @RequestMapping(value="/queryStudentActivityWorks", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String queryStudentActivityWorks(@RequestParam int count,
                                            @RequestParam String area)
    {
        try
        {
            List<QualifiedParticipant> list = service.queryStudentActivityWorks(area,count);
            if (list == null)
                return "[]";
            StringWriter text = new StringWriter();
            JSONWriter writer = new JSONWriter(text);
            writer.array();
            for (QualifiedParticipant qualifiedParticipant : list)
            {
                writer.object();
                writer.key("id").value(qualifiedParticipant.id);
                writer.key("realName").value(qualifiedParticipant.realName);
                writer.key("className").value(qualifiedParticipant.className);
                writer.key("school").value(qualifiedParticipant.school);
                writer.key("grade").value(qualifiedParticipant.grade);
                writer.key("avatarUrl").value(qualifiedParticipant.avatarUrl);
                writer.key("instructor").value(qualifiedParticipant.instructor);
                writer.key("slogan").value(qualifiedParticipant.slogan);
                writer.key("status").value(qualifiedParticipant.status);
                writer.key("submissionTitle").value(qualifiedParticipant.submissionTitle);
                writer.key("submissionUploadTime").value(qualifiedParticipant.submissionUploadTime);
                writer.key("activityId").value(qualifiedParticipant.activityId);
                writer.key("submissionWorks").value(qualifiedParticipant.submissionWorks);
                writer.key("userNumber").value(qualifiedParticipant.userNumber);
                writer.key("likes").value(qualifiedParticipant.likes);
                writer.key("votes").value(qualifiedParticipant.votes);
                writer.endObject();
            }
            writer.endArray();
            return text.toString();
        }
        catch(Exception ex)
        {
           ex.printStackTrace();
           return toMsgJSONString(500, "查询失败！", ex.getMessage());
        }
    }
    
    @RequestMapping(value="/queryStudentActivityWorksById", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String queryStudentActivityWorksById(@RequestParam("id") int id)
    {
        try
        {
            System.out.println("查询作品："+id);
            QualifiedParticipant qualifiedParticipant = service.queryStudentActivityWorksById(id);
            if (qualifiedParticipant == null)
                return "{}";
            StringWriter text = new StringWriter();
            JSONWriter writer = new JSONWriter(text);
            writer.object();
            writer.key("id").value(qualifiedParticipant.id);
            writer.key("realName").value(qualifiedParticipant.realName);
            writer.key("className").value(qualifiedParticipant.className);
            writer.key("school").value(qualifiedParticipant.school);
            writer.key("grade").value(qualifiedParticipant.grade);
            writer.key("avatarUrl").value(qualifiedParticipant.avatarUrl);
            writer.key("instructor").value(qualifiedParticipant.instructor);
            writer.key("slogan").value(qualifiedParticipant.slogan);
            writer.key("status").value(qualifiedParticipant.status);
            writer.key("submissionTitle").value(qualifiedParticipant.submissionTitle);
            writer.key("submissionUploadTime").value(qualifiedParticipant.submissionUploadTime);
            writer.key("activityId").value(qualifiedParticipant.activityId);
            writer.key("submissionWorks").value(qualifiedParticipant.submissionWorks);
            writer.key("userNumber").value(qualifiedParticipant.userNumber);
            writer.key("likes").value(qualifiedParticipant.likes);
            writer.key("votes").value(qualifiedParticipant.votes);
            writer.endObject();
            return text.toString();
        }
        catch(Exception ex)
        {
           ex.printStackTrace();
           return toMsgJSONString(500, "查询失败！", ex.getMessage());
        }
    }
    /**
     * 查询用户是否报名
     * @param usernumber
     * @param activityId
     * @return 
     */
    @RequestMapping(value="/queryActivityParticipantsByConditional", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String queryActivityParticipantsByConditional(@RequestParam("usernumber") String usernumber,
                                                         @RequestParam("activityId") int activityId)
    {
        try
        {
            System.out.println("查询参加活动 parameter:"+usernumber+","+activityId);
            ActivityParticipant activityParticipant =service.queryActivityParticipantsByActivityIdAndUsernumber(activityId, usernumber);
            if (activityParticipant == null)
                return "{}";
            Student student = service.selectStudentInfoByUsernumber(usernumber);
            StringWriter text = new StringWriter();
            JSONWriter writer = new JSONWriter(text);
            writer.object();
            writer.key("id").value(activityParticipant.id);
            writer.key("userNumber").value(activityParticipant.userNumber);
            writer.key("username").value(student.getUsername());
            writer.key("realname").value(student.getRealname());
            writer.key("classname").value(student.getClassname());
            writer.key("grade").value(student.getGrade());
            writer.key("school").value(student.getSchool());
            writer.key("area").value(student.getArea());
            writer.key("type").value(student.getType());
            writer.key("activityId").value(activityParticipant.activityId);
            writer.key("avatarUrl").value(activityParticipant.avatarUrl);
            writer.key("dislikes").value(activityParticipant.dislikes);
            writer.key("instructor").value(activityParticipant.instructor);
            writer.key("likes").value(activityParticipant.likes);
            writer.key("slogan").value(activityParticipant.slogan);
            writer.key("status").value(activityParticipant.status);
            writer.key("submissionId").value(activityParticipant.submissionId);
            writer.key("votes").value(activityParticipant.votes);
            writer.endObject();
            return text.toString();
        }
        catch (Exception ex)
        {
           ex.printStackTrace();
           return toMsgJSONString(500, "查询失败！", ex.getMessage());
        }
    }
    
    
    @RequestMapping(value="/queryActivityParticipantsById", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String queryActivityParticipantsById(@RequestParam("id") int id)
    {
        try
        {
            ActivityParticipant activityParticipant = service.queryActivityParticipantsById(id);
            if (activityParticipant == null)
                return "{}";
            StringWriter text = new StringWriter();
            JSONWriter writer = new JSONWriter(text);
            writer.object();
            writer.key("id").value(activityParticipant.id);
            writer.key("userNumber").value(activityParticipant.userNumber);
            writer.key("activityId").value(activityParticipant.activityId);
            writer.key("avatarUrl").value(activityParticipant.avatarUrl);
            writer.key("dislikes").value(activityParticipant.dislikes);
            writer.key("instructor").value(activityParticipant.instructor);
            writer.key("likes").value(activityParticipant.likes);
            writer.key("slogan").value(activityParticipant.slogan);
            writer.key("status").value(activityParticipant.status);
            writer.key("submissionId").value(activityParticipant.submissionId);
            writer.key("votes").value(activityParticipant.votes);
            writer.endObject();
            return text.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return toMsgJSONString(500, "查询失败！", ex.getMessage());
        }
    }
    
    /**
     * 点赞
     * @param resource
     * @return 
     */
    @RequestMapping(value="/activity/iLikes", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String ILikes(@RequestParam String commentatorUsernumber,
                        @RequestParam String participantUsernumber,
                        @RequestParam int participantId,
                        @RequestParam int activityId,
                        HttpServletRequest request)
    {
        try
        {
            
            /**
             * 1、学生用户给其他人点赞（包括自己）每日五次
             * 2、非学生用户给其他人点赞每日五次
             * 3、加分对象点赞的人
             * {
             *   commentatorUsernumber:xxxx,点赞人
             *   participantUsernumber:xxxxx,被点赞人
             *   participantId://报名信息的id，
             *   activityId:ddd//活动id
             * }
             */
            System.out.println("likes resource:"+commentatorUsernumber);
//            JSONObject json = new JSONObject("");
            
            int frequency = service.queryLoggerByConditional(commentatorUsernumber, request.getRequestURI(), HttpStatus.OK+"", LocalDate.now());
            if (frequency <=4)
            {
                BonusAccount bonusAccount = service.queryBonusAmountById(commentatorUsernumber);
                if (bonusAccount != null)
                    service.changeBonus(bonusAccount.id, SHOU_RU, 1, "点赞", "点赞一次送1分");
                int count = service.addLikes(participantId);
                //加日志
                service.recordLogger(commentatorUsernumber, 
                                     getAddr(request),
                                     request.getHeader("User-Agent") ,
                                     request.getRequestURI(), 
                                     "点赞增分",
                                     HttpStatus.OK+"", 
                                     String.format("%s->%s", commentatorUsernumber,participantUsernumber));
                ActivityParticipant activityParticipant = service.queryActivityParticipantsById(participantId);
//                return "{\"status\":200,\"chance\":"+frequency+",\"count\":"+activityParticipant.likes+"}";
                return "{\"status\":200,\"chance\":"+(4-frequency)+",\"count\":"+activityParticipant.likes+"}";
            }
            else
            {
                //加日志
                service.recordLogger(commentatorUsernumber, 
                                     getAddr(request),
                                     request.getHeader("User-Agent") ,
                                     request.getRequestURI(), 
                                     "点赞增分",
                                     HttpStatus.INTERNAL_SERVER_ERROR+"", 
                                     String.format("%s->%s", commentatorUsernumber,participantUsernumber));
                ActivityParticipant activityParticipant = service.queryActivityParticipantsById(participantId);
                return "{\"status\":200,\"chance\":0,\"count\":"+activityParticipant.likes+"}";
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return toMsgJSONString(500, "查询失败！", ex.getMessage());
        }
    }
    
    @RequestMapping(value="/activity/iVotes", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String IVotes(@RequestParam String commentatorUsernumber,
                        @RequestParam String participantUsernumber,
                        @RequestParam int participantId,
                        @RequestParam int activityId,
                         HttpServletRequest request)
    {
        try
        {
            /**
             * 1、学生用户给其他人投票（包括自己）每日1次
             * {
             *   commentatorUsernumber:xxxx,点赞人
             *   participantUsernumber:xxxxx,被点赞人
             *   participantId://报名信息的id，
             *   activityId:ddd//活动id
             * }
             */
            
            int frequency = service.queryLoggerByConditional(commentatorUsernumber, request.getRequestURI(), HttpStatus.OK+"", LocalDate.now());
            if (frequency <=0)
            {
                BonusAccount bonusAccount = service.queryBonusAmountById(commentatorUsernumber);
                System.out.println("bonus_id:"+bonusAccount.id+",commentator:"+commentatorUsernumber);
                service.changeBonus(bonusAccount.id, SHOU_RU, Score.SCORE_VOTE, "投票", "投票一次送1分");
                System.out.println("participantId:"+participantId);
                int count = service.addVotes(participantId);
                //加日志
                service.recordLogger(commentatorUsernumber, 
                                     getAddr(request),
                                     request.getHeader("User-Agent") ,
                                     request.getRequestURI(), 
                                     "投票增积分",
                                     HttpStatus.OK+"", 
                                     String.format("%s->%s", commentatorUsernumber,participantUsernumber));
                ActivityParticipant activityParticipant = service.queryActivityParticipantsById(participantId);
//                return "{\"status\":200,\"chance\":"+frequency+",\"count\":"+activityParticipant.votes+"}";
                return "{\"status\":200,\"chance\":"+(0-frequency)+",\"count\":"+activityParticipant.votes+"}";
            }
            else
            {
                //加日志
                service.recordLogger(commentatorUsernumber, 
                                     getAddr(request),
                                     request.getHeader("User-Agent") ,
                                     request.getRequestURI(), 
                                     "投票增积分",
                                     HttpStatus.INTERNAL_SERVER_ERROR+"", 
                                     String.format("%s->%s", commentatorUsernumber,participantUsernumber));
                ActivityParticipant activityParticipant = service.queryActivityParticipantsById(participantId);
                return "{\"status\":200,\"chance\":0,\"count\":"+activityParticipant.votes+"}";
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return toMsgJSONString(500, "查询失败！", ex.getMessage());
        }
    }
    
    @RequestMapping(value="/activity/updateAvatarByParticipantId", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String updateAvatarByParticipantId(@RequestParam("file") MultipartFile file,
                                              @RequestParam("participantId") int participantId,
                                              @RequestParam("usernumber") String usernumber)
    {
        try
        {
            String avatarUrl = uploadAvatarImage(ConfigurationDir.dir, file, usernumber,ConfigurationDir.httpUrl);
            service.uploadAvatarByParticipantId(participantId, avatarUrl);
            ActivityParticipant activityParticipant = service.queryActivityParticipantsById(participantId);
            StringWriter text = new StringWriter();
            JSONWriter writer = new JSONWriter(text);
            writer.object();
            writer.key("id").value(activityParticipant.id);
            writer.key("usernumber").value(activityParticipant.userNumber);
            writer.key("submissionId").value(activityParticipant.submissionId);
            writer.key("avatarUrl").value(activityParticipant.avatarUrl);
            writer.key("dislikes").value(activityParticipant.dislikes);
            writer.key("instructor").value(activityParticipant.instructor);
            writer.key("likes").value(activityParticipant.likes);
            writer.key("slogan").value(activityParticipant.slogan);
            writer.key("status").value(activityParticipant.status);
            writer.key("votes").value(activityParticipant.votes);
            writer.endObject();
            return text.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return toMsgJSONString(500, "查询失败！", ex.getMessage());
        }
    }
    
    @RequestMapping(value="/activity/queryActivityParticipantsBySchool", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody 
    public String queryActivityParticipantsBySchool(@RequestParam("school") String school,
                                                    @RequestParam("pageNumber")int pageNumber,
                                                    @RequestParam("pageSize")int pageSize)
    {
        try
        {
            List<QualifiedParticipant> list = service.queryActivityParticipantsBySchool(school, 
                                                                                        pageNumber,
                                                                                        pageSize);
            if (list == null && list.isEmpty())
                return "[]";
            StringWriter text = new StringWriter();
            JSONWriter writer = new JSONWriter(text);
            writer.array();
            for (QualifiedParticipant qpp: list)
            {
                writer.object();
                writer.key("id").value(qpp.id);
                writer.key("userNumber").value(qpp.userNumber);
                writer.key("area").value(qpp.area);
                writer.key("avatarUrl").value(qpp.avatarUrl);
                writer.key("classname").value(qpp.className);
                writer.key("dislikes").value(qpp.dislikes);
                writer.key("grade").value(qpp.grade);
                writer.key("activityId").value(qpp.activityId);
                writer.key("instructor").value(qpp.instructor);
                writer.key("likes").value(qpp.likes);
                writer.key("realname").value(qpp.realName);
                writer.key("school").value(qpp.school);
                writer.key("slogn").value(qpp.slogan);
                writer.key("status").value(qpp.status);
                writer.key("votes").value(qpp.votes);
                writer.key("submissionId").value(qpp.submissionId);
                writer.key("submisstionTitle").value(qpp.submissionTitle);
                writer.key("submisstionUploadTime").value(qpp.submissionUploadTime);
                writer.key("submisstionWorks").value(qpp.submissionWorks);
                writer.endObject();
            }
            writer.endArray();
            return text.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return toMsgJSONString(500, "查询失败！", ex.getMessage());
        }
    }
	// send sms
	 @RequestMapping(value="/student/sms", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public String sms(@RequestParam String mobile, @RequestParam String code) throws UnsupportedEncodingException {
		 Status re =SMS.sendSMSQZ(mobile, code);
		 Gson g = new Gson();
		 System.out.println(g.toJson(re));
		 ResponseUtil responseUtil=new ResponseUtil();
		 if("200".equals(re.getCode())){
			responseUtil.setResult(1);	
		 }else{
		    System.out.println(code+"=====================");
			responseUtil.setResult(0);
			responseUtil.setCause("发送失败！");
		 }
		 return g.toJson(responseUtil);
	 }

    
    // 验证用户名是否重复注册
    @RequestMapping(value="/student/checkusername")  
    public @ResponseBody ResponseUtil checkusername(@RequestParam String username) throws IOException{
    	ResponseUtil responseUtil=null;
    	try{
    		responseUtil=new ResponseUtil();
    	  	if(service.selectUsername(username)) {
    	  		responseUtil.setResult(0);
    			responseUtil.setCause("用户名已存在！");
        	} else {
        		responseUtil.setResult(1);
        	}
    		return responseUtil;    		
    	}catch(Exception e) {
    		e.printStackTrace();
			responseUtil.setResult(0);
			responseUtil.setCause("获取数据异常！");
		    return responseUtil;
    	}  
    } 
    
    // 验证用户名是否重复注册
    @RequestMapping(value="/student/checkusername2")  
    public @ResponseBody ResponseUtil checkusername2(@RequestParam String username) throws IOException{
    	ResponseUtil responseUtil=null;
    	System.out.println("xxxxxxxxxx1aaaa");
    	try{
    		responseUtil=new ResponseUtil();
    	  	if(service.selectUsername(username)) {
    	  		responseUtil.setResult(0);
    			responseUtil.setCause("用户名已存在！");
        	} else {
        		responseUtil.setResult(1);
        	}
    		return responseUtil;    		
    	}catch(Exception e) {
    		e.printStackTrace();
			responseUtil.setResult(0);
			responseUtil.setCause("获取数据异常！");
		    return responseUtil;
    	}  
    } 

    // 验证手机号是否重复注册
    @RequestMapping(value="/student/checkmobile")
    public @ResponseBody ResponseUtil checkmobile(@RequestParam String mobile) throws Exception{ 
    	ResponseUtil responseUtil=null;
    	try{
    		responseUtil = new ResponseUtil();
    	  	if(service.selectMobile(mobile)) {
    			responseUtil.setResult(0);
    			responseUtil.setCause("手机号重复！");
        	} else {
    			responseUtil.setResult(1);
        	}
    		return responseUtil;    		
    	} catch(Exception e) {
			responseUtil.setResult(0);
			responseUtil.setCause("获取数据异常！");
		    return responseUtil;
    	}  
    }
    
    // 发表视频的评论
	@RequestMapping(value = "/filter/videocomment", method = RequestMethod.POST)
	public @ResponseBody ResponseUtil  videocomment(Comment c) throws UnsupportedEncodingException {
		ResponseUtil responseUtil=null;
		try {
			responseUtil=new ResponseUtil();
			service.createComment(c);
			responseUtil.setResult(1);
			return responseUtil;
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(0);
			responseUtil.setCause("创建评论失败");
			return responseUtil;
		}
	}  
	
	// 修改个人信息
	@RequestMapping(value = "/student/update", method = RequestMethod.POST)
	public @ResponseBody ResponseUtil  studentupdate(Student student, HttpSession session) throws UnsupportedEncodingException {
		ResponseUtil responseUtil=null;
		System.out.println("update->"+student.toString());
        try {
            System.out.println("===========>"+student.getUsername());
            System.out.println("===========>"+student.getUsernumber());
			responseUtil = new ResponseUtil();
			
			student.setUsernumber((String)session.getAttribute("userid"));
			// 用户名已经存在
			String username = student.getUsername();
            String ssss = student.getUsernumber();
            System.out.println("username:"+username);
            System.out.println("usernumber:"+ssss);
			Student o = service.selectByUserName(username);
            System.out.println("select:-->"+o);
			if (o != null) {	
				String stuNum = o.getUsernumber();
                System.out.println("stuNum:"+stuNum+"----usernumebr:"+student.getUsernumber()+"==========="+(stuNum.equals(student.getUsernumber())));
				if ( null!=stuNum && ! stuNum.equals( student.getUsernumber() )) {
					responseUtil.setResult(0);
					responseUtil.setCause("修改用户信息失败: 用户名不能和其他用户相同("+o.getUsernumber()+")");
					return responseUtil;
				}
			}
			int ra = service.updateUser(student);
			if (ra > 0) {
				responseUtil.setResult(1);
			} else {
				responseUtil.setResult(0);
				responseUtil.setCause("修改用户信息失败");
			}
			return responseUtil;
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(0);
			responseUtil.setCause("修改服务器异常");
			return responseUtil;
		}
	}
	
	// 删除评论
	@RequestMapping(value = "/filter/deleteComment")
	public @ResponseBody ResponseUtil  deleteComment(String commentid) throws UnsupportedEncodingException {
		ResponseUtil responseUtil = null;
		try {
			responseUtil = new ResponseUtil();
			service.deleteComment(Integer.valueOf(commentid));
			responseUtil.setResult(1);
			return responseUtil;
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(0);
			responseUtil.setCause("删除评论失败");
			return responseUtil;
		}
	}
	
	// 修改密码
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody ResponseUtil resetPassword(@RequestParam String userid, @RequestParam String oldPassword, @RequestParam String password) throws UnsupportedEncodingException {
		Student user;
		boolean result;
		
		try {
			user = service.selectByUserid(userid);
//			System.out.println(user.getPwd());
			result = PasswordEncryption.authenticate(oldPassword, user.getPwd(), user.getSalt());
			if (result) {
				user.setPwd(password);
				service.updateByUserid(user);
				responseUtil.setResult(1);			
			} else {
				responseUtil.setResult(0);
				responseUtil.setCause("原密码错误！");
			}
			return responseUtil;
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(0);
			responseUtil.setCause("系统错误！");
			return responseUtil;
		}
	}  
	
	// 上传图文大事件
	@RequestMapping(value = "/postevent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody  ResponseUtil saveEvent(@RequestParam("imageFile") MultipartFile imageFile,
			HttpServletRequest request,
			@RequestParam("userid") String userId,
			@RequestParam("createDate") String createDate,
			@RequestParam("title") String title,
			@RequestParam("content") String content)
					throws UnsupportedEncodingException {

		ResponseUtil responseUtil = null;
		
		// handle FormData, file upload
		String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";
		// String uploadDir = Constants.DIR_EVENT_IMG + "/";
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String filename = new StringBuilder(createDate).append(".").append(userId).append(".jpg").toString();
		File targetFile = new File(uploadDir + filename);
		
		try {
			responseUtil = new ResponseUtil();
			
			if (!targetFile.exists()) {
				targetFile.createNewFile();
			}
			System.out.println("文件上传到: " + targetFile.getAbsolutePath());
			imageFile.transferTo(targetFile);
						
			BigEvent e = new BigEvent();
			e.setImageFile(filename);
			e.setImg(Constants.URL_EVENT_IMG + "/" + filename);
			e.setUserid(userId);
			e.setCreateDate(java.sql.Date.valueOf(createDate));
			e.setTitle(title);
			e.setContent(content);			
			service.saveEvent(e);
			
			responseUtil.setResult(1);
			return responseUtil;
		} catch (Exception e) {
			e.printStackTrace();
			responseUtil.setResult(0);
			responseUtil.setCause("提交失败");
			return responseUtil;
		}	
	}
	
	@RequestMapping(value = "/filter/getevent")
	public @ResponseBody  Map<String,BigEvent> getevent(String userid) throws UnsupportedEncodingException {
		List<BigEvent> bigEvents = null;
		Map<String, BigEvent> map = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			responseUtil = new ResponseUtil();
			bigEvents = service.getEventByUserid(userid);
			
			map = new HashMap<String, BigEvent>();
			for (BigEvent e : bigEvents) {				
				map.put(df.format(e.getCreateDate()), e);
			}
			return map;
		} catch (Exception e) {
			return map;
		}
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, String referer) {
		StringBuilder sb = new StringBuilder("redirect:" + com.tsinghuadtv.www.util.Constants.APP_ROOT + "/login.jsp");
		if (null != referer) {
			sb.append("?referer=").append(URLDecoder.decode(referer));
		}
		HttpSession session = request.getSession();
		if (null != session) {
			session.invalidate();
		}

		return sb.toString();
	}
	
	// deprecated
	@RequestMapping("/student/switch-school")
	public String switchSchool(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder("redirect:../model-school.jsp?school=");
		String school = request.getParameter("school");
		HttpSession session = request.getSession();
		session.setAttribute("tmp_school", school);
		
		String sc = URLEncoder.encode(school);
		System.out.println(sc);
		sb.append(sc);
				
		return sb.toString();
	}

	/**
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/student/findPwd", method = RequestMethod.POST)
	// @RequestParam String pwd, @RequestParam String sms
	public @ResponseBody ResponseUtil findPwd(HttpServletRequest request) throws UnsupportedEncodingException {
		ResponseUtil resp = new ResponseUtil();
		HttpSession session = request.getSession();
		String sessCode = (String) session.getAttribute("code");
		if (null == sessCode) {
			resp.setResult(2);
			resp.setCause("session已经过期,请重新获取验证码.");
			return resp;
		}
		System.out.println("get code from session: " + sessCode);
		
		String code = request.getParameter("code");
		if (!code.equals(sessCode)) {
			resp.setResult(3);
			resp.setCause("短信验证码错误");
			return resp;
		}
		
		String mobile = request.getParameter("mobile");
		if (!Pattern.matches(phonePattern, mobile)) {
			resp.setResult(4);
			resp.setCause("手机号不合法");
			return resp;
		}
		
		String password = request.getParameter("password");
		int ret = 0;
		try {
			ret = service.resetPwdByMobile(mobile, password);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		if (ret > 0) {
			resp.setResult(0);
			resp.setCause("success");	
		} else {
			resp.setResult(5);
			resp.setCause("update db erorr");
		}
		 
		return resp;
	}
    
	@RequestMapping(value = "/parse/userAgent", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String parseUserAgent(HttpServletRequest request)
    {
        try
        {
            String header = request.getHeader("User-Agent").toLowerCase();
            System.out.println("header:"+header);
            if (header.contains("android")|| header.contains("iphone")||
                header.contains("ipad")   || header.contains("phone") ||
                header.contains("mobile"))
                {
                    return "{\"status\":404}";
                }
                else
                {
                    return "{\"status\":200}";
                }
        }
        catch (Exception ex)
        {
            return "{\"status\":500}";
        }
        
    }
	/**
	 * 找回密码 发送sms
	 * @return 0: success
	 * @throws UnsupportedEncodingException
	 */
	 @RequestMapping(value="/student/sessionSMS", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public String sessionSMS(HttpServletRequest request) throws UnsupportedEncodingException {
		 String mobile = request.getParameter("mobile");
		 ResponseUtil responseUtil = new ResponseUtil();
		 // mobile exists?
		 try {
			Student student = service.selectByPhone(mobile);
			if (null == student.getUsername()) {
				responseUtil.setResult(-1);
				responseUtil.setCause("该手机号未注册,请直接用这个手机号注册.");
				return g.toJson(responseUtil);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			responseUtil.setResult(-2);
			responseUtil.setCause("数据库查询手机号出错");
			return g.toJson(responseUtil);
		}
		 
		 // generate code => session
		 String code = service.gensmscode(6);		
		 HttpSession session = request.getSession();
		 session.setAttribute("code", code);
		 
		 // send sms code, 测试时候不发送验证码
		 // 上线时候DEBUG 改为false
		 final boolean DEBUG = false;
		 if (DEBUG) {
			 System.out.println(mobile);
			 System.out.println("set code: " + code);
			 responseUtil.setResult(0);
			 responseUtil.setCause("sent success"); 
		 } else {
			 Status re =SMS.sendSMSQZ(mobile, code);		 
			 System.out.println(g.toJson(re));
			 
			 if("200".equals(re.getCode())) {
				responseUtil.setResult(0);
				responseUtil.setCause("sent success");
			 } else {
			    System.out.println(code+"=====================");
				responseUtil.setResult(1);
				responseUtil.setCause("发送失败！");
			 } 
		 }
		 
		 return g.toJson(responseUtil);		 
	 }
     
     @RequestMapping(value="/file/updateHeadPicture", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
     public String uploadCJImge(@RequestParam("imgeFile")  String file)
     {
         System.out.println("file:"+file);
         Base64.Decoder base64 = Base64.getDecoder();
         byte[] bytes = base64.decode(file.split(",")[1]);
         try(FileOutputStream out = new FileOutputStream("d:\\img.jpg"))
                 
         {
             out.write(bytes);
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
         return "OK";
     }
     private String uploadAvatarImage(String path,MultipartFile image, String usernumber,String url)
     {
         LocalDateTime creatTime = LocalDateTime.now();
         File imgeFile = Paths.get(path, "writerActivity").toFile();
         if (!imgeFile.exists() && !imgeFile.isDirectory())
         {
             imgeFile.mkdir();
         }
         File file = Paths.get(imgeFile.getAbsolutePath(), usernumber).toFile();
         if (!file.exists() && !file.isDirectory())
         {
             file.mkdir();
         }
         
         try (InputStream is = image.getInputStream();
              OutputStream os = new FileOutputStream(Paths.get(path, "writerActivity",usernumber,String.format("%s%s.%s","avatar",creatTime.format(dateTimeFormatter),image.getOriginalFilename().split("\\.")[1])).toFile()))
         {
             byte[] buf = new byte[2048];
             while (true)
             {
                 int read = is.read(buf);
                 if (read == -1)
                     break;
                 os.write(buf, 0, read);
             }
             os.flush();
             return String.format("%s%s%s", url,"/writerActivity/"+usernumber+"/",String.format("%s%s.%s","avatar",creatTime.format(dateTimeFormatter),image.getOriginalFilename().split("\\.")[1]));
         }
         catch (Exception ex)
         {
             return null;
         }
     }
     private String uploadContentMaterials(String path,String  content, String usernumber,String url)throws Exception
     {
         LocalDateTime creatTime = LocalDateTime.now();
         File imgeFile = Paths.get(path, "writerActivity").toFile();
         if (!imgeFile.exists() && !imgeFile.isDirectory())
         {
             imgeFile.mkdir();
         }
         File file = Paths.get(imgeFile.getAbsolutePath(), usernumber).toFile();
         if (!file.exists() && !file.isDirectory())
         {
             file.mkdir();
         }
         
         try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Paths.get(path, "writerActivity",usernumber,String.format("%s%s.%s","typewrite",creatTime.format(dateTimeFormatter),"html")).toFile()),Charset.forName("utf-8"))))
                 
         {
             writer.write("<html>\r\n");
             writer.write("<head>\r\n");
             writer.write("<meta charset=\"UTF-8\">\r\n");
             writer.write("<title>Document</title>\r\n");
             writer.write("</head>\r\n");
             writer.write("<body>\r\n");
             writer.write(content);
             writer.write("</body>\r\n");
             writer.write("</html>\r\n");
         }
         catch (Exception ex)
         {
             throw ex;
         }
         StringWriter text = new StringWriter();
         JSONWriter writer = new JSONWriter(text);
         writer.array();
         writer.object();
         writer.key("image_url").value(String.format("%s%s%s%s",url,"/writerActivity/",usernumber+"/",String.format("%s%s.%s","typewrite",creatTime.format(dateTimeFormatter),"html")));
         writer.endObject();
         writer.endArray();
         return text.toString();
     }
     private String uploadFileMaterials(String path,MultipartFile[] image, String usernumber,String url)
     {
         LocalDateTime creatTime = LocalDateTime.now();
         File imgeFile = Paths.get(path, "writerActivity").toFile();
         if (!imgeFile.exists() && !imgeFile.isDirectory())
         {
             imgeFile.mkdir();
         }
         File file = Paths.get(imgeFile.getAbsolutePath(), usernumber).toFile();
         if (!file.exists() && !file.isDirectory())
         {
             file.mkdir();
         }
         List<String> list = new ArrayList<>();
         int count = 0;
         for (MultipartFile multipartFile : image)
         {
            try (InputStream is = multipartFile.getInputStream();
                 OutputStream os = new FileOutputStream(Paths.get(path, "writerActivity",usernumber,String.format("%s%d%s.%s","production",count,creatTime.format(dateTimeFormatter),multipartFile.getOriginalFilename().split("\\.")[1])).toFile()))
                                 
            {
                byte[] buf = new byte[2048];
                while (true)
                {
                    int read = is.read(buf);
                    if (read == -1)
                        break;
                    os.write(buf, 0, read);
                }
                os.flush();
                System.out.println("--->"+String.format("%s%s%s%s",url,"/writerActivity/",usernumber+"/",String.format("%s%d%s.%s","production",count,creatTime.format(dateTimeFormatter),multipartFile.getOriginalFilename().split("\\.")[1])));
                list.add(String.format("%s%s%s%s",url,"/writerActivity/",usernumber+"/",String.format("%s%d%s.%s","production",count,creatTime.format(dateTimeFormatter),multipartFile.getOriginalFilename().split("\\.")[1])));
                count++;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return null;
            }
           
         }
         
         StringWriter text = new StringWriter();
         JSONWriter writer = new JSONWriter(text);
         writer.array();
         for (String imgURL : list)
         {
             System.out.println("imgURL:"+imgURL);
             writer.object();
             writer.key("image_url").value(imgURL);
             writer.endObject();
         }
         writer.endArray();
         return text.toString();
     }
     
     
     private String toJSONString(ActivityParticipant activityParticipant)
     {
         StringWriter text = new StringWriter();
         JSONWriter json = new JSONWriter(text);
         json.object();
         if (activityParticipant != null)
         {
            json.key("id").value(activityParticipant.id);
            json.key("usernumber").value(activityParticipant.userNumber);
            json.key("activityId").value(activityParticipant.activityId);
            json.key("avatarUrl").value(activityParticipant.avatarUrl);
            json.key("dislikes").value(activityParticipant.dislikes);
            json.key("likes").value(activityParticipant.likes);
            json.key("instructor").value(activityParticipant.instructor);
            json.key("slogan").value(activityParticipant.slogan);
            json.key("status").value(activityParticipant.status);
            json.key("submissionId").value(activityParticipant.submissionId);
            json.key("votes").value(activityParticipant.votes);
         }
         json.endObject();
         return text.toString();
     }
     
     private AppActivityMaterials parseAppUploadMaterial(String resource)
     {
         AppActivityMaterials appActivityMaterials = new AppActivityMaterials();
         JSONObject json = new JSONObject(resource);
         appActivityMaterials.setParticipantId(json.getInt("participantId"));
         appActivityMaterials.setTitle(json.getString("title"));
         appActivityMaterials.setUsernumber(json.getString("usernumber"));
         appActivityMaterials.setWorks(json.getString("works"));
         return appActivityMaterials;
     }
     public String getAddr(HttpServletRequest request) 
     {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
        {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
        {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
        {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
     
    private String toRegisterJSONString(int status,String msg)
    {
        StringWriter text = new StringWriter();
        JSONWriter writer = new JSONWriter(text);
        writer.object();
        writer.key("result").value(status);
        writer.key("cause").value(msg);
        writer.endObject();
        return text.toString();
    }
    
    /**
     * 提示信息
     * @param status 状态码
     * @param msg 消息提示
     * @param error 错误信息（开发人员信息）
     * @return json object
     */
    private String toMsgJSONString(int status, String msg , String error)
    {
        StringWriter text = new StringWriter();
        JSONWriter writer = new JSONWriter(text);
        writer.object();
        writer.key("status").value(status);
        writer.key("msg").value(msg);
        writer.key("error").value(error);
        writer.endObject();
        return text.toString();
    }
}
