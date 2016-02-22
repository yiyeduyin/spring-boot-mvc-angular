package com.cmiracle.service;

import com.cmiracle.comment.CommonPage;
import com.cmiracle.domain.Engineerings;

public interface EngineeringsService extends BaseService<Engineerings, Long> {

	public CommonPage<Engineerings> findList(Integer page, Integer size, String name);
}
