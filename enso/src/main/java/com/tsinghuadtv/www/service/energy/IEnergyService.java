package com.tsinghuadtv.www.service.energy;

import java.util.List;

import com.tsinghuadtv.www.entity.energy.EnergyLevel;
import com.tsinghuadtv.www.entity.energy.SchoolEnergy;
import com.tsinghuadtv.www.model.filter.SearchFilter;
import com.tsinghuadtv.www.model.filter.SearchResult;

public interface IEnergyService {
	
	public EnergyLevel getEnergyLevelByLevel(int level);
	
	public SchoolEnergy getSchoolEnergyBySchoolName(String schoolName);

	public List<EnergyLevel> getAllEnergyLevels();
	
	public SearchResult<SchoolEnergy> searchSchoolEnergyByFilter(SearchFilter filter);
	
}
