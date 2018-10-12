package com.zjk.module.common.authorization.server.base.method.repository;

import com.zjk.module.common.authorization.server.base.method.domain.TCMethod;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "method")
public interface ITCMethodRepository extends IDataRepository<TCMethod, Integer> {

	TCMethod findOneByApplicationNameAndMethodAndAndPattern(String applicationName, String method, String pattern);

	List<TCMethod> findAllByApplicationName(String applicationName);

}
