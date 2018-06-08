package com.tsinghuadtv.www.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tsinghuadtv.www.dao.ISysUserDao;
import com.tsinghuadtv.www.entity.TComment;
import com.tsinghuadtv.www.entity.Video;
import com.tsinghuadtv.www.util.Constants;
import com.tsinghuadtv.www.util.verificode.RandomValidateCode;
import java.io.StringWriter;
import org.json.JSONWriter;

@Controller
public class ToolController {
	@Resource
	HttpServletRequest request;
	@RequestMapping("/topage")
	public String toPage(@RequestParam String pagename){
		System.out.println(pagename+"==================");
		return pagename;
//		return "/WEB-INF/jsp/" + pagename + ".jsp";
	}
	@Resource
	ISysUserDao dao ;
	@Resource
	RandomValidateCode code;
	@RequestMapping("/vcode")
	public void vcode(HttpServletRequest request, HttpServletResponse response) {
		code.getRandcode(request, response);
	}	
	
	@RequestMapping("/checkcode")
	public String checkcode(HttpServletRequest request, HttpServletResponse response, @RequestParam String vcode){
		
		String sessionCode = (String)request.getSession().getAttribute(Constants.RANDOM_CODE_KEY);
		if (vcode.equals(sessionCode)) {
			request.setAttribute("error", "yeahhhhhhhhhhhhhh验证码正确");
			return "login";
		} else {
			request.setAttribute("error", "验证码错误");
			return "login";
		}
		
	}	
	
