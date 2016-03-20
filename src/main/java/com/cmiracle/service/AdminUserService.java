package com.cmiracle.service;

import com.cmiracle.domain.AdminUser;

public interface AdminUserService extends BaseService<AdminUser, Long> {

	public AdminUser findByUsername(String username);
}
