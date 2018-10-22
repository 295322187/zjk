package com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.biz.impl;


import com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.biz.ITCUserWeixinService;
import com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.domain.TCUserWeixin;
import com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.repository.ITCUserWeixinRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TCUserWeixinServiceImpl extends DataServiceImpl<TCUserWeixin, Integer> implements ITCUserWeixinService {

	@Autowired
	private ITCUserWeixinRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCUserWeixin findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCUserWeixin findOneByOpenid(String idCard) {
		return repository.findOneByOpenid(idCard);
	}

	@Override
	@Transactional
	public void deleteByCode(String code) {
		repository.deleteByCode(code);
	}
}
