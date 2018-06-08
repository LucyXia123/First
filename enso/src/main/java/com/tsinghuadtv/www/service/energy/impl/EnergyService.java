package com.tsinghuadtv.www.service.energy.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsinghuadtv.www.entity.energy.EnergyLevel;
import com.tsinghuadtv.www.entity.energy.SchoolEnergy;
import com.tsinghuadtv.www.mapper.EnergyMapper;
import com.tsinghuadtv.www.model.common.PagingData;
import com.tsinghuadtv.www.model.common.PagingResult;
import com.tsinghuadtv.www.model.filter.SearchFilter;
import com.tsinghuadtv.www.model.filter.SearchResult;
import com.tsinghuadtv.www.service.energy.IEnergyService;

@Service
public class EnergyService implements IEnergyService {

	@Autowired
	private EnergyMapper energyMapper;
	
	@Override
	public EnergyLevel getEnergyLevelByLevel(int level) {
		return energyMapper.selectEnergyLevelByLevel(level);
	}
	
	@Override
	public SchoolEnergy getSchoolEnergyBySchoolName(String schoolName) {
		return energyMapper.selectSchoolEnergyBySchoolName(schoolName);
	}
	
	@Override
	public List<EnergyLevel> getAllEnergyLevels() {
		return energyMapper.selectAllEnergyLevels();
	}
	
	@Override
	public SearchResult<SchoolEnergy> searchSchoolEnergyByFilter(SearchFilter filter) {
		
        SearchResult<SchoolEnergy> result = new SearchResult<>();

        List<SchoolEnergy> list = energyMapper.selectSchoolEnergyByFilter(filter);
        result.setResult(list);

        PagingData pagingData = filter.getPagingData();
        if (filter.isPaged() && pagingData != null) {
            int recordNumber = energyMapper.countSchoolEnergyByFilter(filter);

            PagingResult pagingResult = new PagingResult();

            pagingResult.setRecordNumber(recordNumber);
            pagingResult.setPageSize(pagingData.getPageSize());
            pagingResult.setPageNumber(pagingData.getPageNumber());

            result.setPaged(true);
            result.setPagingResult(pagingResult);
        }

        return result;
    }
}
