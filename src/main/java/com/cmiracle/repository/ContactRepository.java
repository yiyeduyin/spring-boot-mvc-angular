package com.cmiracle.repository;

import java.util.List;

import com.cmiracle.domain.Contact;

public interface ContactRepository extends BaseRepository<Contact,Long>{
	
	List<Contact> findByTypeOrderByOrderIndexAsc(Integer type);

}
