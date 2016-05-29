package com.classnotice.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginChecker implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
		HttpSession session=request.getSession();
		String uid=(String)session.getAttribute("uid");
		if(uid==null || uid.equals("")){
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
	
	//=========Useless functions below===========
	public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {}
	public void postHandle(HttpServletRequest arg0,HttpServletResponse arg1,Object arg2,ModelAndView arg3) throws Exception {}
}