	// load 视频信息 +评论 mysql分页   
    @RequestMapping(value="/video/selectById", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  String getVideo(@RequestParam("videoId") int videoId,
                            @RequestParam("currentPage") int currentPage,
                            @RequestParam("pageSize") int pageSize)
    {
        try
        {
            System.out.println("videoId:"+videoId);
            System.out.println("currentPage:"+currentPage);
            System.out.println("pageSize:"+pageSize);
            Video video = dao.getVideoById(videoId);
            if (video == null)
                return toVideoJSONString(videoId, 
                                        0, 
                                        String.format("get video %d failed", videoId),
                                        null,
                                        null, 
                                        null, 
                                        null, 
                                        0, 
                                        0,
                                        0, 
                                        0, 
                                        null);
            int totalCount = dao.findInfoRowCount(String.valueOf(videoId));
            int pageSizes = pageSize <=0?10:pageSize;
            int totalPage = ((totalCount%pageSizes)==0)?(totalCount/pageSizes):(totalCount/pageSizes+1);
            int pageNumber = currentPage<=0?1:currentPage;
            System.out.println("totalCount:"+totalCount);
            System.out.println("pageSizes:"+pageSizes);
            System.out.println("totalPage:"+totalPage);
            System.out.println("pageNumber:"+pageNumber);
            List<TComment> list = dao.getregList(String.valueOf(videoId), pageNumber, pageSizes);
            String string =  toVideoJSONString(videoId, 1, null, video.getName(), video.getLocation(),
                                     video.getPoster(), video.getContent(), pageNumber, totalCount, totalPage,
                                     pageSizes, list);
            System.out.println("string:"+string);
            return string;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return toVideoJSONString(videoId, 
                                        0, 
                                        ex.getMessage(),
                                        null,
                                        null, 
                                        null, 
                                        null, 
                                        0, 
                                        0,
                                        0, 
                                        0, 
                                        null);
        }
    }
    
    // load 视频评论列表
    @RequestMapping(value="/video/listCommentByVideoId", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  String listCommentByVideoId(@RequestParam("videoId") int videoId,
                                        @RequestParam("currentPage") int currentPage,
                                        @RequestParam("pageSize") int pageSize) 
    {
    	try
        {
            int pageSizes = pageSize <=0?10:pageSize;
            int pageNumber = currentPage<=0?1:currentPage;
            List<TComment> list = dao.getregList(String.valueOf(videoId), pageNumber, pageSizes);
            StringWriter text = new StringWriter();
            JSONWriter writer = new JSONWriter(text);
            writer.array();
            if (list != null)
            {
                for (TComment comment : list)
                {
                    writer.object();
                    writer.key("id").value(comment.id);
                    writer.key("userid").value(comment.userid);
                    writer.key("videoid").value(comment.videoid);
                    writer.key("username").value(comment.username);
                    writer.key("content").value(comment.content);
                    writer.key("createtime").value(comment.createtime);
                    writer.endObject();
                }
            }
            writer.endArray();
            return text.toString();
        }
        catch(Exception ex)
        {
            return toMSGJSONString(0, ex.getMessage());
        }
    }
    
    @RequestMapping("/video/listRelatedVideos")
    public  @ResponseBody List<Map<String, Object>> listRelatedVideos(Integer id) throws Exception {
		Video video = dao.getVideoById(id);
		String type = video.getType();
		System.out.println(type);
		return dao.listVideoByType(type);
    }
    
	@RequestMapping(value = "/sb1/doLogin", method = RequestMethod.POST)
	public String doLogin(@RequestParam String userName, @RequestParam String password,HttpSession session) {
		
		try {
//			User user = service.doLogin(userName, password);
			session.setAttribute("username","admin");
			ServletContext ContextA =session .getServletContext();  
			ContextA.setAttribute("session", session );  
			return "redirect:http://localhost/springmvcInterceptor/userhome.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "http://localhost/ENSO/WEB-INF/jsp/login.jsp";
		}
	}
	
	@RequestMapping("/sb/logout")
	public String logout(){
	     HttpSession sessionB = request.getSession();    
	     ServletContext ContextB = sessionB.getServletContext();    
	     ServletContext ContextA= ContextB.getContext("/ENSO");// 这里面传递的是 WebappA的虚拟路径  
	     HttpSession sessionA =(HttpSession)ContextA.getAttribute("session");  
		 sessionA.removeAttribute("username");
		 sessionA.removeAttribute("userid");
		 return "redirect:http://localhost/springmvcInterceptor/login.jsp";
	}
    /**
     * 
     * @param videoId  视频id
     * @param result  
     * @param cause 
     * @param videoname
     * @param videoURL
     * @param poster
     * @param videointroduce
     * @param currentPage
     * @param totalCount
     * @param totalPage
     * @param pageSize
     * @param list
     * @return 
     */
    private String toVideoJSONString(int    videoId,
                                     int    result,
                                     String cause,
                                     String videoname,
                                     String videoURL,
                                     String poster,
                                     String videointroduce,
                                     int currentPage,
                                     int totalCount,
                                     int totalPage,
                                     int pageSize,
                                     List<TComment> list)
    {
        System.out.println("videoId:"+videoId);
        System.out.println("videoname:"+videoname);
        System.out.println("videoURL:"+videoURL);
        System.out.println("poster:"+poster);
        StringWriter text = new StringWriter();
        JSONWriter writer = new JSONWriter(text);
        writer.object();
        writer.key("videoId").value(videoId);
        writer.key("result").value(result);
        writer.key("cause").value(cause);
        writer.key("videoname").value(videoname);
        writer.key("videoURL").value(videoURL);
        writer.key("poster").value(poster);
        writer.key("videointroduce").value(videointroduce);
        writer.key("currentPage").value(currentPage);
        writer.key("totalCount").value(totalCount);
        writer.key("totalPage").value(totalPage);
        writer.key("pageSize").value(pageSize);
        writer.key("list").array();
        if (list != null)
        {
            for (TComment comment : list)
            {
                writer.object();
                writer.key("id").value(comment.id);
                writer.key("userid").value(comment.userid);
                writer.key("videoid").value(comment.videoid);
                writer.key("username").value(comment.username);
                writer.key("content").value(comment.content);
                writer.key("createtime").value(comment.createtime);
                writer.endObject();
            }
        }
        writer.endArray();
        writer.endObject();
        
        return text.toString();
    }
	private String toMSGJSONString(int status,String msg)
    {
        StringWriter text = new StringWriter();
        JSONWriter writer = new JSONWriter(text);
        writer.object();
        writer.key("result").value(status);
        writer.key("cause").value(msg);
        writer.endObject();
        return text.toString();
    }	
}
