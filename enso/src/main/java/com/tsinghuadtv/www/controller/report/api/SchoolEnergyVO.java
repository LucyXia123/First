package com.tsinghuadtv.www.controller.report.api;

import java.util.ArrayList;
import java.util.List;

import com.tsinghuadtv.www.entity.School;
import com.tsinghuadtv.www.entity.energy.EnergyLevel;
import com.tsinghuadtv.www.entity.energy.SchoolEnergy;
import com.tsinghuadtv.www.util.EnergyUtil;

public class SchoolEnergyVO {

	private String name;
	private String logo;
	private String area;
	private Integer energy;
	private Integer rank;
	private Integer level;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getEnergy() {
		return energy;
	}
	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public static List<SchoolEnergyVO> fromSchoolEnergyList(List<SchoolEnergy> schoolEnergys, List<EnergyLevel> levels) {
		
		List<SchoolEnergyVO> list = new ArrayList<>();
		for (SchoolEnergy schoolEnergy : schoolEnergys) {
			list.add(fromSchoolEnergy(schoolEnergy, levels));
		}
		return list;
	}
	
	public static SchoolEnergyVO fromSchoolEnergy(SchoolEnergy schoolEnergy, List<EnergyLevel> levels) {
		
		SchoolEnergyVO vo = new SchoolEnergyVO();
		
		vo.setName(schoolEnergy.getSchoolName());
		vo.setEnergy(schoolEnergy.getEnergy());
		vo.setRank(schoolEnergy.getEnergyRank());
		
		School school = schoolEnergy.getSchool();
		
		if (school != null) {
			vo.setArea(school.getArea());
			vo.setLogo(school.getLogo());
		}
		
		vo.setLevel(EnergyUtil.getEnergyLevel(schoolEnergy.getEnergy(), levels));
		
		return vo;
	}
	
}
