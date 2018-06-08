package com.tsinghuadtv.www.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.tsinghuadtv.www.entity.Course;
import com.tsinghuadtv.www.entity.Journal;
import com.tsinghuadtv.www.entity.School;
import com.tsinghuadtv.www.entity.News;
import com.tsinghuadtv.www.entity.Teacher;
import com.tsinghuadtv.www.entity.Theme;
import com.tsinghuadtv.www.entity.Video;

public interface INewsService {
	
	public int add(News news) throws Exception;
	
	public ArrayList<News> listLatestNews(Integer limit) throws Exception;
	
	public ArrayList<News> related(Integer thisId, Integer limit) throws Exception;
	
	public News remove(News news) throws Exception;

	public News get(Integer id) throws Exception;
	
	public int put(News news) throws Exception;
	
	public ArrayList<Journal> listJournals(String school, Integer limit) throws Exception;
	
	public ArrayList<Theme> listTheme(String school, Integer limit) throws Exception;
	
	public ArrayList<Theme> allTheme(Integer limit) throws Exception;
	
	public Theme getTheme(Integer i) throws Exception;
	
	public ArrayList<Course> listCourse(String school, Integer limit) throws Exception;
	
	public ArrayList<Video> allCourse(Integer start, Integer end) throws Exception;
	
	public ArrayList<Teacher> listTeacher(String school, String type, Integer limit) throws Exception;
	
	public ArrayList<Teacher> listTeacherByArea(String area, Integer limit, String type) throws Exception;
	
	public Teacher getTeacher(Integer id) throws Exception;
	
	public ArrayList<School> allSchools() throws Exception;
	
	// 列出所有示范校
	public List<Object> listModel() throws Exception;
	
	public Object getLastModel() throws Exception;
	
	// 列出通知公告
	public List<Object> listInform(Integer limit) throws Exception;
	
	public Object getInform(Integer id) throws Exception;
	
	public Object getPrincipalBySchool(String school) throws Exception;
	
	public Object getLastTVProgram(String area) throws Exception;
	
	public LinkedList<Video> listTVProgram(String area) throws Exception;

	public Journal getJournal(Integer i) throws Exception;
	
	public ArrayList<Teacher> allTeacher(String type, Integer limit) throws Exception;
    
    
}
