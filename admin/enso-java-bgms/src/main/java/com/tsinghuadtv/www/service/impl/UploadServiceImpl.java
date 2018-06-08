package com.tsinghuadtv.www.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsinghuadtv.www.entity.T_courseRowMapper;
import com.tsinghuadtv.www.entity.T_journalRowMapper;
import com.tsinghuadtv.www.entity.T_schoolRowMapper;
import com.tsinghuadtv.www.entity.T_teacherRowMapper;
import com.tsinghuadtv.www.entity.T_video;
import com.tsinghuadtv.www.entity.T_videoRowMapper;
import com.tsinghuadtv.www.service.UploadService;

@Transactional	
@Service
public class UploadServiceImpl implements UploadService {
	
    @Resource
    JdbcTemplate jdbcTemplate;

	@Override
	public List<T_video> gett_video(String type) throws Exception {
		String sql="select id,type,name,location,poster,content from t_video where type=? order by id desc";
		return jdbcTemplate.query(sql, new Object[]{type}, new T_videoRowMapper());
	}
	
	@Override
	public T_video getVideoById(Integer id) throws Exception {
		String sql="select id,type,name,location,poster,content from t_video where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new T_videoRowMapper());
	}
	
	@Override
	public int update(T_video t_video) throws Exception {
		String sql="update t_video set type=?,name=?,location=?,poster=?,content=? where id=?";
		return jdbcTemplate.update(sql, new Object[]{t_video.getType(),t_video.getName(),t_video.getLocation(),t_video.getPoster(),t_video.getContent(),t_video.getId()});
	}
	@Override
	public int add(T_video t_video) throws Exception {
		String sql="insert into t_video set type=?,name=?,location=?,poster=?,content=?";
		return jdbcTemplate.update(sql,new Object[]{t_video.getType(),t_video.getName(),t_video.getLocation(),t_video.getPoster(),t_video.getContent()});
	}
	
	// 课程
	@Override
	public List<T_video> gett_course(String school) throws Exception {
		String sql = "select id,img,title,url,videoid,school from t_course where school=? order by id desc";
		return jdbcTemplate.query(sql, new Object[]{school}, new T_courseRowMapper());
	}
	@Override
	public T_video getCourseById(Integer id) throws Exception {
		String sql="select id,title,img,school,url,videoid from t_course where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new T_courseRowMapper());
	}
	
	@Override
	public int updateCourse(T_video t_video) throws Exception {
		String sql = "update t_course set img=?,title=?,url=?,videoid=?,school=? where id=?";
		return jdbcTemplate.update(sql, new Object[]{t_video.getImg(),t_video.getTitle(),t_video.getUrl(),
				t_video.getVideoid(), t_video.getSchool(), t_video.getId()});
	}
	
	@Override
	public int addCourse(T_video t_video) throws Exception {
		String sql="insert into t_course set img=?,title=?,url=?,school=?,videoid=?";
		return jdbcTemplate.update(sql,new Object[]{t_video.getImg(),t_video.getTitle(),t_video.getUrl(),t_video.getSchool(),t_video.getVideoid()});
	}
	
	// 学校简介
	public List<T_video> listSchoolByArea(String area) throws Exception {
		String sql="select id,area,title,pinyin,logo,img,content,avatar,words from t_school where area=? order by id desc";
		return jdbcTemplate.query(sql, new Object[]{area}, new T_schoolRowMapper());
	}
	public T_video getSchoolById(Integer id) throws Exception {
		String sql="select id,area,title,pinyin,logo,img,content,avatar,words from t_school where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new T_schoolRowMapper());
	}
	@Override
	public List<T_video> gett_school(String title) throws Exception {
		String sql="select id,content,avatar,words from t_school where title=?";
		return jdbcTemplate.query(sql, new Object[]{title}, new T_schoolRowMapper());
	}
	
	@Override
	public int updateSchool(T_video school) throws Exception {
		String logo = school.getLogo();
		String avatar = school.getAvatar();
		if (logo != null) {
			if (avatar != null) {
				String sql = "update t_school set area=?,title=?,pinyin=?,logo=?,content=?,avatar=?,words=? where id=?";
				return jdbcTemplate.update(sql, new Object[]{school.getArea(), school.getTitle(), school.getPinyin(), 
						logo,school.getContent(),avatar,school.getWords(),school.getId()});
			} else {
				String sql = "update t_school set area=?,title=?,pinyin=?,logo=?,content=?,words=? where id=?";
				return jdbcTemplate.update(sql, new Object[]{school.getArea(), school.getTitle(), school.getPinyin(), 
						logo,school.getContent(),school.getWords(),school.getId()});
			}
		} else {
			if (avatar != null) {
				String sql = "update t_school set area=?,title=?,pinyin=?,content=?,avatar=?,words=? where id=?";
				return jdbcTemplate.update(sql, new Object[]{school.getArea(), school.getTitle(), school.getPinyin(), 
						school.getContent(),avatar,school.getWords(),school.getId()});
			} else {
				String sql = "update t_school set area=?,title=?,pinyin=?,content=?,words=? where id=?";
				return jdbcTemplate.update(sql, new Object[]{school.getArea(), school.getTitle(), school.getPinyin(), 
						school.getContent(),school.getWords(),school.getId()});
			}
		}
	}
	
