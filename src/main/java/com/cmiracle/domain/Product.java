package com.cmiracle.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 商品
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_product")
public class Product implements Serializable {

	private static final long serialVersionUID = -7320952630208727402L;

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
	 * 描述
	 */
	@Column(name = "description", nullable = true)
	public String description;

	/**
	 * 规格
	 */
	@Column(name = "spec", nullable = true)
	public String spec;

	/**
	 * 图标
	 */
	@Column(name = "icon", nullable = true)
	public String icon;

	/**
	 * 文件名
	 */
	@Column(name = "fileName", nullable = true)
	public String fileName;

	/**
	 * 照片
	 */
	@Column(name = "pictures", nullable = true)
	public String pictures;

	/**
	 * 状态 0 不显示，1 显示
	 */
	@Column(name = "status", nullable = true)
	public Integer status;

	/**
	 * 是否新产品 1 是，0否
	 */
	@Column(name = "isNew", nullable = false)
	public Integer isNew;

	/**
	 * 创建日期
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created", nullable = true)
	public Date created;
	
	/**
	 * 类型
	 */
	@OneToOne(fetch= FetchType.EAGER,optional=true)
    @JoinColumn(name = "productTypeId", nullable = false)
    @NotFound(action=NotFoundAction.IGNORE)
    public ProductType productType;
	
	@Transient
	public Long productTypeId;
}
