package com.zjk.module.common.authorization.server.base.role.biz.impl;

import com.zjk.module.common.authorization.client.serialcode.AuthorizationSerialCode;
import com.zjk.module.common.authorization.server.api.serialcode.biz.ISerialCodeService;
import com.zjk.module.common.authorization.server.base.role.biz.ITCRoleService;
import com.zjk.module.common.authorization.server.base.role.domain.TCRole;
import com.zjk.module.common.authorization.server.base.role.repository.ITCRoleRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TCRoleServiceImpl extends DataServiceImpl<TCRole, Integer> implements ITCRoleService {

	@Autowired
	private ITCRoleRepository repository;

	@Autowired
	private ISerialCodeService serialCodeService;

	@Override
	public TCRole newInstance() {
		TCRole po = new TCRole();
		po.setCode(serialCodeService.next(AuthorizationSerialCode.CROLE.getSerialGroup()));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCRole findOneByCode(String code) {
		return repository.findOneByCode(code);
	}


	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCRole findOneByRole(String role) {
		return repository.findOneByRole(role);
	}
}
