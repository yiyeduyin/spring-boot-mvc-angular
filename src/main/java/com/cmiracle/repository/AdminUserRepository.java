package com.cmiracle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmiracle.domain.AdminUser;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

	public AdminUser findByUsername(String username);

}