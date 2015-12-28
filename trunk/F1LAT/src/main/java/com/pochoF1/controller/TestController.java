package com.pochoF1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.pochoF1.daos.ResultadoPilotoDAO;
import com.pochoF1.enums.PilotosEnum;
import com.pochoF1.stats.ResultadoPiloto;

@Controller
public class TestController {

	private static Logger log = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "/testDriversInfo")
	public String showRaces(Model model, HttpServletRequest request, HttpServletResponse response) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, request.getLocale());
		model.addAttribute("MOSTRAR",false);
		
		List<ResultadoPiloto> resultadoPiloto;
		try {
			resultadoPiloto = ResultadoPilotoDAO.getInstance().getResultadosByNombrePiloto(PilotosEnum.hamilton.toString());
			model.addAttribute("resultadoPiloto",resultadoPiloto);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
		return "testDriverInfo";
	}
	
}
