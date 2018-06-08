package com.tsinghuadtv.www.service;

import java.util.List;
import java.util.Map;

import com.tsinghuadtv.www.entity.Student;

public interface ISysUserService {

	public int getAreaUserCount(String area)throws Exception;

	public int getMonthCount(String month)throws Exception;
	
	public int getUserCountBeforeDate(String date) throws Exception;

	public int getUserCount() throws Exception;
	
	public int countModelSchool(String area) throws Exception;
	
	public Integer onlineCountByArea(String area) throws Exception;
	
	public List<Student> listUsers(Map<String, String> map, Integer begin, Integer offset) throws Exception;
	
	public Student getUserByNum(String usernumber) throws Exception;

	public String getUserArea(String username) throws Exception;

	// public Integer onlineCount() throws Exception;
}
