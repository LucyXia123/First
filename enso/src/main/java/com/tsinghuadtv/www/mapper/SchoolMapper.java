package com.tsinghuadtv.www.mapper;

import org.apache.ibatis.annotations.Param;

import com.tsinghuadtv.www.entity.School;

public interface SchoolMapper {
	
	School selectSchoolByName(@Param("name") String name);

}
