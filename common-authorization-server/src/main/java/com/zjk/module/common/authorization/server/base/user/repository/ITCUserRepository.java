package com.zjk.module.common.authorization.server.base.user.repository;


import com.zjk.module.common.authorization.server.base.user.domain.TCUser;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user")
public interface ITCUserRepository extends IDataRepository<TCUser, Integer> {

	TCUser findOneByCode(String code);

	@Query(value = "select * from t_c_user where email = ?1 or mobile = ?1", nativeQuery = true)
	TCUser findOneByUsername(String username);

	TCUser findOneByEmail(String email);

	TCUser findOneByMobile(String mobile);

	void deleteByCode(String code);
}
