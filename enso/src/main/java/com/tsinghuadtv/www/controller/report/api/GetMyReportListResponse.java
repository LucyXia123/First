package com.tsinghuadtv.www.controller.report.api;

import java.util.List;

import com.tsinghuadtv.www.controller.api.common.PagingResponse;

public class GetMyReportListResponse extends PagingResponse {
	private static final long serialVersionUID = 1L;

	private List<MyReportVO> reports;

	public List<MyReportVO> getReports() {
		return reports;
	}

	public void setReports(List<MyReportVO> reports) {
		this.reports = reports;
	}	
}
