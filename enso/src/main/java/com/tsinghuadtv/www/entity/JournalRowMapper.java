package com.tsinghuadtv.www.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class JournalRowMapper implements RowMapper<Journal> {

	@Override
	public Journal mapRow(ResultSet rs, int arg1) throws SQLException {		
		Journal journal = new Journal(); 
		journal.setId(rs.getInt("id"));
		journal.setTitle(rs.getString("title"));
		journal.setUrl(rs.getString("url"));
		journal.setImg(rs.getString("img"));
		journal.setSchool(rs.getString("school"));
		journal.setHot(rs.getInt("hot"));
		journal.setDate(rs.getDate("date"));
		journal.setContent(rs.getString("content"));
		return journal;
	}

}
