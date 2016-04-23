package com.cmiracle.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 联系我们
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_contact")
public class Contact implements Serializable {

	private static final long serialVersionUID = 12L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Long id;

	/**
	 * 公司简称
	 */
	@Column(name = "name", nullable = true)
	public String name;
	
	/**
	 * 公司全称
	 */
	@Column(name = "detailName", nullable = true)
	public String detailName;

	/**
	 * 地址
	 */
	@Column(name = "address", nullable = true)
	public String address;
	
	/**
	 * 电话
	 */
	@Column(name = "mobile", nullable = false)
	public String mobile;
	
	/**
	 * 传真
	 */
	@Column(name = "fax", nullable = false)
	public String fax;

	/**
	 * 邮箱
	 */
	@Column(name = "email", nullable = true)
	public String email;
	
	/**
	 * 网站
	 */
	@Column(name = "website", nullable = true)
	public String website;

	
	/**
	 * 图片
	 */
	@Column(name = "icon", nullable = true)
	public String icon;
	
	/**
	 * 排序
	 */
	@Column(name = "orderIndex", nullable = true)
	public String orderIndex;
	
	/**
	 * 类型 (1中文，2英文)
	 */
	@Column(name = "type", nullable = true)
	public Integer type;

	

}
