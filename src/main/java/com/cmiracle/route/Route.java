package com.cmiracle.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Route{

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String adminLogin() {
		return "admin/adminIndex";
	}
	
	@RequestMapping("/logout")
	public String adminLogout() {
		return "admin/adminIndex";
	}
	
	@RequestMapping("/admin")
	public String adminIndex() {
		return "admin/adminIndex";
	}
}
