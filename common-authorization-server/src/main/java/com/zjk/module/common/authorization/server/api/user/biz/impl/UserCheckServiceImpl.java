package com.zjk.module.common.authorization.server.api.user.biz.impl;

import com.zjk.module.common.authorization.client.exception.AuthorizationCode;
import com.zjk.module.common.authorization.server.api.role.biz.IRoleService;
import com.zjk.module.common.authorization.server.api.user.biz.IUserCheckService;
import com.zjk.module.common.authorization.server.api.userrole.biz.IUserRoleService;
import com.zjk.module.common.authorization.server.base.user.biz.ITCUserService;
import com.zjk.module.common.authorization.server.base.usersettings.biz.ITCUserSettingsService;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCheckServiceImpl extends CommonServiceImpl implements IUserCheckService {

	@Autowired
	private ITCUserService service;
	@Autowired
	private ITCUserSettingsService userSettingsService;

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserRoleService userRoleService;

	/********** 检查（不存在） **********/

	@Override
	public void isNotExistMobile(String mobile) {
		checkIfNotNullThrowException(service.findOneByMobile(mobile), new BusinessException(AuthorizationCode.PP0003, new Object[]{mobile}));
	}

	@Override
	public void isNotExistEmail(String email) {
		checkIfNotNullThrowException(service.findOneByEmail(email), new BusinessException(AuthorizationCode.PP0004, new Object[]{email}));
	}

	@Override
	public void isNotExistIdCard(String idCard) {
		checkIfNotNullThrowException(userSettingsService.findOneByIdCard(idCard), new BusinessException(AuthorizationCode.PP0005, new Object[]{idCard}));
	}

	/********** 检查（存在） **********/

	@Override
	public void existMobile(String mobile) {
		checkIfNullThrowException(service.findOneByMobile(mobile), new BusinessException(AuthorizationCode.PP0006, new Object[]{mobile}));
	}

	@Override
	public void existEmail(String email) {
		checkIfNullThrowException(service.findOneByEmail(email), new BusinessException(AuthorizationCode.PP0007, new Object[]{email}));
	}

	@Override
	public void existIdCard(String idCard) {
		checkIfNullThrowException(userSettingsService.findOneByIdCard(idCard), new BusinessException(AuthorizationCode.PP0008, new Object[]{idCard}));
	}

	@Override
	public void existCode(String code) {
		checkIfNullThrowException(service.findOneByCode(code), new BusinessException(AuthorizationCode.PP0009, new Object[]{code}));
	}

	@Override
	public void existUserRole(String userCode, String role) {
		checkIfNullThrowException(
				userRoleService.findOneByUserCodeAndRoleCode(userCode,
						checkIfNullThrowException(roleService.findOneByRole(role),
								new BusinessException(AuthorizationCode.PP0010, new Object[]{role})).getCode()),
				new BusinessException(AuthorizationCode.PP0011, new Object[]{userCode, role}));
	}
}
