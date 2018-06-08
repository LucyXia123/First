package com.tsinghuadtv.www.mapper;

import org.apache.ibatis.annotations.Param;

import com.tsinghuadtv.www.entity.Student;

public interface StudentMapper {
	
	Student selectStudentByUserNumber(@Param("userNumber") String userNumber);

}
