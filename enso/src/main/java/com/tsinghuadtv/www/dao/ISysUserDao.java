package com.tsinghuadtv.www.dao;

import com.tflx.enso.service.activity.json.ActivityParticipant;
import com.tflx.enso.service.activity.json.QualifiedParticipant;
import com.tflx.enso.service.activity.json.SchoolActivity;
import com.tflx.enso.service.bonus.json.BonusAccount;
import com.tflx.enso.service.bonus.json.BonusSettlement;
import java.util.List;
import java.util.Map;

import com.tsinghuadtv.www.entity.BigEvent;
import com.tsinghuadtv.www.entity.Comment;
import com.tsinghuadtv.www.entity.Student;
import com.tsinghuadtv.www.entity.TComment;
import com.tsinghuadtv.www.entity.UserInfo;
import com.tsinghuadtv.www.entity.Video;
import java.time.LocalDate;

public interface ISysUserDao {

	public int save(Student student) throws Exception;

	public Student selectByUserNo(String username) throws Exception;
	
	public int selectMobile(String mobile) throws Exception;
	
	public Student selectByUserMobile(String phone)throws Exception;
	
	public int selectUsername(String username)throws Exception;

	public Student selectByUsername(String username)throws Exception;
	
	public Student selectByUserid(String userid)throws Exception;
	
	public void updatePassword(Student student)throws Exception;

	public int createComment(Comment comment)throws Exception;

	public int deleteComment(Integer commnetId)throws Exception;
	
	public int updateUser(Student student) throws Exception;
	
	public void saveEvent(BigEvent bigEvent) throws Exception;
	
	public boolean eventExists(BigEvent bigEvent) throws Exception;
	
	/**
	 * 根据用户id和日期更新大事件
	 * @param bigEvent
	 * @return
	 * @throws Exception
	 */
	public int updateEvent(BigEvent bigEvent) throws Exception;
	
	public List<BigEvent> getEventByUserid(String userid) throws Exception;
	
	// 视频评论分页
    public List<TComment> getregList(String id, int currentPage,int pageSize)throws Exception;
    
    // count 一个视频id对应的评论条数
    public int findInfoRowCount(String id) throws Exception;
	
    public Video getVideoById(Integer id) throws Exception;
    
    public List<Map<String, Object>> listVideoByType(String type) throws Exception;
    
    // 通过手机号找回密码
    public int resetPwdByMobile(String mobile, String ciphertext, String salt) throws Exception;
    
    public BonusAccount queryBonusAmountById(String usernumber)throws Exception;
    
    public List<BonusSettlement> queryBonusSettlementList(int accountId,int pageSize, int pageNumber)throws Exception;
    
    public UserInfo queryUserInfoByIndentifier(String identifier,boolean flag)throws Exception;
    
    public SchoolActivity querySchoolActivity(int id)throws Exception;
    
    public ActivityParticipant insertActivityParticipants(ActivityParticipant activityParticipant)throws Exception;
    
    public int initBonusAccount(String usernumber,String type)throws Exception;
    
    public Student queryStudentInfoByUsername(String username)throws Exception;
    
    public boolean uploadFileMaterials(int participantId,String title,String works)throws Exception;
    
    public ActivityParticipant queryActivityParticipantsById(int id) throws Exception;
    
    public void updateActivityParticipantsById(ActivityParticipant activityParticipant)throws Exception;
    
    public ActivityParticipant queryActivityParticipantsByActivityIdAndUsernumber(int activityId,String usernumber)throws Exception;
    
    
    public List<QualifiedParticipant> queryStudentActivityWorks(String area, int count)throws Exception;
    
    public QualifiedParticipant queryStudentActivityWorksById(int id)throws Exception;
    
    public BonusSettlement changeBonus(int accountId, String accountType, int accountAmount,String title, String comment )throws Exception;
    
    public int checkUserBalance(String usernumber)throws Exception;
    
    public int checkAccountBalance(int accountId)throws Exception;
    
    public BonusAccount queryBonusAccountById(int id)throws Exception;
    
    public void recordLogger(String userIdentity,
                             String userAddress,
                             String userAgent,
                             String accessUri,
                             String accessContext,
                             String accessStatus,
                             String accessSource)throws Exception;
    public int queryLoggerByConditional(String usernumber, 
                                        String uri,
                                        String status,
                                        LocalDate localDate)throws Exception;
    
    public int addLikes(int id)throws Exception;
    
    public int addVotes(int id)throws Exception;
    
    public boolean uploadAvatarByParticipantId(int participantId, String avatarUrl)throws Exception;
    
    public void updateCustomerInfo(Student student)throws Exception;
    
    public boolean selectStudentInfoByUsernumberAndUsername(String usernumber,String username)throws Exception;
    
    public boolean selecStudentInfoByUsernumberAndTelephone(String usernumber,String telephone)throws Exception;
    
    
    public List<ActivityParticipant> queryActivityParticipantStudentsInfo(int activityId,boolean flag, int pageNum, int pageSize)throws Exception;
    
    public List<Student> queryStudents()throws Exception;
    
    public List<QualifiedParticipant> queryActivityParticipantsBySchool(String school, int pageNumber,int pageSize)throws Exception;
    
    public Student selectStudentInfoByUsernumber(String usernumber)throws Exception;
    
    public boolean selectCustomerInfoByUsername(String username)throws Exception;
    public boolean selectCustomerInfoByTelephone(String telephone)throws Exception;
    
    public List<ActivityParticipant> listActivityParticipants(int activityId, 
                                         String school, 
                                         String grade, 
                                         String classname,
                                         String realname,
                                         int pageSize,
                                         int pageNumber)throws Exception;
}