	@Override
	public int addSchool(T_video school) throws Exception {
		String sql = "insert into t_school(area,title,pinyin,logo,content,avatar,words,img) values(?,?,?,?,?,?,?,?)"; 
		return jdbcTemplate.update(sql, new Object[]{school.getArea(), school.getTitle(), school.getPinyin(), 
			  school.getLogo(), school.getContent(), school.getAvatar(), school.getWords(), school.getImg()});
	}
	
	@Override
	public List<T_video> gett_teacher(String school, String type) throws Exception {		
		String sql="select id,type,name,url,img,school,intro,datetime,content from t_teacher where type=? and school=? order by id desc";
		return jdbcTemplate.query(sql, new Object[]{type,school}, new T_teacherRowMapper());
	}
	
	public T_video getTeacherById(Integer id) throws Exception {
		String sql="select id,type,name,url,img,school,intro,datetime,content from t_teacher where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new T_teacherRowMapper());
	}
	
	@Override
	public int updateTeacher(T_video t_video) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String img = t_video.getImg();
		if (img != null) {
			String sql="update t_teacher set type=?, name=?,img=?,school=?,intro=?,content=?,datetime=? where id=?";			
			return jdbcTemplate.update(sql, new Object[]{ t_video.getType(),t_video.getName(), img, t_video.getSchool(),
				t_video.getIntro(), t_video.getContent(), df.format(new Date()), t_video.getId() });	
		}
		String sql="update t_teacher set type=?, name=?,school=?,intro=?,content=?,datetime=? where id=?";		
		return jdbcTemplate.update(sql, new Object[]{ t_video.getType(),t_video.getName(), t_video.getSchool(),
			t_video.getIntro(), t_video.getContent(), df.format(new Date()), t_video.getId() });	
	}
	@Override
	public int addTeacher(T_video t_video) throws Exception {
		String sql="insert into t_teacher set type=?, name=?,img=?,school=?,intro=?,content=?,datetime=?";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return jdbcTemplate.update(sql,new Object[]{t_video.getType(),t_video.getName(), t_video.getImg(), t_video.getSchool(),
				t_video.getIntro(), t_video.getContent(), df.format(new Date())});
	}
	@Override
	public List<T_video> gett_journal(String school) throws Exception {
		String sql="select id,img,title,url from t_journal where  school=?";
		return jdbcTemplate.query(sql, new Object[]{school}, new T_journalRowMapper());
	}
	@Override
	public void updateJournal(T_video t_video) throws Exception {
		String sql="update t_journal set  img=?,title=?,url=?, where id=?";
		jdbcTemplate.update(sql, new Object[]{t_video.getImg(),t_video.getTitle(),t_video.getUrl(),t_video.getId()});
	}
	@Override
	public void addJournal(T_video t_video) throws Exception {
		String sql="insert into t_journal  set  img=?,title=?,url=?,school=?";
		jdbcTemplate.update(sql,new Object[]{t_video.getImg(),t_video.getTitle(),t_video.getUrl(),t_video.getSchool()});
	}
	@Override
	public List<T_video> gett_theme(String school) throws Exception {
		String sql="select id,img,title,url from t_theme where  school=?";
		return jdbcTemplate.query(sql, new Object[]{school}, new T_journalRowMapper());
	}
	@Override
	public void updateTheme(T_video t_video) throws Exception {
		String sql="update t_theme set  img=?,title=?,url=?, where id=?";
		jdbcTemplate.update(sql, new Object[]{t_video.getImg(),t_video.getTitle(),t_video.getUrl(),t_video.getId()});
	}
	@Override
	public void addTheme(T_video t_video) throws Exception {
		String sql="insert into t_theme  set  img=?,title=?,url=?,school=?";
		jdbcTemplate.update(sql,new Object[]{t_video.getImg(),t_video.getTitle(),t_video.getUrl(),t_video.getSchool()});
	}
    
}
