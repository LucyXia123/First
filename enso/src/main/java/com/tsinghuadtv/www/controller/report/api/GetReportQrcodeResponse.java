package com.tsinghuadtv.www.controller.report.api;

import com.tsinghuadtv.www.controller.api.common.ServiceResponse;

public class GetReportQrcodeResponse extends ServiceResponse {
	private static final long serialVersionUID = 1L;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
