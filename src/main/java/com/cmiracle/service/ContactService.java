package com.cmiracle.service;

import java.util.List;

import com.cmiracle.comment.CommonPage;
import com.cmiracle.domain.Contact;

public interface ContactService extends BaseService<Contact, Long> {
	
	public CommonPage<Contact> findList(Integer page, Integer size);
	
	public List<Contact> findAll();
	
	public List<Contact> findByType(Integer type);
	
}
