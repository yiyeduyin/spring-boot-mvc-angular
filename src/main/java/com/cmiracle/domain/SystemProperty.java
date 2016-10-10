package com.cmiracle.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 简介
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_systemProperty")
public class SystemProperty implements Serializable  {
	
	private static final long serialVersionUID = 13L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Long id;
	
	
	/**
	 * 标识
	 */
	@Column(name = "code", nullable = false)
	public String code;
	
	
	/**
	 * 图片
	 */
	@Column(name = "icon", nullable = true)
	public String icon;

	/**
	 * 介绍
	 */
	@Column(name = "value", nullable = true)
	public String value;

	
	

}
