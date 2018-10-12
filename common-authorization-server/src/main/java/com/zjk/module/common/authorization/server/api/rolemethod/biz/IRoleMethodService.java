package com.zjk.module.common.authorization.server.api.rolemethod.biz;


import com.zjk.module.common.authorization.client.api.rolemethod.domain.RoleMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IRoleMethodService {

	void save(List<RoleMethod> vos);

	Page<RoleMethod> findAll(Pageable pageable);

	RoleMethod findOneByRoleCodeAndMethodCode(String roleCode, String methodCode);

	List<RoleMethod> findAllByRoleCodeInAndMethodCode(Collection<String> roleCodes, String methodCode);

}
