package com.cmiracle.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cmiracle.comment.CommonPage;
import com.cmiracle.domain.ProductType;
import com.cmiracle.repository.ProductTypeRepository;
import com.cmiracle.util.Util;

@Service
public class ProductTypeServiceImpl extends AbstractBaseServiceImpl<ProductType, Long>implements ProductTypeService {

	private static final Logger logger = LoggerFactory.getLogger(ProductTypeServiceImpl.class);

	
	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Override
	@Cacheable("productTypes")
	public CommonPage<ProductType> findList(Integer page, Integer size, String name, Integer status, Integer type,
			Integer parentProductTypeId) {
		// 分页
		page = page - 1 >= 0 ? page - 1 : 0;
		List<Order> sortList = new ArrayList<Order>();
		// 排序
		sortList.add(new Sort.Order(Direction.DESC, "id"));
		Sort sort = new Sort(sortList);
		PageRequest pageRequest = new PageRequest(page, size, sort);

		// 查询条件
		Specification<ProductType> spec = new Specification<ProductType>() {
			@Override
			public Predicate toPredicate(Root<ProductType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Predicate> predicateList = new ArrayList<Predicate>();

				if (Util.isNotNull(name)) {
					predicateList.add(cb.like(root.<String> get("name"), "%" + name + "%"));
				}

				if (Util.isNotNull(status)) {
					predicateList.add(cb.equal(root.<Integer> get("status"), status));
				}

				if (Util.isNotNull(type)) {
					predicateList.add(cb.equal(root.<Integer> get("type"), type));
				}

				if (Util.isNotNull(parentProductTypeId)) {
					predicateList
							.add(cb.equal(root.<ProductType> get("parentProductType").get("id"), parentProductTypeId));
				}

				Predicate[] predicates = new Predicate[predicateList.size()];
				for (int i = 0; i < predicateList.size(); i++) {
					predicates[i] = predicateList.get(i);
				}
				predicate = cb.and(predicates);

				return predicate;
			}

		};
		Page<ProductType> result = productTypeRepository.findAll(spec, pageRequest);
		CommonPage<ProductType> commonPage = new CommonPage<ProductType>(result);
		return commonPage;
	}
	
	@Override
	public List<ProductType> findByParentProductType(Long pid) {
		return productTypeRepository.findByParentProductType(pid);
	}

	@Override
	@CacheEvict(value="productTypes",allEntries=true)// 清空缓存
	public void reloadCache() {
		logger.info("the productTypes cache reload: " + LocalDateTime.now());
	}

	
	
	
}
