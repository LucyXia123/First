package com.tsinghuadtv.www.controller.report.api;

public class ReportFavoriteRequest {

	private Integer reportId;
	private Boolean favorite;
	
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Boolean getFavorite() {
		return favorite;
	}
	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}
	
}
