package com.cmiracle.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理员表
 * @author chenjiarong
 *
 */
@Entity
@Table(name = "t_user")
public class AdminUser implements Serializable {

	private static final long serialVersionUID = -719995947047957874L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	/**
	 * 用户名
	 */
	@Column(name = "username", nullable = false, unique = true)
	public String username;

	/**
	 * 密码
	 */
	@Column(name = "password", nullable = false)
	public String password;
	
	/**
	 * 角色
	 */
	@Column(name = "role", nullable = false)
	public String role;

	/**
	 * 是否可用
	 */
	@Column(name = "enabled", nullable = false)
	public Boolean enabled;
	
	@Column(name = "created", nullable = false)
	public Date created;
}
