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
@Table(name = "t_engineerings")
public class Engineerings implements Serializable  {
	
	private static final long serialVersionUID = 12L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Long id;

	/**
	 * 工程技术
	 */
	@Column(name = "name", nullable = true)
	public String name;

	/**
	 * 工程技术图片
	 */
	@Column(name = "picture", nullable = false)
	public String picture;
	
	/**
	 * 排列序号：从小到大
	 */
	@Column(name = "orderd", nullable = false)
	public Integer orderd;

}
