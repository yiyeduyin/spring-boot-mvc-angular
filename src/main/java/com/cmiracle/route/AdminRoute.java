package com.cmiracle.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminRoute{
	
	private final String adminPageRoutePrefix = "admin/";

	@RequestMapping("")
	public String index() {
		return adminPageRoutePrefix + "index";
	}
	
}
