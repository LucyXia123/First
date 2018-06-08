package com.tsinghuadtv.www.controller.energy.api;

import java.util.ArrayList;
import java.util.List;

import com.tsinghuadtv.www.entity.energy.EnergyLevel;

public class EnergyLevelVO {

	private Integer level;
	private Integer energy;
	private String name;

	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getEnergy() {
		return energy;
	}
	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static List<EnergyLevelVO> fromEnergyLevelList(List<EnergyLevel> levels) {
		
		List<EnergyLevelVO> list = new ArrayList<>();
		for (EnergyLevel level : levels) {
			list.add(fromEnergyLevel(level));
		}
		return list;
	}
	
	public static EnergyLevelVO fromEnergyLevel(EnergyLevel level) {
		
		EnergyLevelVO vo = new EnergyLevelVO();
		vo.setLevel(level.getLevel());
		vo.setEnergy(level.getEnergy());
		vo.setName(level.getName());
		
		return vo;
	}
}
