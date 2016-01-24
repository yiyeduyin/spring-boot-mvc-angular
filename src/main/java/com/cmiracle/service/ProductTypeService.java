package com.cmiracle.service;

import com.cmiracle.comment.CommonPage;
import com.cmiracle.domain.ProductType;

public interface ProductTypeService extends BaseService<ProductType, Long> {
	
	public CommonPage<ProductType> findList(Integer page, Integer size, String name, Integer status);
}
