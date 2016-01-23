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
	 * 状态 0 不显示，1 显示
	 */
	@Column(name = "status", nullable = true)
	public Integer status;
	
	/**
	 * 库存
	 */
	@Column(name = "stock", nullable = true)
	public Integer stock;
	
	/**
	 * 是否新产品 1 是，0否
	 */
	@Column(name = "isNew", nullable = false)
	public Integer isNew;
	
	
	/**
	 * 
	 */
	@Column(name = "drawing", nullable = true)
	public String drawing;
	
	/**
	 * 
	 */
	@Column(name = "coretype", nullable = true)
	public String coretype;
	
	/**
	 * 
	 */
	@Column(name = "row", nullable = true)
	public String row;
	
	/**
	 * 
	 */
	@Column(name = "pitch", nullable = true)
	public String pitch;
	
	/**
	 * 
	 */
	@Column(name = "pins", nullable = true)
	public String pins;
	
	/**
	 * 
	 */
	@Column(name = "style", nullable = true)
	public String style;
	
	/**
	 * 
	 */
	@Column(name = "ml", nullable = true)
	public String ml;
	
	/**
	 * 
	 */
	@Column(name = "sec", nullable = true)
	public String sec;
	
	/**
	 * 
	 */
	@Column(name = "drawingPath", nullable = true)
	public String drawingPath;
	
	/**
	 * 
	 */
	@Column(name = "headDrawingPath", nullable = true)
	public String headDrawingPath;
	
	/**
	 * 创建日期
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "created", nullable = true)
	public Date created;
	
//	@ManyToOne(fetch = FetchType.EAGER, optional = false)
//	@JoinColumn(name="productTypeId",referencedColumnName="id" ,nullable=false)
//	public ProductType productType;
	
	/**
	 * 类型ID
	 */
	@Column(name = "productTypeId", nullable = false)
	public Long productTypeId;
}
