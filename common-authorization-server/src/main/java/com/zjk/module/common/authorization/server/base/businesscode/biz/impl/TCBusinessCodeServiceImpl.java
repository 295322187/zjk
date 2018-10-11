package com.zjk.module.common.authorization.server.base.businesscode.biz.impl;


import com.zjk.module.common.authorization.client.serialcode.AuthorizationSerialCode;
import com.zjk.module.common.authorization.server.api.serialcode.biz.ISerialCodeService;
import com.zjk.module.common.authorization.server.base.businesscode.biz.ITCBusinessCodeService;
import com.zjk.module.common.authorization.server.base.businesscode.domain.TCBusinessCode;
import com.zjk.module.common.authorization.server.base.businesscode.repository.ITCBusinessCodeRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TCBusinessCodeServiceImpl extends DataServiceImpl<TCBusinessCode, Integer> implements ITCBusinessCodeService {

	@Autowired
	private ITCBusinessCodeRepository repository;

	@Autowired
	private ISerialCodeService serialCodeService;

	@Override
	public TCBusinessCode newInstance() {
		TCBusinessCode po = new TCBusinessCode();
		po.setCode(serialCodeService.next(AuthorizationSerialCode.CBUSINESSCODE.getSerialGroup()));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCBusinessCode findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCBusinessCode findOneByBusinessClazzAndBusinessCode(String businessClazz, String businessCode) {
		return repository.findOneByBusinessClazzAndBusinessCode(businessClazz, businessCode);
	}
}
