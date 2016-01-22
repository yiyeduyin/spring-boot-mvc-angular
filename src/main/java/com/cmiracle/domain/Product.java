package com.cmiracle.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 商品
 * @author Administrator
 *
 */
@Entity
@Table(name="t_product")
public class Product implements Serializable{

	private static final long serialVersionUID = -7320952630208727402L;
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	 * 图标
	 */
	@Column(name = "icon", nullable = true)
	public String icon;
	
	/**
	 * 照片
	 */
	@Column(name = "pictures", nullable = true)
	public String pictures;
	
	/**
	 * 显示价格
	 */
	@Column(name = "salePrice", nullable = true)
	public Float salePrice;
	
	/**
	 * 真实价格
	 */
	@Column(name = "realPrice", nullable = true)
	public Float realPrice;
	
	/**
	 * 状态
	 */
	@Column(name = "product_status", nullable = true)
	public Integer productStatus;
	
	/**
	 * 库存
	 */
	@Column(name = "product_stock", nullable = true)
	public Integer productStock;
	
	/**
	 * 是否新产品 1 是，0否
	 */
	@Column(name = "isNew", nullable = false)
	public Integer isNew;
	
	/**
	 * 生产日期
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "productionDate", nullable = true)
	public Date productionDate;
	
	/**
	 * 创建日期
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "created", nullable = true)
	public Date created;
}
