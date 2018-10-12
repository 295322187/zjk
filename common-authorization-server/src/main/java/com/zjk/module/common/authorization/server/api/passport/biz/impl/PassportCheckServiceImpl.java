package com.zjk.module.common.authorization.server.api.passport.biz.impl;

import com.zjk.module.common.authorization.server.api.passport.biz.IPassportCheckService;
import com.zjk.module.common.authorization.server.api.user.biz.IUserCheckService;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassportCheckServiceImpl extends CommonServiceImpl implements IPassportCheckService {

	@Autowired
	private IUserCheckService service;

	/********** 检查（不存在） **********/

	@Override
	public void isNotExistMobile(String mobile) {
		service.isNotExistMobile(mobile);
	}

	@Override
	public void isNotExistEmail(String email) {
		service.isNotExistEmail(email);
	}

	@Override
	public void isNotExistIdCard(String idCard) {
		service.isNotExistIdCard(idCard);
	}

	/********** 检查（存在） **********/

	@Override
	public void existMobile(String mobile) {
		service.existMobile(mobile);
	}

	@Override
	public void existEmail(String email) {
		service.existEmail(email);
	}

	@Override
	public void existIdCard(String idCard) {
		service.existIdCard(idCard);
	}

	@Override
	public void existCode(String code) {
		service.existCode(code);
	}

	@Override
	public void existUserRole(String userCode, String role) {
		service.existUserRole(userCode, role);
	}
}
