package com.tsinghuadtv.www.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsinghuadtv.www.util.ResponseUtil;

public class TestController {
	
	private ResponseUtil responseUtil = new ResponseUtil();
	
	 @RequestMapping(value="/test/getConst", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	 public @ResponseBody ResponseUtil getConst() throws Exception {
		 System.out.println("xxxxxxx");
		 responseUtil.setResult(0);
		 responseUtil.setCause("success");
		 return responseUtil;		 
	 }

	public static void main(String[] args) {
		InputStream inStream = TestController.class.getClassLoader().getResourceAsStream("const.properties");
		Properties prop = new Properties();
		try {
			prop.load(inStream);
			String key = prop.getProperty("const.username");
			System.out.println(key);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
