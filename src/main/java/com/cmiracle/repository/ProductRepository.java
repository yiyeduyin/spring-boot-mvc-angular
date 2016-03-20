package com.cmiracle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cmiracle.domain.Product;

public interface ProductRepository extends BaseRepository<Product,Long>{

	@Query("from Product p where p.productType.id = ?1 or p.subProductType.id = ?1")
	public List<Product> findByProductType(Long tid);
}
