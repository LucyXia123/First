package com.tsinghuadtv.www.service.impl;

import com.tflx.enso.service.activity.json.ActivityParticipant;
import com.tflx.enso.service.activity.json.QualifiedParticipant;
import com.tflx.enso.service.activity.json.SchoolActivity;
import com.tflx.enso.service.bonus.json.BonusAccount;
import com.tflx.enso.service.bonus.json.BonusSettlement;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tsinghuadtv.www.dao.ISysUserDao;
import com.tsinghuadtv.www.entity.BigEvent;
import com.tsinghuadtv.www.entity.Comment;
import com.tsinghuadtv.www.entity.Student;
import com.tsinghuadtv.www.entity.UserInfo;
import com.tsinghuadtv.www.entity.Video;
import com.tsinghuadtv.www.service.ISysUserService;
import com.tsinghuadtv.www.util.Sequnce4StudentUtil;
import com.tsinghuadtv.www.util.encrypt.PasswordEncryption;
import java.time.LocalDate;

@Transactional	
@Service
public class SysUserServiceImpl implements ISysUserService {

	@Resource
	ISysUserDao dao;
	
    @Resource
    JdbcTemplate jdbcTemplate;

	public Student selectByUserName(String username)throws Exception {
       return dao.selectByUserNo(username);
	}
	
	public Student selectByPhone(String phone)throws Exception {
		return dao.selectByUserMobile(phone);
	}

	public void save(Student student) throws Exception {
//        String salt = PasswordEncryption.generateSalt();
//		String ciphertext = PasswordEncryption.getEncryptedPassword(student.getPwd(), salt);
//		student.setPwd(ciphertext);
//		student.setSalt(salt);
//		student.setUsernumber(Sequnce4StudentUtil.getMerchantSeq());
		int row = dao.save(student);
        System.out.println("row:"+row);
	}

	@Override
	public boolean selectMobile(String mobile) throws Exception {
		if(dao.selectMobile(mobile)==1)
			return true;
		return false;
	}

	/**
	 * 用户名是否存在
	 */
	@Override
	public boolean selectUsername(String username) throws Exception {
		if(dao.selectUsername(username) >= 1)
			return true;
		return false;
	}

	@Override
	public Student selectByUserid(String userid) throws Exception {
       return dao.selectByUserid(userid);
	}	

	@Override
	public void createComment(Comment comment) throws Exception {
		dao.createComment(comment);
	}

	@Override
	public void deleteComment(Integer commnetId) throws Exception {
		dao.deleteComment(commnetId);
	}

	@Override
	public void updateByUserid(Student student) throws Exception {
		String ciphertext = PasswordEncryption.getEncryptedPassword(student.getPwd(), student.getSalt());
		student.setPwd(ciphertext);
		dao.updatePassword(student);
	}
	
	public int updateUser(Student student) throws Exception {
		return dao.updateUser(student);
	}

	@Override
	public void saveEvent(BigEvent bigEvent) throws Exception {
		if (dao.eventExists(bigEvent)) {
			dao.updateEvent(bigEvent);
		} else {
			dao.saveEvent(bigEvent);
		}	
	}

	@Override
	public List<BigEvent> getEventByUserid(String userid) throws Exception {
		return dao.getEventByUserid(userid);
	}

	public int loginCallback(Object area) throws Exception {
		String s = (String)area;
		return jdbcTemplate.update("update t_online set count=count+1 where area=?", 
				new Object[] {s}, new int[] {java.sql.Types.VARCHAR});
	}

	@Override
	public int logoutCallback(Object area) throws Exception {
		String s = (String)area;
		return jdbcTemplate.update("update t_online set count=count-1 where area=?", 
				new Object[] {s}, new int[] {java.sql.Types.VARCHAR});
	}
	
