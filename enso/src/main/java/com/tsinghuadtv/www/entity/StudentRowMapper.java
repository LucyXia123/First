package com.tsinghuadtv.www.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Student student = new Student();
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
        student.setType(rs.getString("type"));
		return student;
	}

}
