package com.zjk.module.common.authorization.server.api.role.biz;


import com.zjk.module.common.authorization.client.api.role.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoleService {

	void save(Role vo);

	Page<Role> findAll(Pageable pageable);

	Role findOneByRole(String role);

}
