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
import com.cmiracle.domain.Message;
import com.cmiracle.repository.MessageRepository;
import com.cmiracle.util.Util;

@Service
public class MessageServiceImpl extends AbstractBaseServiceImpl<Message, Long>implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public CommonPage<Message> findList(Integer page, Integer size, String username, String mobile, String address,
			String email, String content, Integer type, Integer status) {
		// 分页
		page = page - 1 >= 0 ? page - 1 : 0;
		List<Order> sortList = new ArrayList<Order>();
		// 排序
		sortList.add(new Sort.Order(Direction.DESC, "created"));
		Sort sort = new Sort(sortList);
		PageRequest pageRequest = new PageRequest(page, size, sort);

		// 查询条件
		Specification<Message> spec = new Specification<Message>() {
			@Override
			public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Predicate> predicateList = new ArrayList<Predicate>();

				if (Util.isNotNull(username)) {
					predicateList.add(cb.like(root.<String> get(username), "%" + username + "%"));
				}

				if (Util.isNotNull(mobile)) {
					predicateList.add(cb.like(root.<String> get(mobile), "%" + mobile + "%"));
				}

				if (Util.isNotNull(address)) {
					predicateList.add(cb.like(root.<String> get(address), "%" + address + "%"));
				}

				if (Util.isNotNull(email)) {
					predicateList.add(cb.like(root.<String> get(email), "%" + email + "%"));
				}

				if (Util.isNotNull(content)) {
					predicateList.add(cb.like(root.<String> get(content), "%" + content + "%"));
				}

				if (Util.isNotNull(type)) {
					predicateList.add(cb.equal(root.<Integer> get(type.toString()), type));
				}

				if (Util.isNotNull(status)) {
					predicateList.add(cb.equal(root.<Integer> get(status.toString()), status));
				}

				Predicate[] predicates = new Predicate[predicateList.size()];
				for (int i = 0; i < predicateList.size(); i++) {
					predicates[i] = predicateList.get(i);
				}
				predicate = cb.and(predicates);

				return predicate;
			}

		};
		Page<Message> result = messageRepository.findAll(spec, pageRequest);
		CommonPage<Message> commonPage = new CommonPage<Message>(result);
		return commonPage;
	}

}
