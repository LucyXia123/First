package com.tsinghuadtv.www.util.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	     HttpSession sessionB = request.getSession();    
	     ServletContext ContextB = sessionB.getServletContext();    
	     ServletContext ContextA= ContextB.getContext("/ENSO");// 这里面传递的是 WebappA的虚拟路径  
	     HttpSession sessionA =(HttpSession)ContextA.getAttribute("session");  
	     System.out.println("userId: "+sessionA.getAttribute("username")); 
	     if(sessionA.getAttribute("username").equals("")) {
				System.out.println("尚未登录，调到登录页面");
				response.sendRedirect("/tf_online/login.jsp");
				return false;
	     }
		 return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("afterCompletion");
	}

}
