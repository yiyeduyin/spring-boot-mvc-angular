package com.cmiracle.controller.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cmiracle.comment.DTO;
import com.cmiracle.comment.MediaType;
import com.cmiracle.domain.AdminUser;
import com.cmiracle.service.AdminUserService;
import com.cmiracle.util.Util;

@RestController
@RequestMapping("/admin/rest")
public class AdminCtrl {
	
	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping("/adminUser")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}
	
	/**
	 * 更新
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{adminName}/username", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String updateUsername(@PathVariable("adminName") String adminName,
			@RequestBody JSONObject content) {
		DTO dto = DTO.newDTO();
		try {
			String username = content.getString("username");
			String password = content.getString("password");
			
			if(!Util.isNotNull(username) || !Util.isNotNull(password)){
				dto.errMsg = "对不起，信息不完整，请重试";
				dto.errCode = 1;
				return dto.toJson();
			}
			
			AdminUser oldAdminUser = adminUserService.findByUsername(username);
			
			if(!oldAdminUser.role.equals("ADMIN")){
				dto.errMsg = "对不起，您当前操作没有权限";
				dto.errCode = 2;
				return dto.toJson();
			}
			
			//校验原密码
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String oldHashedPassword = passwordEncoder.encode(password);
			if(!oldHashedPassword.equals(oldAdminUser.password)){
				dto.errMsg = "对不起，密码错误";
				dto.errCode = 3;
				return dto.toJson();
			}
			oldAdminUser.username = username;
			adminUserService.update(oldAdminUser);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
	
	/**
	 * 更新密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/password", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String updatePassword(@PathVariable("id") Long id,
			@RequestBody JSONObject content) {
		DTO dto = DTO.newDTO();
		try {
			String oldPassword = content.getString("oldPassword");
			String newPassword = content.getString("newPassword");
			
			if(!Util.isNotNull(oldPassword) || !Util.isNotNull(newPassword)){
				dto.errMsg = "对不起，信息不完整，请重试";
				dto.errCode = 1;
				return dto.toJson();
			}
			
			AdminUser oldAdminUser = adminUserService.get(id);
			
			if(!oldAdminUser.role.equals("ADMIN")){
				dto.errMsg = "对不起，您当前操作没有权限";
				dto.errCode = 2;
				return dto.toJson();
			}
			
			//校验原密码
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String oldHashedPassword = passwordEncoder.encode(oldPassword);
			if(!oldHashedPassword.equals(oldAdminUser.password)){
				dto.errMsg = "对不起，密码错误";
				dto.errCode = 3;
				return dto.toJson();
			}
			//加密密码
			String newHashedPassword = passwordEncoder.encode(newPassword);
			oldAdminUser.username = newHashedPassword;
			adminUserService.update(oldAdminUser);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
}
