package com.zjk.module.common.authorization.server.api.permission.biz.impl;


import com.zjk.module.common.authorization.client.api.frequency.domain.Frequency;
import com.zjk.module.common.authorization.client.api.jsonwebtoken.domain.JSONWebToken;
import com.zjk.module.common.authorization.client.api.method.constant.MethodConstant;
import com.zjk.module.common.authorization.client.api.method.domain.Method;
import com.zjk.module.common.authorization.client.api.permission.domain.Permission;
import com.zjk.module.common.authorization.client.api.rolemethod.domain.RoleMethod;
import com.zjk.module.common.authorization.client.exception.AuthorizationCode;
import com.zjk.module.common.authorization.server.api.frequency.biz.IFrequencyService;
import com.zjk.module.common.authorization.server.api.jsonwebtoken.biz.IJSONWebTokenService;
import com.zjk.module.common.authorization.server.api.method.biz.IMethodService;
import com.zjk.module.common.authorization.server.api.permission.biz.IPermissionService;
import com.zjk.module.common.authorization.server.api.rolemethod.biz.IRoleMethodService;
import com.zjk.module.common.authorization.server.api.userrole.biz.IUserRoleService;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends CommonServiceImpl implements IPermissionService {

	@Autowired
	private IMethodService methodService;
	@Autowired
	private IJSONWebTokenService jsonWebTokenService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IRoleMethodService roleMethodService;

	@Autowired
	private IFrequencyService frequencyService;

	@Override
	public Permission valid(Permission vo) {
		// 查询资源
		Method method = methodService.findOneByPathAndMethod(vo.getPath(), vo.getMethod());

		// 检查频率
		frequencyService.checkFrequency(new Frequency(vo.getIp(), vo.getLogin(), vo.getMethod(), vo.getPath()));

		// TODO 判断是否记录该资源，目前没有记录的资源全部放过
		// 检查权限
		if (null != method) {
			// 判断资源是否需要校验
			if (MethodConstant.VALID_FLAG_1.equals(method.getValidFlag())) {
				// 校验token
				jsonWebTokenService.valid(vo.getLogin(), vo.getToken());
				// 根据全部角色，方法，查询是否有绑定
				List<RoleMethod> roleMethods = roleMethodService.findAllByRoleCodeInAndMethodCode(map(userRoleService.findAllByUserCode(vo.getLogin()), e -> e.getRoleCode()), method.getCode());
				if (roleMethods.isEmpty()) {
					throw new BusinessException(AuthorizationCode.P0001, new Object[]{vo.getLogin(), method.getCode()});
				}
				// 校验通过，刷新token
				vo.setToken(jsonWebTokenService.token(new JSONWebToken(vo.getLogin())));
			}
		}
		return vo;
	}

}
