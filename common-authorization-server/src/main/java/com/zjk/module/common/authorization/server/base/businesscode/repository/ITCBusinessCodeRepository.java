package com.zjk.module.common.authorization.server.base.businesscode.repository;


import com.zjk.module.common.authorization.server.base.businesscode.domain.TCBusinessCode;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "businesscode")
public interface ITCBusinessCodeRepository extends IDataRepository<TCBusinessCode, Integer> {

	TCBusinessCode findOneByCode(String code);

	TCBusinessCode findOneByBusinessClazzAndBusinessCode(String businessClazz, String businessCode);
}
