package com.cmiracle.repository;

import com.cmiracle.domain.SystemProperty;

public interface SystemPropertyRepository extends BaseRepository<SystemProperty,Long>{

	public SystemProperty findFirstByCode(String code);
	
}
