package com.sm.app.transmanage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	private static final String INDEX_PAGE = "index";

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView renderHome(ModelAndView modelAndView) {
		modelAndView.setViewName(INDEX_PAGE);
		return modelAndView;
	}

}
