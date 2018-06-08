package com.tsinghuadtv.www.controller.report.api;

import com.tsinghuadtv.www.controller.api.common.ServiceResponse;

public class GetSchoolInfoResponse extends ServiceResponse {
	private static final long serialVersionUID = 1L;

	private SchoolInfoVO schoolInfo;

	public SchoolInfoVO getSchoolInfo() {
		return schoolInfo;
	}

	public void setSchoolInfo(SchoolInfoVO schoolInfo) {
		this.schoolInfo = schoolInfo;
	}
}
