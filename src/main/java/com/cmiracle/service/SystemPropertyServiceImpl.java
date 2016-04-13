package com.cmiracle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmiracle.domain.SystemProperty;
import com.cmiracle.repository.SystemPropertyRepository;

@Service
public class SystemPropertyServiceImpl extends AbstractBaseServiceImpl<SystemProperty, Long>implements SystemPropertyService {

	@Autowired
	private SystemPropertyRepository systemPropertyRepository;
	
	@Override
	public SystemProperty findByCode(String code) {
		return systemPropertyRepository.findFirstByCode(code);
	}
	

}
