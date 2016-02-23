package com.cmiracle.service;

import java.util.List;

import com.cmiracle.comment.CommonPage;
import com.cmiracle.domain.Product;

public interface ProductService extends BaseService<Product, Long> {

	public CommonPage<Product> findList(Integer page, Integer size, Integer productType, Integer subProductType, String name, String drawingNo, Integer isNew, Integer status);
	
	public List<Product> findByProductType(Long tid);

}
