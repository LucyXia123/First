package com.tsinghuadtv.www.entity;

public class T_video {
	
    private Integer id;
	private String name;//二、视频标题 ;四、老师名字
	private String location;//二、视频URL
	private String poster;//二、海报URL(没有就第０秒)
	private String content;//二、视频简介;三、学校介绍;四、详细页内容
	private String type;//二、地区(XX教育,XX教育,XX教育); 四、学生或老师
	
	private String avatar;//三、校长头像
	private String words;//三、校长寄语
	private String title;//三、学校;五、标题;六、标题;七、标题
	
	private String img;//四、老师头像;五、图片;六、图片;七、图片
	private String intro;//四、老师简介
	private String datetime;//四、日期
	
	private String school;//四、学校;
	
	private String url;//五、详情;六、详情;七、详情
	
	private String videoid;// 七、视频id
	private String area;	
	private String pinyin;
	private String logo;
	
	public T_video() {
		super();
	}

	public T_video(Integer id, String name, String location, String poster, String content, String type, String avatar,
			String words, String title, String img, String intro, String datetime, String school, String url,
			String videoid) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.poster = poster;
		this.content = content;
		this.type = type;
		this.avatar = avatar;
		this.words = words;
		this.title = title;
		this.img = img;
		this.intro = intro;
		this.datetime = datetime;
		this.school = school;
		this.url = url;
		this.videoid = videoid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVideoid() {
		return videoid;
	}

	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}

	public void setArea(String area) {
		this.area = area;		
	}
	public String getArea() {
		return this.area;
	}
	
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getPinyin() {
		return this.pinyin;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getLogo() {
		return this.logo;
	}
}
