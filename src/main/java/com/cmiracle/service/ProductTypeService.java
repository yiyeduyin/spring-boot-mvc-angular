package com.cmiracle.service;

import java.util.List;

import com.cmiracle.comment.CommonPage;
import com.cmiracle.domain.ProductType;

public interface ProductTypeService extends BaseService<ProductType, Long> {
	
	public void reloadCache();
	
	public List<ProductType> findByParentProductType(Long pid);
	
	public CommonPage<ProductType> findList(Integer page, Integer size, String name, Integer status, Integer type, Integer parentProductTypeId);
	
}
