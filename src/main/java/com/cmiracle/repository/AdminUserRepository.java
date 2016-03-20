package com.cmiracle.repository;

import org.springframework.stereotype.Repository;

import com.cmiracle.domain.AdminUser;

@Repository
public interface AdminUserRepository extends BaseRepository<AdminUser,Long>{

	public AdminUser findByUsername(String username);

}