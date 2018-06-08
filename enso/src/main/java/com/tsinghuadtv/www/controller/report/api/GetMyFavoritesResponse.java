package com.tsinghuadtv.www.controller.report.api;

import java.util.List;

import com.tsinghuadtv.www.controller.api.common.PagingResponse;

public class GetMyFavoritesResponse extends PagingResponse {
	private static final long serialVersionUID = 1L;

	private List<ReportVO> reports;

	public List<ReportVO> getReports() {
		return reports;
	}

	public void setReports(List<ReportVO> reports) {
		this.reports = reports;
	}
}
