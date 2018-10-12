package com.zjk.module.common.authorization.server.api.businesscode.biz;


import com.zjk.module.common.authorization.client.api.businesscode.domain.BusinessCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBusinessCodeService {

	Page<BusinessCode> findAll(Pageable pageable);

	void refresh();

	void save(BusinessCode vo);

}
