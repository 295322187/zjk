package com.zjk.module.common.authorization.server.base.method.biz.impl;

import com.zjk.module.common.authorization.client.serialcode.AuthorizationSerialCode;
import com.zjk.module.common.authorization.server.api.serialcode.biz.ISerialCodeService;
import com.zjk.module.common.authorization.server.base.method.biz.ITCMethodService;
import com.zjk.module.common.authorization.server.base.method.domain.TCMethod;
import com.zjk.module.common.authorization.server.base.method.repository.ITCMethodRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TCMethodServiceImpl extends DataServiceImpl<TCMethod, Integer> implements ITCMethodService {

	@Autowired
	private ITCMethodRepository repository;

	@Autowired
	private ISerialCodeService serialCodeService;

	@Override
	public TCMethod newInstance() {
		TCMethod po = new TCMethod();
		po.setCode(serialCodeService.next(AuthorizationSerialCode.CMETHOD.getSerialGroup()));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCMethod findOneByApplicationNameAndMethodAndAndPattern(String applicationName, String method, String pattern) {
		return repository.findOneByApplicationNameAndMethodAndAndPattern(applicationName, method, pattern);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TCMethod> findAllByApplicationName(String applicationName) {
		return repository.findAllByApplicationName(applicationName);
	}
}
