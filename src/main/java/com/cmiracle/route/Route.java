package com.cmiracle.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Route{

	@RequestMapping("/")
	public String index() {
		System.out.println("hello spring-boot");
		return "index";
	}
	
	@RequestMapping("/index2")
	public String index2() {
		return "index2";
	}
	
	@RequestMapping("/user")
	public String userIndex() {
		return "user/userIndex";
	}
	
	@RequestMapping("/login")
	public String adminLogin() {
		return "admin/adminIndex";
	}
	
	@RequestMapping("/logout")
	public String adminLogout() {
		return "admin/adminIndex";
	}
	
	@RequestMapping("/admin/index")
	public String adminIndex() {
		return "admin/adminIndex";
	}
}
