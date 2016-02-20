package com.cmiracle.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
	
	/**
	 * 图标
	 */
	@Column(name = "icon", nullable = true)
	public String icon;
	
	/**
	 * 类型 0 父类型， 1 子类型
	 */
	@Column(name = "type", nullable = true)
	public Integer type;
	
	/**
	 * 类型
	 */
	@ManyToOne(fetch= FetchType.EAGER ,optional=true)
    @JoinColumn(name = "parentProductTypeId", nullable = true)
    @NotFound(action=NotFoundAction.IGNORE)
    public ProductType parentProductType;
	
	@Transient
	public Long parentProductTypeId;

}
