package com.zjk.module.common.authorization.server.base.role.repository;


import com.zjk.module.common.authorization.server.base.role.domain.TCRole;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "role")
public interface ITCRoleRepository extends IDataRepository<TCRole, Integer> {

	TCRole findOneByCode(String code);

	TCRole findOneByRole(String role);

}
