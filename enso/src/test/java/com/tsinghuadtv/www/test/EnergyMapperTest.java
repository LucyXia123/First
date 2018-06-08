package com.tsinghuadtv.www.test;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.tsinghuadtv.www.entity.energy.EnergyLevel;
import com.tsinghuadtv.www.entity.energy.SchoolEnergy;
import com.tsinghuadtv.www.entity.energy.StudentEnergy;
import com.tsinghuadtv.www.mapper.EnergyMapper;

public class EnergyMapperTest extends AbstractPersistenceTest {

	@Autowired
	private EnergyMapper energyMapper;
	
//	@Test
	public void selectAllEnergyLevels() {
		printList(energyMapper.selectAllEnergyLevels());
	}
	
//	@Test
	public void selectEnergyLevelByLevel() {
		
		EnergyLevel level = energyMapper.selectEnergyLevelByLevel(1);
		
		System.out.println(ReflectionToStringBuilder.toString(level));
	}
	
//	@Test
	public void selectStudentEnergyByUserNumber() {
		
		StudentEnergy record = energyMapper.selectStudentEnergyByUserNumber("STU201709291720560001");
		
		System.out.println(ReflectionToStringBuilder.toString(record));
	}

//	@Test
	public void insertStudentEnergy() {
		
		StudentEnergy studentEnergy = new StudentEnergy();
		studentEnergy.setEnergy(100);
		studentEnergy.setUserNumber("STU201709301049030001");
		
		System.out.println(energyMapper.insertStudentEnergy(studentEnergy));
	}
	
//	@Test
	public void updateStudentEnergy() {
		
		System.out.println(energyMapper.updateStudentEnergy("STU201709301049030001"));
		
	}
	
//	@Test
	public void selectSchoolEnergyBySchoolName() {
		
		SchoolEnergy record = energyMapper.selectSchoolEnergyBySchoolName("开封市五中");
		
		System.out.println(ReflectionToStringBuilder.toString(record));
	}
	
//	@Test
	public void insertSchoolEnergy() {
		
		SchoolEnergy schoolEnergy = new SchoolEnergy();
		schoolEnergy.setEnergy(100);
		schoolEnergy.setEnergyRank(1);
		schoolEnergy.setSchoolName("开封市五中");
		
		System.out.println(energyMapper.insertSchoolEnergy(schoolEnergy));	
		
	}
	
//	@Test
	public void updateSchoolEnergy() {
		
		System.out.println(energyMapper.updateSchoolEnergy("开封市五中"));	
		
	}
	
//	@Test
	public void updateSchoolEnergyRank() {
		
		System.out.println(energyMapper.updateSchoolEnergyRank());	
	}
}
