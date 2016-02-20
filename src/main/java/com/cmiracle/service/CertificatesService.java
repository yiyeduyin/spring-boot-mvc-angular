package com.cmiracle.service;

import com.cmiracle.comment.CommonPage;
import com.cmiracle.domain.Certificates;

public interface CertificatesService extends BaseService<Certificates, Long> {

	public CommonPage<Certificates> findList(Integer page, Integer size, String name);
}
