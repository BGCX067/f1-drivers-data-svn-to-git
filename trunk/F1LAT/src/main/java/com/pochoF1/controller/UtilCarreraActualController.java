package com.pochoF1.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.gson.Gson;
import com.pochoF1.xml.Race;

@Controller
public class UtilCarreraActualController {
	
	private static Logger log = LoggerFactory.getLogger(UtilCarreraActualController.class);

	@RequestMapping("/utilCarreraActualController")
	public void utilCarreraActualController(HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		HttpSession session = request.getSession();
		try {
			String json = new Gson().toJson((Race)session.getAttribute("race"));

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
	}

}