package com.tsinghuadtv.www.entity;

import java.sql.Date;

public class Journal {
	private Integer id;	
	private String title;
	private String url;
	private String img;
	private String school;
	private int hot;	
	private Date date;
	private String content;
	
	public Journal(Integer id, String title, String url, String img, String school, int hot, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.img = img;
		this.school = school;
		this.hot = hot;
		this.date = date;
	}
	
	public Journal() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int val) {
		this.hot = val;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getContent() {
		return this.content;
	}

	public void setContent(String s) {
		this.content = s;
	}
}