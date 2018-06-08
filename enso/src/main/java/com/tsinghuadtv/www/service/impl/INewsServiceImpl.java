package com.tsinghuadtv.www.service.impl;

import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;
import com.tsinghuadtv.www.dao.INewsDao;
import com.tsinghuadtv.www.entity.Course;
import com.tsinghuadtv.www.entity.Journal;
import com.tsinghuadtv.www.entity.School;
import com.tsinghuadtv.www.entity.News;
import com.tsinghuadtv.www.entity.Teacher;
import com.tsinghuadtv.www.entity.Theme;
import com.tsinghuadtv.www.entity.Video;
import com.tsinghuadtv.www.service.INewsService;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class INewsServiceImpl implements INewsService {

	@Resource
	INewsDao dao;
	
	private static SqlSessionFactory sqlSessionFactory;
//	private static Reader reader;

	static 
    {
		try (InputStream is = INewsServiceImpl.class.getResourceAsStream("/conf.xml");
             Reader reader  = new InputStreamReader(is);)
        {
            
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public int add(News news) throws Exception {
		return dao.add(news);
	}
	
	public ArrayList<News> listLatestNews(Integer limit) throws Exception {
		return dao.listLatestNews(limit);
	}
	
	public ArrayList<News> related(Integer thisId, Integer limit) throws Exception {
		return dao.related(thisId, limit);
	}
	
	public News remove(News news) throws Exception {
		return dao.remove(news);
	}

	public News get(Integer id) throws Exception {
		return dao.get(id);
	}
	
	public int put(News news) throws Exception {
		return dao.put(news);		
	}
	
	public ArrayList<Journal> listJournals(String school, Integer limit) throws Exception {
		return dao.listJournals(school, limit);
	}
	
	public ArrayList<Theme> listTheme(String school, Integer limit) throws Exception {
		return dao.listTheme(school, limit);
	}
	
	public ArrayList<Course> listCourse(String school, Integer limit) throws Exception {
		return dao.listCourse(school, limit);
	}
	
	public ArrayList<Video> allCourse(Integer start, Integer end) throws Exception {
		return dao.allCourse(start, end);
	}
	
	public ArrayList<Teacher> listTeacher(String school, String type, Integer limit) throws Exception {
		return dao.listTeacher(school, type, limit);
	}
	
	public Teacher getTeacher(Integer id) throws Exception {
		return dao.getTeacher(id);
	}
	
	public ArrayList<School> allSchools() throws Exception {
		return dao.allSchools();
	}
	
	// 示范校列表
	public List<Object> listModel() throws Exception {		
		return dao.listModel();
	}
	
	// 最后插入的1个示范校
	public Object getLastModel() throws Exception {
		return dao.getLastModel();
	}
	
	// 通知公告列表
	public List<Object> listInform(Integer limit) throws Exception {
		List<Object> list = null;
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			list = session.selectList("com.tsinghuadtv.www.mapper.InformMapper.listInform", limit);
		} finally {
			session.close();
		}
		return list;		
	}
	
	public Object getInform(Integer id) throws Exception {
		Object inform = null;
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			inform = session.selectList("com.tsinghuadtv.www.mapper.InformMapper.getInform", id);
		} finally {
			session.close();
		}
		return inform;
	}

	public Object getPrincipalBySchool(String school) throws Exception {
		return dao.getPrincipalBySchool(school);
	}
	
	public LinkedList<Video> listTVProgram(String area) throws Exception {
		String type = this.getVideoTypeByArea(area);
		return dao.listVideoByType(type);
	}
	
	public Object getLastTVProgram(String area) throws Exception {
		String type = this.getVideoTypeByArea(area);
		return dao.getLastVideoByType(type);
	}
	
	private String getVideoTypeByArea(String area) {
		String type = "";
		switch (area) {
		case "开封": type = "宋城教育"; break;
		case "濮阳": type = "龙都教育"; break;
		case "商丘": type = "应天教育"; break;
		default: type = "学校介绍";
		}
		return type;
	}

	@Override
	public Journal getJournal(Integer i) throws Exception { 
		return dao.getJournal(i);
	}
	
	/**
	 * 取得一条 活动采风
	 */
	public Theme getTheme(Integer i) throws Exception {
		return dao.getTheme(i);
	}
	
	public ArrayList<Theme> allTheme(Integer limit) throws Exception {
		return dao.allTheme(limit);
	}
	
	public ArrayList<Teacher> allTeacher(String type, Integer limit) throws Exception {
		return dao.allTeacher(type, limit);
	}
	
	@Override
	public ArrayList<Teacher> listTeacherByArea(String area, Integer limit, String type) throws Exception {
		return dao.listTeacherByArea(area, limit, type);
	}
}
