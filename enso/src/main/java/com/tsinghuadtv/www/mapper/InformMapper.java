package com.tsinghuadtv.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tsinghuadtv.www.entity.Inform;

public interface InformMapper {

	Inform getInform(@Param("id") int id);
	
	List<Inform> listInform(@Param("lim") int lim);
	
	List<Inform> relatedInform(@Param("id") int id);
	
}
