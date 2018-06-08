package com.tsinghuadtv.www.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsinghuadtv.www.entity.Student;
import com.tsinghuadtv.www.service.ISysUserService;

@Controller
public class SysUserController {
	
	 @Resource
	 ISysUserService service;
	 
	 // 每个地区人数
	 @RequestMapping(value="/areaCount", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public int areaCount(@RequestParam String area) throws Exception {
		 return service.getAreaUserCount(area);
	 }
	 
     // 每月份增量
     //"2017-11"格式 
	 @RequestMapping(value="/monthCount", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public int monthCount(@RequestParam String month) throws Exception {
		 return service.getMonthCount(month);
	 }
	 
	// date"2017-11-01"格式 在date之前注册的用户总数
	@RequestMapping(value = "/getUserCountBeforeDate", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int getUserCountBeforeDate(@RequestParam String date) throws Exception {		
		return service.getUserCountBeforeDate(date);
	}
	
	// http://localhost/enso-java-bgms/getUserMonthInc?list=6,7,8,9,10,11&year=2017
	// 取得2017年6,7,8,9,10,11月份的用户增量, 按顺序递增
	@RequestMapping(value = "/getUserMonthInc", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Integer[] getUserMonthInc(@RequestParam String list, @RequestParam String year) throws Exception {
		String[] ss = list.split(",");	
		int n = ss.length;
		Integer[] monthList = new Integer[n+1];		
		Integer[] a = new Integer[n+1];
		for (int i = 0; i < n; i++) {
			monthList[i] = Integer.parseInt(ss[i]);
		}
		// 当前月份为12月
		monthList[n] = monthList[n-1]+1;
		if (monthList[n]>12) {
			monthList[n] -= 12;
		}
		ss = null;
		
		int y = Integer.parseInt(year);
		for (int i = 0; i < n+1; i++) {
			String s;
			if (i<n-1 && monthList[i] > monthList[i+1]) {				
				s = String.format("%d-%02d-01", y-1, monthList[i]);
			} else if (i==n && monthList[i-1] > monthList[i]) {
				s = String.format("%d-%02d-01", y+1, monthList[i]);
			} else {
				s = String.format("%d-%02d-01", y, monthList[i]);
			}
			a[i] = service.getUserCountBeforeDate(s);
		}
		Integer[] inc = new Integer[n];
		for (int i = 0; i < n; i++) {
			inc[i] = a[i+1]-a[i];
		}
		
		return inc;		
	}
	 
	 //用户总量
	 @RequestMapping(value="/userCount", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public int userCount() throws Exception {
		 return service.getUserCount();
	 }
	 
	 // 这个用户所在地区
	 @RequestMapping(value="/getUserArea", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public String getUserArea(String username) throws Exception {
		 return service.getUserArea(username);
	 }
	 
	 // 在线人数
	 @RequestMapping(value="/onlineCount", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody
	 public Integer onlineCount(HttpServletRequest request) {
		 Integer count = null;
		 
	     HttpSession sessionB = request.getSession();    
	     ServletContext ContextB = sessionB.getServletContext();    
	     ServletContext ContextA= ContextB.getContext("/enso");// 这里面传递的是 WebappA的虚拟路径  
	     HttpSession sessionA = (HttpSession)ContextA.getAttribute("session");
	     count = (Integer) sessionA.getAttribute("onlineCount");

		 return count;
	 }
	 
	 // 各个地区的示范校个数
	 @RequestMapping(value="/modelSchoolCountArray", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public int[] modelSchoolCountArray(String area) throws Exception {
		 String[] areaList = URLDecoder.decode(area).split(",");
		 int n = areaList.length;
		 int[] a = new int[n];
		 for (int i = 0; i<n; i++) {
			 a[i] = service.countModelSchool(areaList[i]);
		 }
		 return a;
	 }
	 
	 // 多个地区人数  /areaCountArray?area=encodeURI(['开封','濮阳','商丘'].join(',')) @return [7,1,1]
	 @RequestMapping(value="/areaCountArray", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public Integer[] areaCountArray(@RequestParam String area) throws Exception {
		 String[] a = URLDecoder.decode(area).split(",");
		 Integer[] c = new Integer[a.length];
		 
		 for (int i = 0, n = a.length; i < n; i++) {
			 c[i] = service.getAreaUserCount(a[i]);
		 }		 
		 return c;
	 }
	 	 
	 // 各个地区的在线人数
	 @RequestMapping(value="/onlineCountByArea", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public int[] onlineCountByArea(String area) throws Exception {
		 String[] areaList = URLDecoder.decode(area).split(",");
		 int n = areaList.length;
		 int[] a = new int[n];
		 for (int i = 0; i<n; i++) {
			 a[i] = service.onlineCountByArea(areaList[i]);
		 }
		 return a;
	 }
	
	 @RequestMapping(value="/listUsers", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public List<Student> listUsers(String username, String mobile) throws Exception {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("username", username);
		 map.put("telephone", mobile);
		 List<Student> list = service.listUsers(map, null, null);		 
		 return list;
	 }
	 
	 @RequestMapping(value="/getUserByNum", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 @ResponseBody 
	 public Student getUserByNum(String usernumber) throws Exception {
		 return service.getUserByNum(usernumber);
	 }
	  
	 
}
