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
import com.cmiracle.domain.Contact;
import com.cmiracle.repository.ContactRepository;

@Service
public class ContactServiceImpl extends AbstractBaseServiceImpl<Contact, Long>implements ContactService {

	@Autowired
	public ContactRepository contactRepository;

	@Override
	public CommonPage<Contact> findList(Integer page, Integer size) {
		// 分页
		page = page - 1 >= 0 ? page - 1 : 0;
		List<Order> sortList = new ArrayList<Order>();
		// 排序
		sortList.add(new Sort.Order(Direction.ASC, "orderIndex"));
		Sort sort = new Sort(sortList);
		PageRequest pageRequest = new PageRequest(page, size, sort);

		// 查询条件
		Specification<Contact> spec = new Specification<Contact>() {
			@Override
			public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Predicate> predicateList = new ArrayList<Predicate>();

				Predicate[] predicates = new Predicate[predicateList.size()];
				for (int i = 0; i < predicateList.size(); i++) {
					predicates[i] = predicateList.get(i);
				}
				predicate = cb.and(predicates);

				return predicate;
			}

		};
		Page<Contact> result = contactRepository.findAll(spec, pageRequest);
		CommonPage<Contact> commonPage = new CommonPage<Contact>(result);
		return commonPage;
	}

	@Override
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

}
