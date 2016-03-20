package com.cmiracle.service;

import com.cmiracle.comment.CommonPage;
import com.cmiracle.domain.Message;

public interface MessageService extends BaseService<Message, Long> {

	public CommonPage<Message> findList(Integer page, Integer size, String username, String mobile, String address,
			String email, String content, Integer type, Integer status);
}
