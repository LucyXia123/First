package com.tsinghuadtv.www.controller.report.api;

import com.tsinghuadtv.www.controller.api.common.PagingRequest;

public class GetReportListRequest extends PagingRequest {

	private Integer topicId;
	private String area;
	private String schoolName;
	private Integer typeId;
	
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
}
