package com.tsinghuadtv.www.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class T_teacherRowMapper implements RowMapper<T_video> {

	@Override
	public T_video mapRow(ResultSet rs, int arg1) throws SQLException {
		
		T_video t=new T_video();
		
        t.setId(rs.getInt("id"));
        t.setName(rs.getString("name"));
        t.setContent(rs.getString("content"));
        t.setType(rs.getString("type"));
        t.setImg(rs.getString("img"));
        t.setIntro(rs.getString("intro"));
        t.setDatetime(rs.getString("datetime"));
        t.setSchool(rs.getString("school"));
		return t;
		
	}

}
