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
 * 留言
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_message")
public class Message implements Serializable {
	
	private static final long serialVersionUID = 12L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Long id;

	/**
	 * 用户名字
	 */
	@Column(name = "username", nullable = true)
	public String username;

	/**
	 * 电话
	 */
	@Column(name = "mobile", nullable = false)
	public String mobile;

	/**
	 * 地址
	 */
	@Column(name = "address", nullable = true)
	public String address;

	/**
	 * 邮箱
	 */
	@Column(name = "email", nullable = true)
	public String email;

	/**
	 * 内容
	 */
	@Column(name = "content", nullable = false)
	public String content;
	
	/**
	 * 主题 0 简单的留言， 1 对网站的意见,  2 对公司的建议， 3 具有合作意向, 4 产品投诉， 5 服务投诉， 6 其它   
	 */
	@Column(name = "type", nullable = false, length = 4000)
	public Integer type;
	
	/**
	 * 状态 0 不显示， 1 显示
	 */
	@Column(name = "status", nullable = true)
	public Integer status;
	
	/**
	 * 时间
	 */
	@Column(name = "created", nullable = false)
	public Date created;

}
