package com.tsinghuadtv.www.entity;

public class News {
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	
	private Integer id;         // AUTO_INCREMENT
	private String 	title;      // 新闻标题
	private String  content; 	// 新闻内容
	private String 	time;       // 新闻发布datetime
	private String 	author;     // 新闻作者
	private String source;      // 新闻来源
	private int hot;            // 新闻访问量
	
}
