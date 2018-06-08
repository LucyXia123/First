package com.tsinghuadtv.www.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper implements RowMapper<Course> {

	@Override
	public Course mapRow(ResultSet rs, int arg1) throws SQLException {		
		Course course = new Course();
		course.setId(rs.getInt("cid"));
		course.setTitle(rs.getString("title"));
		course.setUrl(rs.getString("url"));
		course.setImg(rs.getString("img"));
		course.setSchool(rs.getString("school"));
		course.setVideoid(rs.getInt("videoid"));
		course.setContent(rs.getString("content"));
		return course;
	}

}
