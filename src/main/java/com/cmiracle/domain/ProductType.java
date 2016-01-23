package com.cmiracle.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品类型
 * 
 * @author chenjiarong
 *
 */
@Entity
@Table(name = "t_productType")
public class ProductType implements Serializable {

	private static final long serialVersionUID = -7320952630208727472L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Long id;

	/**
	 * 名称
	 */
	@Column(name = "name", nullable = false)
	public String name;

	/**
	 * 状态 0 不显示， 1 显示
	 */
	@Column(name = "status", nullable = true)
	public Integer status;

}
