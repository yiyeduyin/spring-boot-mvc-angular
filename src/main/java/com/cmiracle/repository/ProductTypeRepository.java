package com.cmiracle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cmiracle.domain.ProductType;

public interface ProductTypeRepository extends BaseRepository<ProductType,Long>{
	
	@Query("from ProductType p where p.parentProductType.id = ?1")
	public List<ProductType> findByParentProductType(Long pid);

}
