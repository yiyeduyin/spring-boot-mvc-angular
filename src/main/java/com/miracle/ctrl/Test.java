package com.miracle.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

	

	@RequestMapping("/admin")
	public String adminIndex() {
		return "adminIndex";
	}

}
