package com.tsinghuadtv.www.controller.report.api;

import com.tsinghuadtv.www.controller.api.common.ServiceResponse;

public class GetReportDetailResponse extends ServiceResponse {
	private static final long serialVersionUID = 1L;

	private ReportDetailVO detail;

	public ReportDetailVO getDetail() {
		return detail;
	}

	public void setDetail(ReportDetailVO detail) {
		this.detail = detail;
	}
}
