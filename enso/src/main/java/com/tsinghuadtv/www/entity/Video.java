package com.tsinghuadtv.www.entity;

public class Video {
	
	private Integer id;       // AUTO_INCREMENT
	private String type;       // enum('学校介绍', '宋城教育', '龙都教育', '新闻', '其他')
	private String name;      // 视频名称
	private String location;  // 视频所在url
	private String poster;    // 视频海报图url
	private String content;   // 视频简介
	
	public Video() {super();}
	
	public Video(Integer id, String type, String name, String location, String poster, String content) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.location = location;
		this.poster = poster;
		this.content = content;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String s) {
		this.type = s;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getPoster() {
		return this.poster;
	}
	
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

    @Override
    public String toString()
    {
        return "Video{" + "id=" + id + ", type=" + type + ", name=" + name + ", location=" + location + ", poster=" + poster + ", content=" + content + '}';
    }
    
}
