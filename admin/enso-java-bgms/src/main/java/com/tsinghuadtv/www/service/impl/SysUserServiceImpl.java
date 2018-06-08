package com.tsinghuadtv.www.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tsinghuadtv.www.dao.ISysUserDao;
import com.tsinghuadtv.www.entity.Student;
import com.tsinghuadtv.www.service.ISysUserService;

@Transactional	
@Service
public class SysUserServiceImpl implements ISysUserService {

	@Resource
	ISysUserDao dao;
	
    @Resource
    JdbcTemplate jdbcTemplate;
    
	@SuppressWarnings("deprecation")
	@Override
	public int getAreaUserCount(String area) throws Exception {
		return jdbcTemplate.queryForInt("select count(*) from T_STUDENT where area='"+area+"'");
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getMonthCount(String month) throws Exception {
		return jdbcTemplate.queryForInt("select count(*) from T_STUDENT where DATE_FORMAT(createdate,'%Y-%m')="+month+"'"  );
	}
	
	public int getUserCountBeforeDate(String date) throws Exception {
		final String sql = "select count(*) from t_student where createdate < \"" + date + "\"";
		return jdbcTemplate.queryForInt(sql);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getUserCount() throws Exception {
		return jdbcTemplate.queryForInt("select count(*) from T_STUDENT");
	}

	@Override
	public String getUserArea(String username) throws Exception {
		return jdbcTemplate.queryForObject("select area from T_STUDENT where username='"+username+"'", String.class) ;
	}
	
	public Integer onlineCountByArea(String area) throws Exception {
		return jdbcTemplate.queryForObject("select count from t_online where area='"+area+"'", Integer.class) ;
	}
	
	public int countModelSchool(String area) throws Exception {
		return jdbcTemplate.queryForObject("select count(id) from t_school where area='"+area+"'", Integer.class) ;
	}
	
	/**
	 * 查询单条用户记录
	 * @param usernumber
	 * @return
	 * @throws Exception
	 */
	public Student getUserByNum(String usernumber) throws Exception {		
		final String sql = "select * from t_student where USERNUMBER=?";
		final Student student = new Student();
		jdbcTemplate.query(sql, new Object[]{usernumber}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				student.setUsernumber(rs.getString("USERNUMBER"));
				student.setUsername(rs.getString("USERNAME"));
				student.setPwd(rs.getString("PASSWORD"));
				student.setMobile(rs.getString("TELEPHONE"));
				student.setEmail(rs.getString("EMAIL"));
				student.setSchool(rs.getString("SCHOOL"));
				student.setGrade(rs.getString("GRADE"));
				student.setClassname(rs.getString("CLASS"));
				student.setRealname(rs.getString("REALNAME"));
				student.setIdnum(rs.getString("IDCRADNO"));
				student.setVersion(rs.getString("VERSION"));
				student.setCreateTime(rs.getDate("CREATEDATE"));
				student.setSalt(rs.getString("SALT"));
				student.setArea(rs.getString("area"));
			}
		});
		return student;
	}
    
	/**
	 * 查询用户 列表
	 * @param begin  limit begin index
	 * @param offset  rows count
	 * @param map     where
	 * @return
	 * @throws Exception
	 */
	public List<Student> listUsers(Map<String, String> map, Integer begin, Integer offset) throws Exception {		
		// jdbcTemplate.queryForList()			
		String sql = "select usernumber,username,area,email,school,grade,class,telephone,version,realname from t_student";
		
		if (!map.isEmpty()) {
			LinkedList<String> cond = new LinkedList<String>();
			for (String key: map.keySet()) {
				String value = map.get(key);
				String s = key + "='" + value + "'";
				if (null != value && "null" != value) {
					cond.add(s);
				}				
			}
			if (!cond.isEmpty()) {
				StringBuilder where = new StringBuilder(" where ");
				for (int i = 0; i < cond.size()-1; i++) {
					where.append(cond.get(i)).append(" and ");
				}
				where.append(cond.get(cond.size()-1));
				sql += where.toString();
			}		
		}

		if (begin !=null && offset != null) {
			sql += String.format(" limit %d, %d", begin, offset);
		}					
		System.out.println(sql);

		List<Student>list = new LinkedList<Student>();
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Student student = new Student();
				student.setUsernumber(rs.getString("usernumber"));
				student.setUsername(rs.getString("username"));								
				student.setArea(rs.getString("area"));
				student.setEmail(rs.getString("email"));
				student.setSchool(rs.getString("school"));
				student.setGrade(rs.getString("grade"));
				student.setClassname(rs.getString("class"));
				student.setMobile(rs.getString("telephone"));
				student.setVersion(rs.getString("version"));
				student.setRealname(rs.getString("realname"));
				list.add(student);				
			}		
		});
		return list;
	}

}
