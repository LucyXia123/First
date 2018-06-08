package com.tsinghuadtv.www.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class T_schoolRowMapper implements RowMapper<T_video> {

	@Override
	public T_video mapRow(ResultSet rs, int arg1) throws SQLException {
		
		T_video t = new T_video();
        t.setId(rs.getInt("id"));
        t.setArea(rs.getString("area"));
        t.setTitle(rs.getString("title"));
        t.setPinyin(rs.getString("pinyin"));
        t.setLogo(rs.getString("logo"));
        t.setImg(rs.getString("img"));
        t.setContent(rs.getString("content"));
        t.setAvatar(rs.getString("avatar"));
        t.setWords(rs.getString("words"));
        
		return t;
	}

}
