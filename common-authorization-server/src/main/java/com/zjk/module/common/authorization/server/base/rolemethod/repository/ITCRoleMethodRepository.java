package com.zjk.module.common.authorization.server.base.rolemethod.repository;


import com.zjk.module.common.authorization.server.base.rolemethod.domain.TCRoleMethod;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource(path = "rolemethod")
public interface ITCRoleMethodRepository extends IDataRepository<TCRoleMethod, Integer> {

	List<TCRoleMethod> findAllByRoleCode(String roleCode);

	List<TCRoleMethod> findAllByMethodCode(String methodCode);

	TCRoleMethod findOneByRoleCodeAndMethodCode(String roleCode, String methodCode);

	List<TCRoleMethod> findAllByRoleCodeInAndMethodCode(Collection<String> roleCodes, String methodCode);

}
