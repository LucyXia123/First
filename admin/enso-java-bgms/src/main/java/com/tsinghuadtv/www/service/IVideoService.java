package com.tsinghuadtv.www.service;

import java.util.List;
import java.util.Map;

import com.tsinghuadtv.www.entity.Student;

public interface IVideoService {

	public List<Object> query(String type, String name)throws Exception;

}
