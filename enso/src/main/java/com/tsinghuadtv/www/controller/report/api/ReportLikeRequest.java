package com.tsinghuadtv.www.controller.report.api;

public class ReportLikeRequest {

	private Integer reportId;
	private Boolean like;
	
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Boolean getLike() {
		return like;
	}
	public void setLike(Boolean like) {
		this.like = like;
	}
	
}
