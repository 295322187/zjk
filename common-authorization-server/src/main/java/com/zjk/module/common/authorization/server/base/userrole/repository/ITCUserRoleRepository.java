package com.zjk.module.common.authorization.server.base.userrole.repository;


import com.zjk.module.common.authorization.server.base.userrole.domain.TCUserRole;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "userrole")
public interface ITCUserRoleRepository extends IDataRepository<TCUserRole, Integer> {

	List<TCUserRole> findAllByUserCode(String userCode);

	List<TCUserRole> findAllByRoleCode(String roleCode);

	TCUserRole findOneByUserCodeAndRoleCode(String userCode, String roleCode);

	void deleteByUserCode(String userCode);
}
