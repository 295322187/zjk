package com.zjk.module.common.authorization.server.api.rolemethod.biz.impl;

import com.zjk.module.common.authorization.client.api.rolemethod.domain.RoleMethod;
import com.zjk.module.common.authorization.client.exception.AuthorizationCode;
import com.zjk.module.common.authorization.server.api.rolemethod.biz.IRoleMethodService;
import com.zjk.module.common.authorization.server.base.rolemethod.biz.ITCRoleMethodService;
import com.zjk.module.common.authorization.server.base.rolemethod.domain.TCRoleMethod;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class RoleMethodServiceImpl extends CommonServiceImpl implements IRoleMethodService {

	@Autowired
	private ITCRoleMethodService service;

	@Override
	@Transactional
	public void save(List<RoleMethod> vos) {
		vos.forEach(e ->
				service.save(mapOneIfNotNullThrowException(service.findOneByRoleCodeAndMethodCode(e.getRoleCode(), e.getMethodCode()), e,
						s -> new TCRoleMethod(s.getRoleCode(), s.getMethodCode()), new BusinessException(AuthorizationCode.RM0001, new Object[]{e.getRoleCode(), e.getMethodCode()})))
		);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<RoleMethod> findAll(Pageable pageable) {
		return service.findAll(pageable).map(e -> new RoleMethod(e.getRoleCode(), e.getMethodCode()));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public RoleMethod findOneByRoleCodeAndMethodCode(String roleCode, String methodCode) {
		return mapOneIfNotNull(service.findOneByRoleCodeAndMethodCode(roleCode, methodCode), e -> new RoleMethod(e.getRoleCode(), e.getMethodCode()));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<RoleMethod> findAllByRoleCodeInAndMethodCode(Collection<String> roleCodes, String methodCode) {
		return map(service.findAllByRoleCodeInAndMethodCode(roleCodes, methodCode), e -> new RoleMethod(e.getRoleCode(), e.getMethodCode()));
	}
}
