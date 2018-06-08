package com.tsinghuadtv.www.controller.report.api;

import com.tsinghuadtv.www.entity.report.ReportType;

public class CreateReportRequest {

	private Integer topicId;
	private Integer typeId;
	private String title;
	private String coverImage;
	private String abstractInfo;
	private String contentImage;
	private String content;
	private String audioUrl;
	
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public String getAbstractInfo() {
		return abstractInfo;
	}
	public void setAbstractInfo(String abstractInfo) {
		this.abstractInfo = abstractInfo;
	}
	public String getContentImage() {
		return contentImage;
	}
	public void setContentImage(String contentImage) {
		this.contentImage = contentImage;
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
	public boolean validate() {
		ReportType type = ReportType.fromId(typeId);
		if (type == null) {
			return false;
		}
		if (title == null || coverImage == null || abstractInfo == null) {
			return false;
		}
		switch (type) {
		case IMAGE_TEXT : 
			if (content == null) {
				return false;
			}
			break;
		case AUDIO : 
			if (audioUrl == null) {
				return false;
			}
			break;
		}
		return true;
	}
}