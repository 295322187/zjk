package com.zjk.module.common.authorization.server.base.role.biz;


import com.zjk.module.common.authorization.server.base.role.domain.TCRole;
import com.zjk.module.common.data.biz.IDataService;

public interface ITCRoleService extends IDataService<TCRole, Integer> {

	TCRole newInstance();

	TCRole findOneByCode(String code);

	TCRole findOneByRole(String role);

}
