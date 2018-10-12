package com.zjk.module.common.authorization.server.base.userrole.biz;


import com.zjk.module.common.authorization.server.base.userrole.domain.TCUserRole;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITCUserRoleService extends IDataService<TCUserRole, Integer> {

	List<TCUserRole> findAllByUserCode(String userCode);

	List<TCUserRole> findAllByRoleCode(String roleCode);

	TCUserRole findOneByUserCodeAndRoleCode(String userCode, String roleCode);

	void deleteByUserCode(String userCode);
}
