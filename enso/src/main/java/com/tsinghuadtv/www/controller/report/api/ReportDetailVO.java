package com.tsinghuadtv.www.controller.report.api;

import com.tsinghuadtv.www.entity.report.Report;
import com.tsinghuadtv.www.util.DateTimeUtility;

public class ReportDetailVO {

	private Integer id;
	private String title;
	private String userName;
	private String area;
	private String schoolName;
	private String createTime;
	private String coverImage;
	private String contentImage;
	private String abstractInfo;
	private String content;
	private String audioUrl;
	private Integer viewCount;
	private Integer likeCount;
	private Boolean like;
	private Boolean favorite;
	private Integer statusId;
	private String statusName;
	private Integer typeId;
	private String typeName;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public String getContentImage() {
		return contentImage;
	}
	public void setContentImage(String contentImage) {
		this.contentImage = contentImage;
	}
	public String getAbstractInfo() {
		return abstractInfo;
	}
	public void setAbstractInfo(String abstractInfo) {
		this.abstractInfo = abstractInfo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAudioUrl() {
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Boolean getLike() {
		return like;
	}
	public void setLike(Boolean like) {
		this.like = like;
	}
	public Boolean getFavorite() {
		return favorite;
	}
	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public static ReportDetailVO fromReport(Report report, boolean like, boolean favorite) {
		
		ReportDetailVO vo = new ReportDetailVO();
		
		vo.setId(report.getId());
		vo.setTitle(report.getTitle());
		vo.setUserName(report.getStudent().getRealname());
		vo.setArea(report.getStudent().getArea());
		vo.setSchoolName(report.getStudent().getSchool());
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(report.getCreateTime()));
		vo.setCoverImage(report.getCoverImage());
		vo.setContentImage(report.getContentImage());
		vo.setAbstractInfo(report.getAbstractInfo());
		vo.setContent(report.getContent());
		vo.setAudioUrl(report.getAudioUrl());
		vo.setViewCount(report.getViewCount());
		vo.setLikeCount(report.getLikeCount());
		vo.setStatusId(report.getStatus().getId());
		vo.setStatusName(report.getStatus().getDescription());
		vo.setTypeId(report.getType().getId());
		vo.setTypeName(report.getType().getDescription());
		vo.setLike(like);
		vo.setFavorite(favorite);
		
		return vo;
	}
}
