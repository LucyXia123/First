package com.tsinghuadtv.www.controller.report.api;

public class CreateCommentRequest {

	private Integer reportId;
	private String content;
	
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
