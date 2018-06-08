package com.tsinghuadtv.www.entity;

import java.sql.Date;

public class Inform {
	
	private int id;
	private String title;
	private String url;
	private String content;
	private Date date;	
	
	public Inform() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDatetime(Date date) {
		this.date = date;
	}

}
