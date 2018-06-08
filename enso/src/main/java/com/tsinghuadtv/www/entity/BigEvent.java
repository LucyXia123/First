package com.tsinghuadtv.www.entity;

import java.sql.Date;

public class BigEvent {

	private Integer id;
	private String userid;
	private String title;
	private String content;
	private String imageFile;
	// private String location;
	private String img;
	private Date createDate;

	public BigEvent() {
		super();
	}

	public BigEvent(Integer id, String userid, String title, String content, String imageFile, String img,
			String location,
			Date createDate) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.imageFile = imageFile;
		this.img = img;
		// this.location = location;
		this.createDate = createDate;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date date) {
		this.createDate = date;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

}
