package com.zjk.module.common.authorization.server.base.user.biz.impl;


import com.zjk.module.common.authorization.client.exception.AuthorizationSerialCode;
import com.zjk.module.common.authorization.server.api.serialcode.biz.ISerialCodeService;
import com.zjk.module.common.authorization.server.base.user.biz.ITCUserService;
import com.zjk.module.common.authorization.server.base.user.domain.TCUser;
import com.zjk.module.common.authorization.server.base.user.repository.ITCUserRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TCUserServiceImpl extends DataServiceImpl<TCUser, Integer> implements ITCUserService {

	@Autowired
	private ITCUserRepository repository;

	@Autowired
	private ISerialCodeService serialCodeService;

	@Override
	public TCUser newInstance() {
		TCUser po = new TCUser();
		po.setCode(serialCodeService.next(AuthorizationSerialCode.CUSER.getSerialGroup()));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCUser findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCUser findOneByUsername(String username) {
		return repository.findOneByUsername(username);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCUser findOneByEmail(String email) {
		return repository.findOneByEmail(email);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCUser findOneByMobile(String mobile) {
		return repository.findOneByMobile(mobile);
	}

	@Override
	@Transactional
	public void deleteByCode(String code) {
		repository.deleteByCode(code);
	}
}
