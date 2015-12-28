package com.pochoF1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

	@RequestMapping(value = "/hi", method = RequestMethod.POST)
	  public String hi(@RequestParam("name") String name, Model model) {
	    List<String> palabras = new ArrayList<String>();
	    palabras.add("asd");
	    palabras.add("asd2");
	    model.addAttribute("message", palabras);
	    return "hi";
	  }
	
}