	/**
	 * 生成len位的数字验证码
	 */
	public String gensmscode(int len) {
		int[] a = new int[len];
		int[] hash = new int[] {0,1,2,3,4,5,6,7,8,9};
		int n = hash.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append( hash[(int)(Math.random()*n)] );
		}
		return sb.toString();
	}
	
	public int resetPwdByMobile(String mobile, String password) throws Exception {
		String salt = PasswordEncryption.generateSalt();
		String ciphertext = PasswordEncryption.getEncryptedPassword(password, salt);
		
		return dao.resetPwdByMobile(mobile, ciphertext, salt);
	}
    @Override
    public BonusAccount queryBonusAmountById(String usernumber) throws Exception
    {
        return dao.queryBonusAmountById(usernumber);
    }

    @Override
    public List<BonusSettlement> queryBonusSettlementList(int accountId,
                                                            int pageSize, 
                                                            int pageNumber) throws Exception
    {
        return dao.queryBonusSettlementList(accountId, pageSize, pageNumber);
    }

    @Override
    public UserInfo queryUserInfoByIndentifier(String identifier, boolean flag)
                                                throws Exception
    {
        return dao.queryUserInfoByIndentifier(identifier, flag);
    }
    
    public SchoolActivity querySchoolActivity(int id)throws Exception
    {
        return dao.querySchoolActivity(id);
    }
    
    public ActivityParticipant insertActivityParticipants(ActivityParticipant activityParticipant)throws Exception
    {
        return dao.insertActivityParticipants(activityParticipant);
    }

    @Override
    public int initBonusAccount(String usernumber, 
                                    String type) throws Exception
                                    
    {
        return dao.initBonusAccount(usernumber, type);
    }

    @Override
    public Student queryStudentInfoByUsername(String username) throws Exception
    {
        return dao.queryStudentInfoByUsername(username);
    }
    
    public boolean uploadFileMaterials(int participantId,String title,String works)throws Exception
    {
        return dao.uploadFileMaterials(participantId, title, works);
    }
    public ActivityParticipant queryActivityParticipantsById(int id) throws Exception
    {
        return dao.queryActivityParticipantsById(id);
    }

    @Override
    public void updateActivityParticipantsById(ActivityParticipant activityParticipant) throws Exception
    {
        dao.updateActivityParticipantsById(activityParticipant);
    }

    @Override
    public ActivityParticipant queryActivityParticipantsByActivityIdAndUsernumber(
                                                            int activityId, String usernumber) throws Exception
    {
        return dao.queryActivityParticipantsByActivityIdAndUsernumber(activityId,usernumber);
    }

    @Override
    public List<QualifiedParticipant> queryStudentActivityWorks(String area, int count)
            throws Exception
    {
        return dao.queryStudentActivityWorks(area,count);
    }

    @Override
    public QualifiedParticipant queryStudentActivityWorksById(int id) throws
                                                                             Exception
    {
        return dao.queryStudentActivityWorksById(id);
    }

    @Override
    public BonusSettlement changeBonus(int accountId, String accountType,
            int accountAmount, String title, String comment) throws Exception
    {
        return dao.changeBonus(accountId, accountType, accountAmount, title,
                comment);
    }

    @Override
    public int checkUserBalance(String usernumber) throws Exception
    {
        return dao.checkUserBalance(usernumber);
    }

    @Override
    public int checkAccountBalance(int accountId) throws Exception
    {
        return dao.checkAccountBalance(accountId);
    }

    @Override
    public BonusAccount queryBonusAccountById(int id) throws Exception
    {
        return dao.queryBonusAccountById(id);
    }
    
    public void recordLogger(String userIdentity,
                             String userAddress,
                             String userAgent,
                             String accessUri,
                             String accessContext,
                             String accessStatus,
                             String accessSource)throws Exception
    {
        dao.recordLogger(userIdentity, userAddress, userAgent, accessUri,
                         accessContext, accessStatus, accessSource);
    }

    @Override
    public int queryLoggerByConditional(String usernumber, String uri,
            String status, LocalDate localDate) throws Exception
    {
        return dao.queryLoggerByConditional(usernumber, uri, status, localDate);
    }

    @Override
    public int addLikes(int id) throws Exception
    {
        return dao.addLikes(id);
    }

    @Override
    public int addVotes(int id) throws Exception
    {
        return dao.addVotes(id);
    }
    
    public boolean uploadAvatarByParticipantId(int participantId, String avatarUrl)throws Exception
    {
        return dao.uploadAvatarByParticipantId(participantId, avatarUrl);
    }

    @Override
    public void updateCustomerInfo(Student student) throws Exception
    {
        dao.updateCustomerInfo(student);
    }

    @Override
    public boolean selectStudentInfoByUsernumberAndUsername(String usernumber,
            String username) throws Exception
    {
        return dao.selectStudentInfoByUsernumberAndUsername(usernumber, username);
    }

    @Override
    public boolean selecStudentInfoByUsernumberAndTelephone(String usernumber,
            String telephone) throws Exception
    {
        return dao.selecStudentInfoByUsernumberAndTelephone(usernumber,
                telephone);
    }

    @Override
    public List<ActivityParticipant> queryActivityParticipantStudentsInfo(
            int activityId, boolean flag, int pageNum, int pageSize) throws
                                                                            Exception
    {
        return dao.queryActivityParticipantStudentsInfo(activityId, flag,pageNum, pageSize);
                
    }

    @Override
    public List<Student> queryStudents() throws Exception
    {
        return dao.queryStudents();
    }

    @Override
    public List<QualifiedParticipant> queryActivityParticipantsBySchool(String school, 
                                                                        int pageNumber, 
                                                                        int pageSize) throws Exception
    {
        return dao.queryActivityParticipantsBySchool(school, pageNumber,pageSize);
    }

    @Override
    public Student selectStudentInfoByUsernumber(String usernumber) throws
                                                                           Exception
    {
        return dao.selectStudentInfoByUsernumber(usernumber);
    }

    @Override
    public boolean selectCustomerInfoByUsername(String username) throws
                                                                        Exception
    {
        return dao.selectCustomerInfoByUsername(username);
    }

    @Override
    public boolean selectCustomerInfoByTelephone(String telephone) throws
                                                                          Exception
    {
        return dao.selectCustomerInfoByTelephone(telephone);
    }

    @Override
    public List<ActivityParticipant> listActivityParticipants(int activityId,
            String school, String grade, String classname, String realname,
            int pageSize, int pageNumber) throws Exception
    {
        return dao.listActivityParticipants(activityId, 
                                            school, 
                                            grade, 
                                            classname,
                                            realname, 
                                            pageSize, 
                                            pageNumber);
                        
    }
    
    
    
    
    
}
