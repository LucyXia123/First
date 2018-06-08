package com.tsinghuadtv.www.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsinghuadtv.www.service.ISysUserService;
import com.tsinghuadtv.www.service.IVideoService;

@Controller
public class VideoController {
	 @Resource
	 IVideoService service;
	 
	 // 每个地区人数
	 @RequestMapping(value="/video/query", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public List<Object> query(@RequestParam String type, @RequestParam String name) throws Exception {
		 return service.query(type, name);
	 }
}