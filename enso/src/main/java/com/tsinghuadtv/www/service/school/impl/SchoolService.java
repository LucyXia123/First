package com.tsinghuadtv.www.service.school.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsinghuadtv.www.entity.School;
import com.tsinghuadtv.www.mapper.SchoolMapper;
import com.tsinghuadtv.www.service.school.ISchoolService;

@Service
public class SchoolService implements ISchoolService {

	@Autowired
	private SchoolMapper schoolMapper;
	
	@Override
	public School getSchoolByName(String name) {
		return schoolMapper.selectSchoolByName(name);
	}
	
}
