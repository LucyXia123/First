package com.tsinghuadtv.www.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsinghuadtv.www.dao.ISysUserDao;
import com.tsinghuadtv.www.service.IVideoService;

@Transactional	
@Service
public class VideoServiceImpl implements IVideoService {

	@Resource
	ISysUserDao dao;
	
    @Resource
    JdbcTemplate jdbcTemplate;

	@Override
	public @ResponseBody List<Object> query(String type, String name) throws Exception {
		final String sql = "select id, type, name, location, poster, content from t_video";
		String where = " where type=" + type;

		if (null!=name && !name.equals("")) {
			where += " and name like %" + name + "%";
		}
		
		// return jdbcTemplate.execute(action);
		return null;
	}

}
