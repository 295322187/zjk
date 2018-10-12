package com.zjk.module.common.authorization.server.api.userrole.biz;


import com.zjk.module.common.authorization.client.api.userrole.domain.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserRoleService {

	void save(List<UserRole> vos);

	Page<UserRole> findAll(Pageable pageable);

	List<UserRole> findAllByUserCode(String userCode);

	UserRole findOneByUserCodeAndRoleCode(String userCode, String roleCode);

	void deleteByUserCode(String userCode);
}
