package com.tsinghuadtv.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tsinghuadtv.www.entity.Model;

public interface ModelMapper {

	Model getModel(@Param("id") int id);
	
	Model getLastModel();
	
	List<Model> listModel();
	
}
