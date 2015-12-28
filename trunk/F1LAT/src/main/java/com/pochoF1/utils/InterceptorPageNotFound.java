package com.pochoF1.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

public class InterceptorPageNotFound  extends HandlerInterceptorAdapter{

	public String exception6(HttpServletRequest request, HttpServletResponse response) throws NoSuchRequestHandlingMethodException
	{
		throw new NoSuchRequestHandlingMethodException("Test for DefaultHandlerExceptionResolver", null);
	}

}
