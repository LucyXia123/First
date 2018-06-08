package com.tsinghuadtv.www.service.oss.api;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.tsinghuadtv.www.util.LocalFileUtility;


public class OssUtils {

	public static String getGmtNow() {
		SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",Locale.US);
	    format.setTimeZone(TimeZone.getTimeZone("GMT"));
	    String gmtNow = format.format(new Date());
	    return gmtNow;
	}
	
	public static String getRelativePath(String sourceCode) throws Exception {
		String datePath = LocalFileUtility.getDateTimeBasedPath(new Date());
        datePath = datePath.replace(File.separator, "/");
        String filePath = sourceCode + datePath;
        
        return filePath;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getRelativePath("RESOURSE"));
		/*
		 * RESOURSE/2017/1/5
		 */
	}
	
}
