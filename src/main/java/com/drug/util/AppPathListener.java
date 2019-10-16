package com.drug.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppPathListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		String contextPath = servletContext.getContextPath();
		System.err.println(contextPath);
		servletContext.setAttribute("APP_PATH", contextPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
