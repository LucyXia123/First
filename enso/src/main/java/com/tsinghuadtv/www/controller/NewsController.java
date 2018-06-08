package com.tsinghuadtv.www.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsinghuadtv.www.entity.Course;
import com.tsinghuadtv.www.entity.Journal;
import com.tsinghuadtv.www.entity.School;
import com.tsinghuadtv.www.entity.Theme;
import com.tsinghuadtv.www.entity.Video;
import com.tsinghuadtv.www.entity.News;
import com.tsinghuadtv.www.entity.Teacher;
import com.tsinghuadtv.www.service.INewsService;

@Controller
public class NewsController {
	
	@Resource
	INewsService service;

	@ResponseBody
	@RequestMapping(value = "/news/get", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public Object get(String id) throws Exception {		
		News news = null;
		
		try {			
			Integer newsId = Integer.valueOf(id);
			news = service.get(newsId);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return news;
	}
	
	@ResponseBody
	@RequestMapping(value="/news/latest", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ArrayList<News> listLatestNews(String limit) throws Exception {
		ArrayList<News> news = null;
		try {			
			Integer lim = Integer.valueOf(limit);
			lim = (0==lim) ? 3: lim;
			news = service.listLatestNews(lim);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return news;
	}
	
	@ResponseBody 
	@RequestMapping(value = "/news/related", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public Object related(String thisId, String limit) throws Exception {		
		ArrayList<News> news = null;
		
		try {			
			Integer id = Integer.valueOf(thisId);
			Integer lim = Integer.valueOf(limit);
			news = service.related(id, lim);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return news;
	}	
	
	@ResponseBody
	@RequestMapping(value="/news/listJournals", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ArrayList<Journal> listJournals(String school, String limit) throws Exception {
		ArrayList<Journal> journals = null;
		try {	
			if (limit == null) limit = "8";
			Integer lim = Integer.valueOf(limit);
			lim = (0==lim) ? 8: lim;
			journals = service.listJournals(school, lim);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return journals;
	}
	
	@ResponseBody
	@RequestMapping(value="/news/getJournal", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public Journal getJournal(String id) throws Exception {
		Journal item = null;
		
		try {
			Integer i = Integer.valueOf(id);
			item = service.getJournal(i);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return item;
	}
	
	// http://localhost/news/listTheme?school=%E5%BC%80%E5%B0%81%E5%B8%82%E9%9B%86%E8%8B%B1%E5%B0%8F%E5%AD%A6
	// 根据学校列出主题活动
	@ResponseBody
	@RequestMapping(value="/news/listTheme", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ArrayList<Theme> listTheme(String school, String limit) throws Exception {
		ArrayList<Theme> list = null;
		try {	
			if (limit == null) limit = "8";
			Integer lim = Integer.valueOf(limit);
			lim = (0==lim) ? 8: lim;
			list = service.listTheme(school, lim);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	// http://mingzhanghui/news/allTheme?limit=6
	// 最新的6条主题活动
	@ResponseBody
	@RequestMapping(value="/news/allTheme", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ArrayList<Theme> allTheme(Integer limit) throws Exception {
		ArrayList<Theme> list = null;
		try {	
			if (limit == null) limit = 6;
			list = service.allTheme(limit);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return list;		
	}
	
	@ResponseBody
	@RequestMapping(value="/news/getTheme", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public Theme getTheme(String id) throws Exception {
		Theme item = null;
		
		try {
			Integer i = Integer.valueOf(id);
			item = service.getTheme(i);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return item;
	}
	
	// http://localhost/news/listCourse?school=%E5%BC%80%E5%B0%81%E5%B8%82%E9%9B%86%E8%8B%B1%E5%B0%8F%E5%AD%A6
	@RequestMapping(value="/news/listCourse", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ArrayList<Course> listCourse(String school, String limit) throws Exception {
		ArrayList<Course> list = null;
		try {
			if (limit == null) limit = "4";
			Integer lim = Integer.valueOf(limit);
			lim = (0==lim) ? 4: lim;
			@SuppressWarnings("deprecation")
			String ds = URLDecoder.decode(school);
			list = service.listCourse(ds, lim);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// http://localhost/news/allCourse?start=0&end=1000
	@RequestMapping(value="/news/allCourse", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ArrayList<Video> allCourse(Integer start, Integer end) throws Exception {
		ArrayList<Video> list = null;
		if (null==start) start = 0;
		if (null==end) end = 1000;
		try {
			list = service.allCourse(start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return list;
	}
	
	/**
	 * 教师风采
	 * @param school
	 * @param limit
	 * @return
	 * @throws Exception
	 * Usage: http://localhost/ENSO/news/listTeacher?school=%E5%BC%80%E5%B0%81%E5%B8%82%E4%BA%94%E4%B8%AD
	 */
	@RequestMapping(value="/news/listTeacher", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ArrayList<Teacher> listTeacher(String school, String limit) throws Exception {
		ArrayList<Teacher> list = null;
		try {
			if (limit == null) limit = "4";
			Integer lim = Integer.valueOf(limit);
			lim = (0==lim) ? 4: lim;
			list = service.listTeacher(school, "教师", lim);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="/news/listTeacherByArea", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ArrayList<Teacher> listTeacherByArea(String area, String type, String limit) throws Exception {
		ArrayList<Teacher> list = null;
		if (null==type) {
			type = "教师";
		}
		try {
			if (limit == null) limit = "6";
			Integer lim = Integer.valueOf(limit);
			lim = (0==lim) ? 4: lim;
			list = service.listTeacherByArea(area, lim, type);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	@RequestMapping(value="/news/allTeacher", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ArrayList<Teacher> allTeacher(String type, Integer limit) throws Exception {
		ArrayList<Teacher> list = null;
		try {					
			limit = (0==limit||null==limit) ? 8: limit;
			if (null==type) {
				type = "教师";
			}
			list = service.allTeacher(type, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	@RequestMapping(value="/news/listStudent", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ArrayList<Teacher> listStudent(String school, String limit) throws Exception {
		ArrayList<Teacher> list = null;
		try {
			if (limit == null) limit = "4";
			Integer lim = Integer.valueOf(limit);
			lim = (0==lim) ? 4: lim;			
			list = service.listTeacher(school, "学生", lim);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="/news/getTeacher", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public Teacher getTeacher(String id) throws Exception {		
		Teacher t = null;		
		try {
			Integer n = Integer.valueOf(id);
			t = service.getTeacher(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	// http://localhost/news/allSchools  所有学校介绍列表
	@RequestMapping(value="/news/allSchools", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ArrayList<School> allSchools() throws Exception {
		ArrayList<School> list = null;
		try {
			list = service.allSchools();
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	/**
	 * 所有示范校列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/news/listModel", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public List<Object> listModel() throws Exception {
		List<Object> list = null;
		try {
			list = service.listModel();
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	/**
	 * 最新的示范校信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/news/getLastModel", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public Object getLastModel() throws Exception {
		Object m = null;
		try {
			m = service.getLastModel();
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return m;
	}
	
	/**
	 * 最新的栏目信息
	 * @param: area-开封,濮阳  /news/getLastTVProgram?area=开封
	 * @return 
	 * {"video":
	 *   {"id":12,"type":"宋城教育","name":"宋城教育-第4期",
	 *   "location":"http://118.190.209.209/media/mp4/songcheng-4.mp4",
	 *   "poster":"http://118.190.209.209/media/poster/songcheng-4.jpg",
	 *   "content":"我市举行孔子诞辰纪念活动 万人齐诵经典"}
	 * }
	 * @throws Exception
	 */
	@RequestMapping(value="/news/getLastTVProgram", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public Object getLastTVProgram(String area) throws Exception {
		Object m = null;
		try {
			m = service.getLastTVProgram(area);
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return m;
	}
	
	/**
	 * 栏目信息列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/news/listTVProgram", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public LinkedList<Video> listTVProgram(String area) throws Exception {
		LinkedList<Video> list = null;
		try {
			list = service.listTVProgram(area);
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	@RequestMapping(value="/news/listInform", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public Object listInform(Integer limit) throws Exception {
		Object m = null;
		if (null==limit || 0==limit) {
			limit = 4;
		}
		try {
			m = service.listInform(limit);
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return m;
	}
	
	@RequestMapping(value="/news/getInform", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public Object getInform(Integer id) throws Exception {
		Object m = null;
		if (null==id || id<=0) {
			id = 1;
		}
		try {
			m = service.getInform(id);
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return m;
	}
	
	@ResponseBody 
	@RequestMapping(value = "/news/getPrincipal", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")	
	// 取得学校简介,校长头像,校长寄语
	public Object getPrincipal(String school) throws Exception {
		return service.getPrincipalBySchool(school);
	}

}
