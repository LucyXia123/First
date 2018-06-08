package com.tsinghuadtv.www.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.tsinghuadtv.www.entity.News;
import com.tsinghuadtv.www.entity.Course;
import com.tsinghuadtv.www.entity.Journal;
import com.tsinghuadtv.www.entity.School;
import com.tsinghuadtv.www.entity.Theme;
import com.tsinghuadtv.www.entity.Video;
import com.tsinghuadtv.www.entity.Teacher;

public interface INewsDao {
	// 教育资讯
	public int add(News news) throws Exception;
	
	public ArrayList<News> listLatestNews(Integer limit) throws Exception;
	
	public ArrayList<News> related(Integer thisId, Integer limit) throws Exception;
	
	public News remove(News news) throws Exception;

	public News get(Integer id) throws Exception;
	
	public int put(News news) throws Exception;
	
	public List<Object> listModel() throws Exception;
	
	// 小小记者站
	public ArrayList<Journal> listJournals(String school, Integer limit) throws Exception;
	
	// 主题活动
	public ArrayList<Theme> listTheme(String school, Integer limit) throws Exception;
	
	// 名师优课
	public ArrayList<Course> listCourse(String school, Integer limit) throws Exception;
	public ArrayList<Video> allCourse(Integer start, Integer end) throws Exception;
	
	// 教师风采
	public ArrayList<Teacher> listTeacher(String school, String type, Integer limit) throws Exception;	
	public Teacher getTeacher(Integer id) throws Exception;
	
	public ArrayList<School> allSchools() throws Exception;
	
	// 加载1个示范校列表
	public Object getLastModel() throws Exception;

	// 学校介绍
	public Object getPrincipalBySchool(String school) throws Exception;
	
	public Object getLastVideoByType(String type) throws Exception;
	
	public LinkedList<Video> listVideoByType(String type) throws Exception;

	public Journal getJournal(Integer i) throws Exception;
	
	public Theme getTheme(Integer theme) throws Exception;
	
	public ArrayList<Theme> allTheme(Integer limit) throws Exception;
	
	public ArrayList<Teacher> allTeacher(String type, Integer limit) throws Exception;
	
	ArrayList<Teacher> listTeacherByArea(String area, Integer limit, String type) throws Exception;
    
    
}
