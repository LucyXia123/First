package com.tsinghuadtv.www.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NewsRowMapper implements RowMapper<News> {

	@Override
	public News mapRow(ResultSet rs, int arg) throws SQLException {
		News news = new News();
		
		news.setId(rs.getInt("id"));
		news.setTitle(rs.getString("title"));
		news.setContent(rs.getString("content"));
		news.setTime(rs.getString("time"));
		news.setAuthor(rs.getString("author"));
		news.setSource(rs.getString("source"));
		news.setHot(rs.getInt("hot"));

		return news;
	}

}
