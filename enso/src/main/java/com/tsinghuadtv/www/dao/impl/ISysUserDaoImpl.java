package com.tsinghuadtv.www.dao.impl;

import com.tflx.enso.service.activity.ActivityManagement;
import com.tflx.enso.service.activity.json.ActivityParticipant;
import com.tflx.enso.service.activity.json.QualifiedParticipant;
import com.tflx.enso.service.activity.json.SchoolActivity;
import com.tflx.enso.service.bonus.BonusManagement;
import com.tflx.enso.service.bonus.json.BonusAccount;
import com.tflx.enso.service.bonus.json.BonusSettlement;
import com.tflx.enso.service.trace.TraceManagement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.tsinghuadtv.www.dao.ISysUserDao;
import com.tsinghuadtv.www.entity.BigEvent;
import com.tsinghuadtv.www.entity.Comment;
import com.tsinghuadtv.www.entity.EventRowMapper;
import com.tsinghuadtv.www.entity.Student;
import com.tsinghuadtv.www.entity.StudentRowMapper;
import com.tsinghuadtv.www.entity.TComment;
import com.tsinghuadtv.www.entity.UserInfo;
import com.tsinghuadtv.www.entity.Video;
import com.tsinghuadtv.www.entity.VideoRowMapper;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ISysUserDaoImpl implements ISysUserDao {

	@Resource
	JdbcTemplate jdbcTemplate;

    private ActivityManagement activityManagement;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
    private synchronized ActivityManagement getActivityManager()
    {
        if (activityManagement == null)
            activityManagement = new ActivityManagement(jdbcTemplate.getDataSource());
        return activityManagement;
    }
    
    private BonusManagement bonusManagement;
    
    private synchronized BonusManagement getBonusManagement()
    {
        if (bonusManagement == null)
            bonusManagement = new BonusManagement(jdbcTemplate.getDataSource());
        return bonusManagement;
    }
    
    private TraceManagement traceManagement;
    
    private synchronized TraceManagement getTraceManagement()
    {
        if (traceManagement == null)
            traceManagement = new TraceManagement(jdbcTemplate.getDataSource());
        return traceManagement;
    }
	public int save(Student student) throws Exception {
		final String sql = "insert into t_student(USERNUMBER,USERNAME,PASSWORD,TELEPHONE,EMAIL,SCHOOL,area, GRADE,CLASS,REALNAME,IDCRADNO,SALT,VERSION,CREATEDATE,type) "
				         + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?)";
		return jdbcTemplate.update(sql,
				new Object[] { student.getUsernumber(),student.getUsername(), student.getPwd(), student.getMobile(), student.getEmail(),
						student.getSchool(), student.getArea(), student.getGrade(), student.getClassname(), student.getRealname(),
						student.getIdnum(), student.getSalt(), student.getVersion(),student.getType() });
	}

	public Student selectByUserNo(String username) throws Exception {
		String sql = "select USERNUMBER, USERNAME, PASSWORD,SALT,SCHOOL, area,type from t_student where USERNAME=?";
		final Student student = new Student();
		jdbcTemplate.query(sql, new Object[] { username }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				student.setUsernumber(rs.getString("USERNUMBER"));
				student.setUsername(rs.getString("USERNAME"));
				student.setPwd(rs.getString("PASSWORD"));
				student.setSalt(rs.getString("SALT"));
				student.setSchool(rs.getString("SCHOOL"));
				student.setArea(rs.getString("area"));		
                student.setType(rs.getString("type"));
			}
		});
		return student;
	}

	@Override
	public int selectMobile(String mobile) throws Exception {
        String sql="select count(TELEPHONE) from t_student where TELEPHONE=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {mobile}, Integer.class);
	}
	
	// 通过手机号找到用户
	public Student selectByUserMobile(String phone) throws Exception {
		String sql = "select USERNUMBER, USERNAME, PASSWORD,SALT, SCHOOL, area, type from t_student where telephone=?";
		final Student student = new Student();
		jdbcTemplate.query(sql,  new Object[] {phone}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				student.setUsernumber(rs.getString("USERNUMBER"));
				student.setUsername(rs.getString("USERNAME"));
				student.setPwd(rs.getString("PASSWORD"));
				student.setSalt(rs.getString("SALT"));
				student.setSchool(rs.getString("SCHOOL"));
				student.setArea(rs.getString("area"));
                student.setType(rs.getString("type"));
			}
		});		
		return student;
	}

	/**
	 * 检查用户名是否已经存在? >0 : 0 
	 */
	@Override
	public int selectUsername(String username) throws Exception {
        String sql="select count(username) from t_student where username=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {username}, Integer.class);
	}

	@Override
	public Student selectByUsername(String username) throws Exception {
		String sql = "SELECT * from t_student where USERNAME=?";				
		return jdbcTemplate.queryForObject(sql, new Object[] {username}, new StudentRowMapper());
	}

	@Override
	public Student selectByUserid(String userid) throws Exception {
		String sql="SELECT * from t_student where USERNUMBER=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {userid}, new StudentRowMapper());
	}

	@Override
	public int createComment(Comment comment) throws Exception {
		final String sqlc = "insert into t_comment(userid,content,videoid,createtime,username) "
		         + "values (?,?,?,now(),?)";
	    return jdbcTemplate.update(sqlc,new Object[] {comment.getUserid(),comment.getContent(),comment.getVideoid(),comment.getUsername()});	
	}

	@Override
	public int deleteComment(Integer commentId) throws Exception {
		String sql="delete from t_comment where id=?";
		return jdbcTemplate.update(sql,new Object[] {commentId});
	}

	// TODO: 检查手机号, 用户名 与其他用户重复
	@Override
	public int updateUser(Student student) throws Exception {
		String sql="update t_student set USERNAME=?,TELEPHONE=?,EMAIL=?,SCHOOL=?,GRADE=?,CLASS=?,"
				+"REALNAME=?,IDCRADNO=?,VERSION=?, area=? WHERE USERNUMBER=?";		
		
		System.out.println(student.getUsernumber());
		
		return jdbcTemplate.update(sql, new PreparedStatementSetter() {			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, student.getUsername());
                ps.setString(2, student.getMobile());
                ps.setString(3, student.getEmail());
                ps.setString(4, student.getSchool());  
                ps.setString(5, student.getGrade()); 
                ps.setString(6, student.getClassname()); 
                ps.setString(7, student.getRealname());  
                ps.setString(8, student.getIdnum());
                ps.setString(9, student.getVersion());
                ps.setString(10, student.getArea());
                ps.setString(11, student.getUsernumber());
			}  
        });
	}

	@Override
	public void updatePassword(Student student) throws Exception {
		String sql="update  t_student set PASSWORD=? where USERNUMBER=?";
		jdbcTemplate.update(sql, new Object[]{student.getPwd(),student.getUsernumber()});
	}

	@Override
	public void saveEvent(BigEvent bigEvent) throws Exception {
		String sql="insert into t_event(userid,title,content,imageFile,img,createtime) values(?,?,?,?,?,?)";
		java.sql.Date date = bigEvent.getCreateDate();
		String ds = new String();		
		
		if (null == date) {
			// 生成当天日期string "2017-09-29"		  
		    LocalDateTime now = LocalDateTime.now();
		    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    ds = dtf.format(now);
		} else {
			ds = date.toString();
		}
		jdbcTemplate.update(sql,new Object[]{bigEvent.getUserid(),bigEvent.getTitle(),
				bigEvent.getContent(),bigEvent.getImageFile(),bigEvent.getImg(), ds});
	}
	
	public boolean eventExists(BigEvent bigEvent) throws Exception {
		final String sql = "select count(id) from t_event where userid=? and createtime=?";
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");	
		int count = jdbcTemplate.queryForObject(sql, 
				new Object[]{bigEvent.getUserid(), myFmt.format(bigEvent.getCreateDate())}, Integer.class);
		return count > 0;
	}
	
	public int updateEvent(BigEvent bigEvent) throws Exception {
		final String sql="update t_event set title=?,content=?,imageFile=?,img=? where userid=? and createtime=?";
		return jdbcTemplate.update(sql, new PreparedStatementSetter() {			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, bigEvent.getTitle());
                ps.setString(2, bigEvent.getContent());
                ps.setString(3, bigEvent.getImageFile());
                ps.setString(4, bigEvent.getImg());
                ps.setString(5, bigEvent.getUserid());
                ps.setDate(6,  bigEvent.getCreateDate());
			}  
        });
	}

	@Override
	public List<BigEvent> getEventByUserid(String userid) throws Exception {		
		String sql ="select * from t_event where userid=?";
		return jdbcTemplate.query(sql, new Object[]{userid}, new EventRowMapper());
	}

	/**
	 * 取得视频id 对应的评论列表
	 */
    public List<TComment> getregList(String id, int currentPage, int pageSize)throws Exception 
    {
        StringBuffer sb = new StringBuffer();
        if(!"".equals(id) ) {
            sb.append(" where  1=1 "); 
            if(!"".equals(id)){
                sb.append("and videoid = "+id+"");
            }     
        }
        currentPage = currentPage < 1 ? 1:currentPage;
        // 评论倒序排列
        final String sql = "select * from t_comment "+sb.toString()+" order by ID desc LIMIT "+(currentPage-1)*pageSize+","+pageSize+"";
        List<Map<String,Object>> query = jdbcTemplate.queryForList(sql);
        if (query != null && !query.isEmpty())
        {
            List<TComment> list = new ArrayList<>();
            for (Map<String,Object> map : query)
            {
                TComment comment = new TComment();
                comment.id = (int)map.get("id");
                comment.userid = (String)map.get("userid");
                comment.username = (String)map.get("username");
                comment.videoid = (int)map.get("videoid");
                comment.content = (String)map.get("content");
                comment.createtime = ((Timestamp)map.get("createtime"))
                                                    .toLocalDateTime()
                                                    .format(dateTimeFormatter);
                list.add(comment);
            }
            return list;
        }
        else
        {
            return null;
        }
    }
    
    // 视频id => comment count => pagination
	public int findInfoRowCount(String id) throws Exception {
        String sql = "select *  from t_comment where videoid=?"; // +" order by id desc";         

        List<Map<String,Object>> query = jdbcTemplate.queryForList(sql, id);
        if (query != null && !query.isEmpty())
        {
            return query.size();
        }
        else
        {
            return 0;
        }
    }
    
    public Video getVideoById(Integer id) throws Exception 
    {        	
        try
        {
            String sql ="select * from t_video where id=?";
            List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, id);
            if (list != null && !list.isEmpty())
            {
                Map<String,Object> map = list.get(0);
                Video video = new Video();
                video.setId((int)map.get("id"));
                video.setName((String)map.get("name"));
                video.setContent((String)map.get("content"));
                video.setLocation((String)map.get("location"));
                video.setPoster((String)map.get("poster"));
                video.setType((String)map.get("type"));
                return video;
            }
            else
            {
                return null;
            }
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
	@Override
	public List<Map<String, Object>> listVideoByType(String type) throws Exception {
		final String sql ="select * from t_video where type=?";		
		return jdbcTemplate.queryForList(sql, type);
	}
    
    // 根据用户的手机号重置密码
    public int resetPwdByMobile(String mobile, String ciphertext, String salt) throws Exception {
    	final String sql = "update t_student set password=?, salt=? where telephone=?";
    	
		return jdbcTemplate.update(sql, 
				new Object[] { ciphertext, salt, mobile },
				new int[] {java.sql.Types.CHAR, java.sql.Types.VARCHAR, java.sql.Types.CHAR}
		);
    }
    
    @Override
    public BonusAccount queryBonusAmountById(String usernumber) throws Exception
    {
        try
        {
//            BonusManagement bm = new BonusManagement(jdbcTemplate.getDataSource());
            List<BonusAccount> list = getBonusManagement().getUserAccounts(usernumber);
            if (list != null && !list.isEmpty())
            {
                return list.stream().filter(b->(b.type.equals("积分"))).collect(Collectors.toList()).get(0);
            }
            return null;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public List<BonusSettlement> queryBonusSettlementList(int accountId,
                                                          int pageSize, 
                                                          int pageNumber) throws Exception
    {
        try
        {
            pageSize  = pageSize   <= 0 ? 10:pageSize;
            pageNumber= pageNumber <= 0 ? 1 :pageNumber;
//            BonusManagement bm = new BonusManagement(jdbcTemplate.getDataSource());
            return getBonusManagement().getAccountSettlements(accountId, Optional.empty(), Optional.empty(),Optional.empty(), pageNumber, pageSize);
                    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public UserInfo queryUserInfoByIndentifier(String identifier, boolean flag)
                                                                throws Exception
    {
        try 
        {
            String selectByUsername = "select * from t_student where username=?";
            String selectByTelephone= "select * from t_student where telephone=?";
            String sql = flag?selectByTelephone:selectByUsername;
            System.out.println("sql:"+sql);
            List<Map<String,Object>> query = jdbcTemplate.queryForList(sql, identifier);
            UserInfo userInfo = new UserInfo();
            if (query.isEmpty())
                return null;
            Map<String,Object> map = query.get(0);
            userInfo.usernumber = (String)map.get("usernumber");
            userInfo.username = (String)map.get("username");
            userInfo.password = (String)map.get("password");
            userInfo.telephone = createDefaultValue((String)map.get("telephone"));
            userInfo.email = createDefaultValue((String)map.get("email"));
            userInfo.school = createDefaultValue((String)map.get("school"));
            userInfo.area = createDefaultValue((String)map.get("area"));
            userInfo.grade = createDefaultValue((String)map.get("grade"));
            userInfo.classes = createDefaultValue((String)map.get("class"));
            userInfo.realname = createDefaultValue((String)map.get("realname"));
            userInfo.idcradno = createDefaultValue((String)map.get("idcradno"));
            userInfo.salt = createDefaultValue((String)map.get("salt"));
            userInfo.version = createDefaultValue((String)map.get("version"));
            userInfo.createTime = ((Timestamp)map.get("createdate")).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            userInfo.type = (String)map.get("type");
            return userInfo;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    @Override
    public SchoolActivity querySchoolActivity(int id)throws Exception
    {
        try
        {
//            ActivityManagement activityManagement = new ActivityManagement(jdbcTemplate.getDataSource());
            return getActivityManager().getActivity(id);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    @Override
    public ActivityParticipant insertActivityParticipants(ActivityParticipant activityParticipant)throws Exception
    {
        try
        {
            return getActivityManager().registerActivityParticipant(activityParticipant.activityId, 
                                                                  activityParticipant.userNumber,
                                                                  activityParticipant.avatarUrl, 
                                                                  activityParticipant.slogan, 
                                                                  activityParticipant.instructor);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public int initBonusAccount(String usernumber, String type) throws Exception
                                
    {
//        BonusManagement bonusManagement = new BonusManagement(jdbcTemplate.getDataSource());
        return getBonusManagement().registerUserBonusAccount(usernumber, type);
    }

    @Override
    public Student queryStudentInfoByUsername(String username) throws Exception
    {
        try
        {
            String sql = "select * from t_student where username=?";
            List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, username);
            if (list == null || list.isEmpty())
                return null;
            Student student = new Student();
            Map<String,Object> map = list.get(0);
            student.setUsername((String)map.get("username"));
            student.setUsernumber((String)map.get("usernumber"));
            student.setType((String)map.get("type"));
            return student;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public boolean uploadFileMaterials(int participantId,String title,String works) throws Exception
    {
        try
        {
//            ActivityManagement activityManagement = new ActivityManagement(jdbcTemplate.getDataSource());
            getActivityManager().submitActivityWorks(participantId, title, works);
            return true;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public ActivityParticipant queryActivityParticipantsById(int id) throws Exception
    {
        try
        {
//            ActivityManagement activityManagement = new ActivityManagement(jdbcTemplate.getDataSource());
            ActivityParticipant activityParticipant =getActivityManager().getActivityParticipant(id);
            return activityParticipant;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public void updateActivityParticipantsById(ActivityParticipant activityParticipant) throws Exception
    {
        try
        {
//            ActivityManagement activityManagement = new ActivityManagement(jdbcTemplate.getDataSource());
//            getActivityManager().updateParticipant(activityParticipant);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public ActivityParticipant queryActivityParticipantsByActivityIdAndUsernumber(int activityId,String usernumber)
                                                                                  throws Exception
    {
        try
        {
//            ActivityManagement activityManagement = new ActivityManagement(jdbcTemplate.getDataSource());
            return getActivityManager().getActivityParticipant(activityId,usernumber);
                    
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    
    public List<QualifiedParticipant> queryStudentActivityWorks(String area , int count)throws Exception
    {
        try
        {
//            ActivityManagement activityManagement = new ActivityManagement(jdbcTemplate.getDataSource());
//            return activityManagement.listLatestQualifiedParticipants(count);
            return getActivityManager().listLatestQualifiedParticipants(area, count);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    
    public QualifiedParticipant queryStudentActivityWorksById(int id)throws Exception
    {
        try
        {
//            ActivityManagement activityManagement = new ActivityManagement(jdbcTemplate.getDataSource());
             
            return getActivityManager().getQualifiedParticipant(id);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    } 
    
    public BonusSettlement changeBonus(int accountId, String accountType, int accountAmount,String title, String comment )throws Exception
    {
        try
        {
//            BonusManagement bonusManagement = new BonusManagement(jdbcTemplate.getDataSource());
            BonusSettlement bonusSettlement = getBonusManagement().changeBonusAccount(accountId, accountType, accountAmount, title, comment);
            return bonusSettlement;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public int checkUserBalance(String usernumber)throws Exception
    {
        try
        {
//           BonusManagement bonusManagement = new BonusManagement(jdbcTemplate.getDataSource());
           return getBonusManagement().checkUserBalance(usernumber);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public int checkAccountBalance(int accountId)throws Exception
    {
        try
        {
//           BonusManagement bonusManagement = new BonusManagement(jdbcTemplate.getDataSource());
           return getBonusManagement().checkAccountBalance(accountId);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
   
    public BonusAccount queryBonusAccountById(int id)throws Exception
    {
        try
        {
//            BonusManagement bonusManagement = new BonusManagement(jdbcTemplate.getDataSource());
            return getBonusManagement().getAccount(id);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public void recordLogger(String userIdentity,
                             String userAddress,
                             String userAgent,
                             String accessUri,
                             String accessContext,
                             String accessStatus,
                             String accessSource)throws Exception
    {
        try
        {
            
            getTraceManagement().createAccessTrace(userIdentity, userAddress,userAgent, accessUri, accessContext, accessStatus,accessSource);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public int queryLoggerByConditional(String usernumber, 
                                        String uri,
                                        String status,
                                        LocalDate localDate)throws Exception
                                           
    {
//        TraceManagement traceManagement = new TraceManagement(jdbcTemplate.getDataSource());
        return getTraceManagement().getDailyAccessTraceCount(usernumber, uri, status,localDate);
                
    }
    
    
    public int addLikes(int id)throws Exception
    {
        try
        {
//            ActivityManagement activityManagement = new ActivityManagement(jdbcTemplate.getDataSource());
            return getActivityManager().addParticipantLikes(id);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public int addVotes(int id)throws Exception
    {
        try
        {
//            ActivityManagement activityManagement = new ActivityManagement(jdbcTemplate.getDataSource());
            return getActivityManager().addParticipantVotes(id);
            
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public boolean uploadAvatarByParticipantId(int participantId, String avatarUrl)throws Exception
    {
        try
        {
//            getActivityManager().updateParticipantAvatar(0, avatarUrl);
            return true;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public void updateCustomerInfo(Student student)
    {
        //IDCRADNO
        String sql = "update t_student set username=?,telephone=?,email=?,school=?,area=?,grade=?,class=?,realname=?,version=?,type=?,IDCRADNO=? where usernumber=?";
        jdbcTemplate.update(sql, student.getUsername(),
                                 student.getMobile(),
                                 student.getEmail(),
                                 student.getSchool(),
                                 student.getArea(),
                                 student.getGrade(),
                                 student.getClassname(),
                                 student.getRealname(),
                                 student.getVersion(),
                                 student.getType(),
                                 student.getIdnum(),
                                 student.getUsernumber());
            
    }

    @Override
    public boolean selectStudentInfoByUsernumberAndUsername(
                                                            String usernumber, 
                                                            String username) throws Exception
    {
        try
        {
            String sql = "select * from t_student where usernumber !=? and username=?";
            List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, usernumber,username);
            if (list != null && !list.isEmpty())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public boolean selecStudentInfoByUsernumberAndTelephone(
            String usernumber, String telephone) throws Exception
    {
        String sql ="select * from t_student where usernumber !=? and telephone=?";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, usernumber,telephone);
        if (list != null && !list.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public List<ActivityParticipant> queryActivityParticipantStudentsInfo(int activityId,boolean flag, int pageNum, int pageSize)throws Exception
    {
        try
        {
            return getActivityManager().listActivityParticipants(activityId, flag, pageNum, pageSize);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public List<Student> queryStudents() throws Exception
    {
        try
        {
            List<Student> list = new ArrayList<>();
            String sql = "select * from t_student where type=?";
            List<Map<String,Object>> query = jdbcTemplate.queryForList(sql, "学生");
            if (query != null && !query.isEmpty())
            {
                for (Map<String,Object> map : query)
                {
                    Student student = new Student();
                    student.setUsernumber((String)map.get("usernumber"));
                    student.setClassname(((String)map.get("CLASS")));
                    student.setGrade((String)map.get("GRADE"));
                    student.setRealname((String)map.get("REALNAME"));
                    student.setType((String)map.get("type"));
                    student.setArea((String)map.get("area"));
                    student.setSchool((String)map.get("SCHOOL"));
                    student.setUsername((String)map.get("username"));
                    list.add(student);
                }

            }
            return list;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public List<QualifiedParticipant> queryActivityParticipantsBySchool(String school, int pageNumber,int pageSize)throws Exception
    {
        try
        {
            return getActivityManager().listQualifiedParticipantsBySchool(school, pageNumber, pageSize);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public Student selectStudentInfoByUsernumber(String usernumber)throws Exception
    {
        try
        {
            Student student = new Student();
            String sql = "select * from t_student where usernumber=?";
            List<Map<String,Object>> query = jdbcTemplate.queryForList(sql, usernumber);
            if (query !=null && !query.isEmpty())
            {
                Map<String,Object> map = query.get(0);
                student.setArea((String)map.get("area"));
                student.setClassname((String)map.get("class"));
                student.setType((String)map.get("type"));
                student.setGrade((String)map.get("grade"));
                student.setRealname((String)map.get("realname"));
                student.setSchool((String)map.get("school"));
                student.setUsernumber((String)map.get("usernumber"));
                student.setUsername((String)map.get("username"));
                return student;
            }
            else
            {
                return student;
            }
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public boolean selectCustomerInfoByUsername(String username) throws
                                                                        Exception
    {
        try
        {
            String sql = "select * from t_student where username=?";
            List<Map<String,Object>> query = jdbcTemplate.queryForList(sql,username);
            if (query != null && !query.isEmpty())
                return true;
            else
                return false;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public boolean selectCustomerInfoByTelephone(String telephone) throws
                                                                          Exception
    {
        try
        {
            String sql = "select * from t_student where TELEPHONE=?";
            List<Map<String,Object>> query = jdbcTemplate.queryForList(sql,telephone);
            if (query != null && !query.isEmpty())
            {
                return true;
            }
            else
            {
                return false;
            }
                    
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public List<ActivityParticipant> listActivityParticipants(int activityId, 
                                         String school, 
                                         String grade, 
                                         String classname,
                                         String realname,
                                         int pageSize,
                                         int pageNumber)throws Exception
    {
        return getActivityManager().listActivityParticipants(activityId, 
        													null,
                                                            Optional.ofNullable(school), 
                                                            Optional.ofNullable(grade),
                                                            Optional.ofNullable(classname), 
                                                            Optional.ofNullable(realname), 
                                                            0,
                                                            pageSize, 
                                                            pageNumber);
                
    }
    private String createDefaultValue(String value)
    {
        return (value == null || value.isEmpty())?"":value;
    }
}
