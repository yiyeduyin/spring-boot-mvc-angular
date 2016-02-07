package com.cmiracle.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 默认密码生成器
 * 
 * @author cmiracle
 *
 */
public class PasswordEncoderGenerator {

	public static void main(String[] args) {
		String password = "123456";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
	}

}
