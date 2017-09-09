package com.wanderingwonderland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wanderingwonderland.service.AboutService;

@Controller
public class AboutController {

	@Autowired
	private AboutService aboutService;
	
	@RequestMapping(value="/about", method=RequestMethod.GET)
	public String home(Model model) {		
		model.addAttribute("content", aboutService.aboutPageContent().subList(0, 1).get(0));
		return "pages/about";
	}
}
