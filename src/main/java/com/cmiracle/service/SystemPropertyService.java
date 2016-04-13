package com.cmiracle.service;

import com.cmiracle.domain.SystemProperty;

public interface SystemPropertyService extends BaseService<SystemProperty, Long> {

	
	public SystemProperty findByCode(String code);
}
