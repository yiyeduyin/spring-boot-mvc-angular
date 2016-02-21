package com.cmiracle.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cmiracle.comment.CommonPage;
import com.cmiracle.domain.Product;
import com.cmiracle.domain.ProductType;
import com.cmiracle.repository.ProductRepository;
import com.cmiracle.util.Util;

@Service
public class ProductServiceImpl extends AbstractBaseServiceImpl<Product, Long>implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public CommonPage<Product> findList(Integer page, Integer size, Integer productType, Integer subProductType, String name, Integer isNew,
			Integer status) {
		// 分页
		page = page - 1 >= 0 ? page - 1 : 0;
		List<Order> sortList = new ArrayList<Order>();
		// 排序
		sortList.add(new Sort.Order(Direction.DESC, "id"));
		Sort sort = new Sort(sortList);
		PageRequest pageRequest = new PageRequest(page, size, sort);

		// 查询条件
		Specification<Product> spec = new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Predicate> predicateList = new ArrayList<Predicate>();

				if (Util.isNotNull(productType)) {
					predicateList
							.add(cb.equal(root.<ProductType> get("productType").get("id"), productType));
				}
				
				if (Util.isNotNull(subProductType)) {
					predicateList
							.add(cb.equal(root.<ProductType> get("subProductType").get("id"), subProductType));
				}

				if (Util.isNotNull(name)) {
					predicateList.add(cb.like(root.<String> get("name"), "%" + name + "%"));
				}

				if (Util.isNotNull(status)) {
					predicateList.add(cb.equal(root.<Integer> get("status"), status));
				}

				if (Util.isNotNull(isNew)) {
					predicateList.add(cb.equal(root.<Integer> get("isNew"), isNew));
				}

				Predicate[] predicates = new Predicate[predicateList.size()];
				for (int i = 0; i < predicateList.size(); i++) {
					predicates[i] = predicateList.get(i);
				}
				predicate = cb.and(predicates);

				return predicate;
			}

		};
		Page<Product> result = productRepository.findAll(spec, pageRequest);
		CommonPage<Product> commonPage = new CommonPage<Product>(result);
		return commonPage;
	}

	@Override
	public List<Product> findByProductType(Long tid) {
		return productRepository.findByProductType(tid);
	}

}
