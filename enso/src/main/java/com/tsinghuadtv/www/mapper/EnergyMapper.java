package com.tsinghuadtv.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tsinghuadtv.www.entity.energy.EnergyLevel;
import com.tsinghuadtv.www.entity.energy.SchoolEnergy;
import com.tsinghuadtv.www.entity.energy.StudentEnergy;
import com.tsinghuadtv.www.model.filter.SearchFilter;

public interface EnergyMapper {
	
	List<EnergyLevel> selectAllEnergyLevels();
	
	EnergyLevel selectEnergyLevelByLevel(@Param("level") int level);
	
	// student energy
	StudentEnergy selectStudentEnergyByUserNumber(@Param("userNumber") String userNumber);

	int insertStudentEnergy(StudentEnergy studentEnergy);
	
	int updateStudentEnergy(@Param("userNumber") String userNumber);
	
	// school energy
	SchoolEnergy selectSchoolEnergyBySchoolName(@Param("schoolName") String schoolName);
	
	int insertSchoolEnergy(SchoolEnergy schoolEnergy);
	
	int updateSchoolEnergy(@Param("schoolName") String schoolName);
	
	int updateSchoolEnergyRank();
	
	List<SchoolEnergy> selectSchoolEnergyByFilter(@Param("filter") SearchFilter filter);
	
	int countSchoolEnergyByFilter(@Param("filter") SearchFilter filter);
	
}
