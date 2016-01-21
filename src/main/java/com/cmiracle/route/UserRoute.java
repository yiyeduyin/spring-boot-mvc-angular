package com.cmiracle.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserRoute {
	
	private final String userPageRoutePrefix = "user/";
	
	@RequestMapping("/")
	public String index() {
		return userPageRoutePrefix + "index";
	}

}
