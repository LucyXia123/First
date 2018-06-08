package com.tsinghuadtv.www.controller.report.api;

import java.util.List;

import com.tsinghuadtv.www.controller.api.common.PagingResponse;

public class GetSchoolEnergyRankListResponse extends PagingResponse {
	private static final long serialVersionUID = 1L;

	private List<SchoolEnergyVO> schoolEnergies;

	public List<SchoolEnergyVO> getSchoolEnergies() {
		return schoolEnergies;
	}

	public void setSchoolEnergies(List<SchoolEnergyVO> schoolEnergies) {
		this.schoolEnergies = schoolEnergies;
	}
}
