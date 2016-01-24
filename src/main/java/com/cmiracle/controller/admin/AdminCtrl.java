package com.cmiracle.controller.admin;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/rest")
public class AdminCtrl {

	@RequestMapping("/adminUser")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}
}
