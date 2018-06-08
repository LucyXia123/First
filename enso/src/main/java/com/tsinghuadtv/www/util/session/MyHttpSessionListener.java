package com.tsinghuadtv.www.util.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.tsinghuadtv.www.service.ISysUserService;

public class MyHttpSessionListener implements HttpSessionListener {
	ISysUserService service;
    private static int count; //统计数
    
    public static int getCount() {
        return count;
    }
    //在session开始时,统计数加1
    public void sessionCreated(HttpSessionEvent se) {
        MyHttpSessionListener.count++;
    }
    //在Session销毁时,统计数减1
    public void sessionDestroyed(HttpSessionEvent se) {
        MyHttpSessionListener.count--;       
    }
}