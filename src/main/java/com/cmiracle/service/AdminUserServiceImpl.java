package com.cmiracle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmiracle.domain.AdminUser;
import com.cmiracle.repository.AdminUserRepository;

@Service
public class AdminUserServiceImpl extends AbstractBaseServiceImpl<AdminUser, Long> implements AdminUserService {

	@Autowired
	protected AdminUserRepository adminUserRepository;
	
	@Override
	public AdminUser findByUsername(String username) {
		return adminUserRepository.findByUsername(username);
	}

}
