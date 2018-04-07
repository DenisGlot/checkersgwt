package com.checkersgwt.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class WebMainController {
	
	protected static Logger logger = LoggerFactory.getLogger(WebMainController.class);
	
//	@Resource
//	private ProjectConfiguration projectConfiguration;
	
	@RequestMapping(value="error404", method = RequestMethod.GET)
	public ModelAndView getError404() {
		ModelAndView model = new ModelAndView("error404");
//		model.addObject("siteDomain", projectConfiguration.getSiteDomain());
//		model.addObject("domain", projectConfiguration.getDomain());
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getApplicationPage(ModelMap model) {
		return "redirect:/app/";
	}
	
}