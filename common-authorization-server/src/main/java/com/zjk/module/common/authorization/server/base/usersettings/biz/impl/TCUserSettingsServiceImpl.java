package com.zjk.module.common.authorization.server.base.usersettings.biz.impl;


import com.zjk.module.common.authorization.server.base.usersettings.biz.ITCUserSettingsService;
import com.zjk.module.common.authorization.server.base.usersettings.domain.TCUserSettings;
import com.zjk.module.common.authorization.server.base.usersettings.repository.ITCUserSettingsRepository;
import com.zjk.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TCUserSettingsServiceImpl extends DataServiceImpl<TCUserSettings, Integer> implements ITCUserSettingsService {

	@Autowired
	private ITCUserSettingsRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCUserSettings findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCUserSettings findOneByIdCard(String idCard) {
		return repository.findOneByIdCard(idCard);
	}

	@Override
	@Transactional
	public void deleteByCode(String code) {
		repository.deleteByCode(code);
	}
}
