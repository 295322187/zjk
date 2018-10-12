package com.zjk.module.common.authorization.server.api.role.biz.impl;

import com.zjk.module.common.authorization.client.api.role.domain.Role;
import com.zjk.module.common.authorization.server.api.role.biz.IRoleService;
import com.zjk.module.common.authorization.server.base.role.biz.ITCRoleService;
import com.zjk.module.common.authorization.server.base.role.domain.TCRole;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl extends CommonServiceImpl implements IRoleService {

	@Autowired
	private ITCRoleService service;

	@Override
	@Transactional
	public void save(Role vo) {
		TCRole po = service.findOneByCode(vo.getCode());
		if (null == po) {
			po = service.newInstance();
		}
		po.setRole(vo.getRole());
		po.setRoleName(vo.getRoleName());
		po.setRemark(vo.getRemark());
		service.save(po);
	}

	private Role convert(TCRole e) {
		return new Role(e.getCode(), e.getRole(), e.getRoleName(), e.getRemark());
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Role> findAll(Pageable pageable) {
		return service.findAll(pageable).map(e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Role findOneByRole(String role) {
		return mapOneIfNotNull(service.findOneByRole(role), e -> convert(e));
	}
}
