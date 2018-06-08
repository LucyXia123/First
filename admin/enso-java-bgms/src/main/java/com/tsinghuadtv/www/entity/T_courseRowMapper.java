package com.tsinghuadtv.www.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class T_courseRowMapper implements RowMapper<T_video> {

	@Override
	public T_video mapRow(ResultSet rs, int arg1) throws SQLException {
		T_video t = new T_video();
		
        t.setId(rs.getInt("id"));
        t.setTitle(rs.getString("title"));
        t.setImg(rs.getString("img"));
        t.setSchool(rs.getString("school"));
        t.setUrl(rs.getString("url"));
        t.setVideoid(rs.getString("videoid"));
		return t;
	}

}
