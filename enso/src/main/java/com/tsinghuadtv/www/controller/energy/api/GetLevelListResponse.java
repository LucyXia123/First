package com.tsinghuadtv.www.controller.energy.api;

import java.util.List;

import com.tsinghuadtv.www.controller.api.common.ServiceResponse;

public class GetLevelListResponse extends ServiceResponse {
	private static final long serialVersionUID = 1L;

	private List<EnergyLevelVO> levels;

	public List<EnergyLevelVO> getLevels() {
		return levels;
	}

	public void setLevels(List<EnergyLevelVO> levels) {
		this.levels = levels;
	}
}
