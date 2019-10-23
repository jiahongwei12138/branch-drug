package com.drug.util;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.drug.entity.BranchEmployee;


@Component
public class LogAdvice {
	
	public void after(JoinPoint joinPoint){
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
    	HttpSession session=attr.getRequest().getSession(true);
    	BranchEmployee employee=(BranchEmployee) session.getAttribute("employee");
		if (employee!=null) {
			//获取类名
			String className=joinPoint.getTarget().getClass().getName();
			//获取方法名
			String methodName=joinPoint.getSignature().getName();
			//方法参数
			Object[] objects=joinPoint.getArgs();
			List<Object> List = Arrays.asList(objects);
			Logger logger=Logger.getLogger(LogAdvice.class);
			MDC.put("empName", employee.getEmpName());
			logger.warn("调用了"+className+"类的"+methodName+"方法");
		}
    	
	}
}
