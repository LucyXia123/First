package com.tsinghuadtv.www.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tsinghuadtv.www.dao.ISysUserDao;

@Repository
public class ISysUserDaoImpl implements ISysUserDao {

	@Resource
	JdbcTemplate jdbcTemplate;

}
