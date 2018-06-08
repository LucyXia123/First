package com.tsinghuadtv.www.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class T_videoRowMapper implements RowMapper<T_video> {

	@Override
	public T_video mapRow(ResultSet rs, int arg1) throws SQLException {
		
		T_video t = new T_video();
		
        t.setId(rs.getInt("id"));
        t.setType(rs.getString("type"));
        t.setName(rs.getString("name"));
        t.setLocation(rs.getString("location"));
        t.setPoster(rs.getString("poster"));
        t.setContent(rs.getString("content"));
        
		return t;
	}

}
