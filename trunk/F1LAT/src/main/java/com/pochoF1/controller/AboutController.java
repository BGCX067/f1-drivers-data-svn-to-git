package com.pochoF1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.pochoF1.thread.ThreadEnviarMail;

@Controller
public class AboutController {

	private static Logger log = LoggerFactory.getLogger(AboutController.class);

	@RequestMapping(value = "/about")
	public String showRaces(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		model.addAttribute("MOSTRAR",false);
		
		return "about";
	}
	
	
	@RequestMapping(value = "/enviarMail" , method = RequestMethod.POST)
	public String enviarMail(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		WebApplicationContext appCtx = RequestContextUtils.getWebApplicationContext(request);
		Boolean ok = true;
		
		String emailContacto = request.getParameter("email");
		String mensaje = request.getParameter("message");
		String nombreContacto = request.getParameter("name");
		
		if(StringUtils.isNotEmpty(emailContacto) && StringUtils.isNotEmpty(mensaje) && StringUtils.isNotEmpty(nombreContacto)){
			
			Runnable r = new ThreadEnviarMail(emailContacto,mensaje,nombreContacto);
			Thread thread = new Thread(r,"Thread Enviar Mail");
			thread.start();
			
		}else{
			ok = false;
			log.error(appCtx.getMessage("error.about.send.email.fields.missing",null,"All the fields must be completed",request.getLocale()));
			model.addAttribute("ERROR", appCtx.getMessage("error.about.send.email.fields.missing",null,"All the fields must be completed",request.getLocale()));
		}
		
		model.addAttribute("MOSTRAR",true);
		model.addAttribute("OK",ok);
		return "about";
	}

}
