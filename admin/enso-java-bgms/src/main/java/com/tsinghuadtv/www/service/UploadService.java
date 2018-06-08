package com.tsinghuadtv.www.service;

import java.util.List;

import com.tsinghuadtv.www.entity.T_video;

public interface UploadService {

	// 视频
	public List<T_video> gett_video(String type) throws Exception;
	public T_video getVideoById(Integer id) throws Exception;
	public int update(T_video t_video)throws Exception;
	public int add(T_video t_video) throws Exception;

	// 课程
	public List<T_video> gett_course(String school)throws Exception;	
	public T_video getCourseById(Integer id) throws Exception;	
	public int updateCourse(T_video t_video)throws Exception;
	public int addCourse(T_video t_video) throws Exception;
	
	// 学校
	public List<T_video> listSchoolByArea(String area) throws Exception;
	public T_video getSchoolById(Integer id) throws Exception;
	public List<T_video> gett_school(String title) throws Exception;
	public int updateSchool(T_video t_video) throws Exception;
	public int addSchool(T_video t_video) throws Exception;
	
	// 教师/学生风采
	public List<T_video> gett_teacher(String school,String type) throws Exception;
	public T_video getTeacherById(Integer id) throws Exception;
	public int updateTeacher(T_video t_video) throws Exception;
	public int addTeacher(T_video t_video) throws Exception;
	
	public List<T_video> gett_journal(String school) throws Exception;
	public void updateJournal(T_video t_video) throws Exception;
	public void addJournal(T_video t_video) throws Exception;	
	public List<T_video> gett_theme(String school)throws Exception;
	public void updateTheme(T_video t_video)throws Exception;
	public void addTheme(T_video t_video) throws Exception;
	
}
