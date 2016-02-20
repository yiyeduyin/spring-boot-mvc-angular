package com.cmiracle.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 证书
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_certificates")
public class Certificates implements Serializable  {
	
	private static final long serialVersionUID = 12L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Long id;

	/**
	 * 证书名称
	 */
	@Column(name = "name", nullable = true)
	public String name;

	/**
	 * 证书图片
	 */
	@Column(name = "picture", nullable = false)
	public String picture;
	
	/**
	 * 排列序号：从小到大
	 */
	@Column(name = "orderd", nullable = false)
	public Integer orderd;

}
