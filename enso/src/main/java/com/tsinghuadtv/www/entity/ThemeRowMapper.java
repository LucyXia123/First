package com.tsinghuadtv.www.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ThemeRowMapper implements RowMapper<Theme> {

	@Override
	public Theme mapRow(ResultSet rs, int arg1) throws SQLException {		
		Theme theme = new Theme();
		theme.setId(rs.getInt("id"));
		theme.setTitle(rs.getString("title"));
		theme.setUrl(rs.getString("url"));
		theme.setImg(rs.getString("img"));
		theme.setSchool(rs.getString("school"));
		theme.setHot(rs.getInt("hot"));
		theme.setDate(rs.getDate("date"));
		theme.setContent(rs.getString("content"));
		return theme;
	}

}
