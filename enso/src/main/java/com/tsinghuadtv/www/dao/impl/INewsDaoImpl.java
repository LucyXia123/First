package com.tsinghuadtv.www.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.tsinghuadtv.www.dao.INewsDao;
import com.tsinghuadtv.www.entity.Teacher;
import com.tsinghuadtv.www.entity.Journal;
import com.tsinghuadtv.www.entity.JournalRowMapper;
import com.tsinghuadtv.www.entity.Model;
import com.tsinghuadtv.www.entity.News;
import com.tsinghuadtv.www.entity.Theme;
import com.tsinghuadtv.www.entity.Video;
import com.tsinghuadtv.www.entity.Course;
import com.tsinghuadtv.www.entity.School;
import com.tsinghuadtv.www.entity.StudentRowMapper;

import java.util.Map;

@Repository
public class INewsDaoImpl implements INewsDao {

	@Resource
	JdbcTemplate jdbcTemplate;
	
	public Object getLastModel() throws Exception {
		final String sql = "select * from t_model order by id desc limit 1";
		Model model = new Model();
		jdbcTemplate.query(sql, new Object[] {}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				model.setId(rs.getInt("id"));
				model.setSchool(rs.getString("school"));
				model.setPoster(rs.getString("poster"));
				model.setInfo(rs.getString("info"));
				model.setDate(rs.getDate("date"));
				model.setVideoid(rs.getInt("videoid"));
			}
		});
		
		return model;
	}
	
	public int add(News news) throws Exception {
		final String sql = "insert into t_news(title,content,time,author,source,hot) values(?,?,?,?,?,?)";
		if (null == news.getTime()) {
			news.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		}
		return jdbcTemplate.update(sql,
				new Object[] { news.getTitle(), news.getContent(), news.getTime(), news.getAuthor(), news.getSource(), news.getHot() });
	}
	
	/**
	 * 新闻ID不是thisId的相关新闻 limit条标题
	 */
	public ArrayList<News> related(Integer thisId, Integer limit) throws Exception {
		String sql = "select id, title from t_news where id!=? order by RAND() desc limit ?";
		final ArrayList<News> newsList = new ArrayList<News>();
		jdbcTemplate.query(sql, new Object[] {thisId, limit}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				News news = new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				newsList.add(news);
			}
		});
		return newsList;
	}
	
	/**
	 * 列出最新limit条新闻标题
	 */
	public ArrayList<News> listLatestNews(Integer limit) throws Exception {
		String sql = "select id, title, time from t_news order by time desc limit ?";
		final ArrayList<News> newsList = new ArrayList<News>();
		jdbcTemplate.query(sql, new Object[] {limit}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				News news = new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setTime(rs.getString("time"));
				newsList.add(news);
			}
		});
		return newsList;
	}
	
	public News remove(News news) throws Exception {
		return news;
	}

	public News get(Integer id) throws Exception {
		final String sql = "select * from t_news where id=?";
		final News news = new News();
		jdbcTemplate.query(sql,  new Object[] {id}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				news.setId(id);
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setTime(rs.getString("time"));
				news.setAuthor(rs.getString("author"));
				news.setSource(rs.getString("source"));
				news.setHot(rs.getInt("hot"));
			}
		});
		return news;
	}
	
	public int put(News news) throws Exception {
		return 0;		
	}
	
	public List<Object> listModel() throws Exception {
		final String sql = "select id, school, poster, info, date, videoid from t_model";
		final List<Object> list = new LinkedList<Object>();
		jdbcTemplate.query(sql, new Object[] {}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Model m = new Model();
				m.setId(rs.getInt("id"));
				m.setSchool(rs.getString("school"));
				m.setPoster(rs.getString("poster"));
				m.setInfo(rs.getString("info"));				
				m.setDate(rs.getDate("date"));
				m.setVideoid(rs.getInt("videoid"));
				list.add(m);
			}
		});
		
		return list;
	}
	
	// 小小记者站
	public ArrayList<Journal> listJournals(String school, Integer limit) throws Exception {
		String sql = "select id, title, url, img, school, hot, date from t_journal where school=? order by id desc limit ?";
		final ArrayList<Journal> list = new ArrayList<Journal>();
		jdbcTemplate.query(sql, new Object[] {school, limit}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Journal journal = new Journal();
				journal.setId(rs.getInt("id"));
				journal.setTitle(rs.getString("title"));
				journal.setUrl(rs.getString("url"));
				journal.setImg(rs.getString("img"));
				journal.setSchool(rs.getString("school"));
				journal.setHot(rs.getInt("hot"));
				journal.setDate(rs.getDate("date"));				
				list.add(journal);
			}
		});
		return list;
	}
	
	// 主题活动
	public ArrayList<Theme> listTheme(String school, Integer limit) throws Exception {
		String sql = "select id, title, url, img, school, hot, date, content from t_theme where school=? order by id desc limit ?";

		final ArrayList<Theme> list = new ArrayList<Theme>();
		jdbcTemplate.query(sql, new Object[] {school, limit}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Theme theme = new Theme();
				theme.setId(rs.getInt("id"));
				theme.setTitle(rs.getString("title"));
				theme.setUrl(rs.getString("url"));
				theme.setImg(rs.getString("img"));
				theme.setSchool(rs.getString("school"));
				theme.setHot(rs.getInt("hot"));
				theme.setDate(rs.getDate("date"));
				theme.setContent(rs.getString("content"));
				list.add(theme);
			}
		});
		return list;
	}
	
	public ArrayList<Theme> allTheme(Integer limit) throws Exception {
		String sql = "select id, title, url, img, school, hot, date, content from t_theme order by id desc limit ?";
		final ArrayList<Theme> list = new ArrayList<Theme>();
		jdbcTemplate.query(sql, new Object[] {limit}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Theme theme = new Theme();
				theme.setId(rs.getInt("id"));
				theme.setTitle(rs.getString("title"));
				theme.setUrl(rs.getString("url"));
				theme.setImg(rs.getString("img"));
				theme.setSchool(rs.getString("school"));
				theme.setHot(rs.getInt("hot"));
				theme.setDate(rs.getDate("date"));
				// 活动采风正文只取前面一段 用 js...
				theme.setContent(rs.getString("content"));
				list.add(theme);
			}
		});
		return list;
	}
	
	public Theme getTheme(Integer id) throws Exception {		
		String sql = "select id, title, url, img, school, hot, date, content from t_theme where id=?";

		Theme theme = new Theme();
		jdbcTemplate.query(sql, new Object[] {id}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {				
				theme.setId(rs.getInt("id"));
				theme.setTitle(rs.getString("title"));
				theme.setUrl(rs.getString("url"));
				theme.setImg(rs.getString("img"));
				theme.setSchool(rs.getString("school"));
				theme.setHot(rs.getInt("hot"));
				theme.setDate(rs.getDate("date"));
				theme.setContent(rs.getString("content"));
			}
		});
		return theme;
	}
	
	// 名师优课
	public ArrayList<Course> listCourse(String school, Integer limit) throws Exception {
		String sql = "select t_course.id as cid, title, url, img, school, videoid, content from t_course left join t_video on t_course.videoid=t_video.id where school=? order by cid desc limit ?";
		final ArrayList<Course> list = new ArrayList<Course>();
		limit = (null==limit)?4:limit;
		jdbcTemplate.query(sql, new Object[] {school, limit}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Course course = new Course();
				course.setId(rs.getInt("cid"));
				course.setTitle(rs.getString("title"));
				course.setUrl(rs.getString("url"));
				course.setImg(rs.getString("img"));
				course.setSchool(rs.getString("school"));
				course.setVideoid(rs.getInt("videoid"));
				course.setContent(rs.getString("content"));
				list.add(course);
			}
		});
		return list;
	}
	
	public ArrayList<Video> allCourse(Integer start, Integer end) throws Exception {
		final String sql = "SELECT id,name,poster,content FROM `t_video` WHERE type='课程' order by id desc limit ?, ?";
		final ArrayList<Video> list = new ArrayList<Video>();
		jdbcTemplate.query(sql, new Object[] {start, end}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Video video = new Video();
				video.setId(rs.getInt("id"));
				video.setName(rs.getString("name"));				
				video.setPoster(rs.getString("poster"));
				video.setContent(rs.getString("content"));
				list.add(video);
			}
		});
		return list;
	}
	
	// 教师风采列表
	public ArrayList<Teacher> listTeacher(String school, String type, Integer limit) throws Exception {		
		// order by id desc ?
		String sql = "select id, name, url, img, school, intro from t_teacher where school=? and type=? order by id desc limit ?";
		ArrayList<Teacher> list = new ArrayList<Teacher>();
		limit = (null==limit)?4:limit;
		jdbcTemplate.query(sql, new Object[] {school, type, limit}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setName(rs.getString("name"));
				teacher.setUrl(rs.getString("url"));
				teacher.setImg(rs.getString("img"));
				teacher.setSchool(rs.getString("school"));
				teacher.setIntro(rs.getString("intro"));
				list.add(teacher);
			}
		});		
		return list;
	}
	
	// 所有教师/学生
	public ArrayList<Teacher> allTeacher(String type, Integer limit) throws Exception {		
		// order by id desc ?
		String sql = "select id, name, url, img, school from t_teacher where type=? order by id desc limit ?";
		ArrayList<Teacher> list = new ArrayList<Teacher>();
		limit = (null==limit)?4:limit;
		jdbcTemplate.query(sql, new Object[] {type, limit}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setName(rs.getString("name"));
				teacher.setImg(rs.getString("img"));
				teacher.setSchool(rs.getString("school"));
				list.add(teacher);
			}
		});		
		return list;
	}
	
	// 教师详情文本页
	public Teacher getTeacher(Integer id) throws Exception {
		final String sql = "SELECT id, name, url, img, school, intro, datetime, content FROM `t_teacher` WHERE id=?";
		Teacher t = new Teacher();
		jdbcTemplate.query(sql, new Object[] {id}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				t.setId(rs.getInt("id"));
				t.setName(rs.getString("name"));
				t.setUrl(rs.getString("url"));
				t.setImg(rs.getString("img"));
				t.setSchool(rs.getString("school"));
				t.setIntro(rs.getString("intro"));
				t.setDatetime(rs.getDate("datetime"));
				t.setContent(rs.getString("content"));
			}
		});
		return t;
	}
	
	// 所有学校介绍
	public ArrayList<School> allSchools() throws Exception {
		final String sql = "select id, title, area, pinyin, logo, img, content from t_school";
		ArrayList<School> list = new ArrayList<School>();
		jdbcTemplate.query(sql, new Object[] {}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				School school = new School();
				school.setId(rs.getInt("id"));
				school.setArea(rs.getString("area"));
				school.setTitle(rs.getString("title"));
				school.setPinyin(rs.getString("pinyin"));
				school.setLogo(rs.getString("logo"));				
				school.setImg(rs.getString("img").split(","));
				school.setContent(rs.getString("content"));
				list.add(school);
			}
		});
		return list;
	}
	
	// 学校介绍 + 校长介绍
	public Object getPrincipalBySchool(String school) throws Exception {
		School entry = new School();
		final String sql = "select * from t_school where title=? order by id desc limit 1";
		
		jdbcTemplate.query(sql, new Object[] {school}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				entry.setId(rs.getInt("id"));
				entry.setArea(rs.getString("area"));
				entry.setAvatar(rs.getString("avatar"));
				entry.setContent(rs.getString("content"));
				entry.setId(rs.getInt("id"));
				entry.setImg(rs.getString("img").split(","));
				entry.setLogo(rs.getString("logo"));
				entry.setPinyin(rs.getString("pinyin"));
				entry.setTitle(rs.getString("title"));
				entry.setWords(rs.getString("words"));
			}
		});
		
		return entry;
	}
	
	/**
	 * 取得最新的视频 type: enum('学校介绍','宋城教育','龙都教育','新闻','其他')
	 */
	public Object getLastVideoByType(String type) throws Exception {				
		Video video = new Video();
		// todo: 改变实体
		final String sql = "select id,type,name,location,poster,content from t_video where type=? order by id desc limit 1";
		jdbcTemplate.query(sql, new Object[] {type}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				video.setId(rs.getInt("id"));
				video.setType(rs.getString("type"));
				video.setName(rs.getString("name"));
				video.setLocation(rs.getString("location"));
				video.setPoster(rs.getString("poster"));
				video.setContent(rs.getString("content"));
			}
		});		
		return video;
	}
	
	/**
	 * 根据类型列出视频
	 */
	public LinkedList<Video> listVideoByType(String type) throws Exception {
		final String sql = "select id,type,name,location,poster,content from t_video where type=?";
		LinkedList<Video> list = new LinkedList<Video>();
		jdbcTemplate.query(sql, new Object[] {type}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Video video = new Video();
				video.setId(rs.getInt("id"));
				video.setType(rs.getString("type"));
				video.setName(rs.getString("name"));
				video.setLocation(rs.getString("location"));
				video.setPoster(rs.getString("poster"));				
				video.setContent(rs.getString("content"));				
				list.add(video);
			}
		});
		return list;
	}

	// 取得校园记者站详情
	@Override
	public Journal getJournal(Integer id) throws Exception {	
		final String sql = "select id,title,url,img,school,hot,date,content from t_journal where id=?";		
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new JournalRowMapper());
	}

	@Override
	public ArrayList<Teacher> listTeacherByArea(String area, Integer limit, String type) throws Exception {
		ArrayList<Teacher> list = new ArrayList<Teacher>();
		final String sql = "SELECT id,name,img,school,intro FROM `t_teacher` WHERE school like \""+area+"%\" and type=? order by id desc limit ?";
		jdbcTemplate.query(sql, new Object[] {type, limit}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Teacher t = new Teacher();
				t.setId(rs.getInt("id"));
				t.setName(rs.getString("name"));
				t.setImg(rs.getString("img"));
				t.setSchool(rs.getString("school"));
				t.setIntro(rs.getString("intro"));
				list.add(t);
			}
		});
		return list;
	}

    
    
    
}
