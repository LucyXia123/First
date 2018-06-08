package com.tsinghuadtv.www.entity.energy;

import java.util.Date;

import com.tsinghuadtv.www.entity.School;

public class SchoolEnergy {

	private Integer id;
	private Integer energy;
	private Integer energyRank;
	private Date updateTime;
	private String schoolName;
	
	private School school;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEnergy() {
		return energy;
	}
	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getEnergyRank() {
		return energyRank;
	}
	public void setEnergyRank(Integer energyRank) {
		this.energyRank = energyRank;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	
}
