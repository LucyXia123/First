package com.tsinghuadtv.www.controller.report.api;

import com.tsinghuadtv.www.controller.api.common.PagingRequest;

public class GetCommentListRequest extends PagingRequest {

	private Integer reportId;

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
}
