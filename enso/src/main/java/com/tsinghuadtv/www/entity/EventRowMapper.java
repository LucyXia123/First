package com.tsinghuadtv.www.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EventRowMapper implements RowMapper<BigEvent> {

	@Override
	public BigEvent mapRow(ResultSet rs, int arg1) throws SQLException {
		BigEvent bigEvent=new BigEvent();
		bigEvent.setId(rs.getInt("id"));
		bigEvent.setContent(rs.getString("content"));
		bigEvent.setCreateDate(rs.getDate("createtime"));
		bigEvent.setImageFile(rs.getString("imageFile"));
		bigEvent.setTitle(rs.getString("title"));
		bigEvent.setUserid(rs.getString("userid"));
		bigEvent.setImg(rs.getString("img"));
		// bigEvent.setLocation(rs.getString("location"));
		return bigEvent;
	}

}
