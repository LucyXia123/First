package com.tsinghuadtv.www.entity;

import java.sql.Date;

public class Comment {
	
	private Integer id;
	private String userid;
	private String content;
	private String username;
	private Integer videoid;
	private Date createtime;

	public Comment() {
		super();
	}

	public Comment(Integer id, String userid, String content, String username, Integer videoid, Date createtime) {
		super();
		this.id = id;
		this.userid = userid;
		this.content = content;
		this.username = username;
		this.videoid = videoid;
		this.createtime = createtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getVideoid() {
		return videoid;
	}

	public void setVideoid(Integer videoid) {
		this.videoid = videoid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
