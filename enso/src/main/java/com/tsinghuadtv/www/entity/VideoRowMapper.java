package com.tsinghuadtv.www.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VideoRowMapper implements RowMapper<Video> {
	 public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Video video = new Video();
		 
		 video.setId(rs.getInt("id"));
		 video.setName(rs.getString("name"));
		 video.setLocation(rs.getString("location"));
		 video.setPoster(rs.getString("poster"));
		 video.setContent(rs.getString("content"));
		 video.setType(rs.getString("type"));
		 
		 return video;
	 }
}
