package com.drug.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.drug.entity.BranchEmployee;


public class LoginIntercepter implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
	HttpSession session = request.getSession();
	BranchEmployee employee = (BranchEmployee) session.getAttribute("employee");
	if (employee==null) {
	    // 获取到项目名，以便下面进行重定向
	    String homeUrl = request.getContextPath();
	    // 如果是 ajax 请求，则设置 session 状态 、CONTEXTPATH 的路径值
	    // 如果是ajax请求响应头会有，x-requested-with
	    if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
		response.setHeader("SESSIONSTATUS", "TIMEOUT");
		response.setHeader("CONTEXTPATH", homeUrl+"/login.jsp");
		// FORBIDDEN，forbidden。也就是禁止、403
		response.setStatus(HttpServletResponse.SC_FORBIDDEN); 
	    }else{
		// 如果不是 ajax 请求，则直接跳转即可
		response.sendRedirect(homeUrl+"/index.html");
	    }
	    return false;
	}else {
	    return true;
	}

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	    throws Exception {

    }

}
