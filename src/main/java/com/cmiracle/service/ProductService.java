package com.cmiracle.service;

import com.cmiracle.comment.CommonPage;
import com.cmiracle.domain.Product;

public interface ProductService extends BaseService<Product, Long> {

	public CommonPage<Product> findList(Integer page, Integer size, String name, Integer isNew, Integer status);

}
