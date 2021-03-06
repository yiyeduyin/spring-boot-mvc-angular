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
import com.cmiracle.domain.Engineerings;
import com.cmiracle.repository.EngineeringsRepository;
import com.cmiracle.util.Util;

@Service
public class EngineeringsServiceImpl extends AbstractBaseServiceImpl<Engineerings, Long>implements EngineeringsService {

	@Autowired
	private EngineeringsRepository engineeringsRepository;

	@Override
	public CommonPage<Engineerings> findList(Integer page, Integer size, String name) {
		// 分页
		page = page - 1 >= 0 ? page - 1 : 0;
		List<Order> sortList = new ArrayList<Order>();
		// 排序
		sortList.add(new Sort.Order(Direction.ASC, "orderd"));
		Sort sort = new Sort(sortList);
		PageRequest pageRequest = new PageRequest(page, size, sort);

		// 查询条件
		Specification<Engineerings> spec = new Specification<Engineerings>() {
			@Override
			public Predicate toPredicate(Root<Engineerings> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Predicate> predicateList = new ArrayList<Predicate>();

				if (Util.isNotNull(name)) {
					predicateList.add(cb.like(root.<String> get(name), "%" + name + "%"));
				}

				Predicate[] predicates = new Predicate[predicateList.size()];
				for (int i = 0; i < predicateList.size(); i++) {
					predicates[i] = predicateList.get(i);
				}
				predicate = cb.and(predicates);

				return predicate;
			}

		};
		Page<Engineerings> result = engineeringsRepository.findAll(spec, pageRequest);
		CommonPage<Engineerings> commonPage = new CommonPage<Engineerings>(result);
		return commonPage;
	}

}
