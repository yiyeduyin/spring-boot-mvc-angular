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
import com.cmiracle.domain.Certificates;
import com.cmiracle.repository.CertificatesRepository;
import com.cmiracle.util.Util;

@Service
public class CertificatesServiceImpl extends AbstractBaseServiceImpl<Certificates, Long>implements CertificatesService {

	@Autowired
	private CertificatesRepository certificatesRepository;

	@Override
	public CommonPage<Certificates> findList(Integer page, Integer size, String name) {
		// 分页
		page = page - 1 >= 0 ? page - 1 : 0;
		List<Order> sortList = new ArrayList<Order>();
		// 排序
		sortList.add(new Sort.Order(Direction.ASC, "orderd"));
		Sort sort = new Sort(sortList);
		PageRequest pageRequest = new PageRequest(page, size, sort);

		// 查询条件
		Specification<Certificates> spec = new Specification<Certificates>() {
			@Override
			public Predicate toPredicate(Root<Certificates> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		Page<Certificates> result = certificatesRepository.findAll(spec, pageRequest);
		CommonPage<Certificates> commonPage = new CommonPage<Certificates>(result);
		return commonPage;
	}

}
