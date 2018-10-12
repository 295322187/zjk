package com.zjk.module.common.authorization.server.api.permission.biz;


import com.zjk.module.common.authorization.client.api.permission.domain.Permission;

public interface IPermissionService {

	Permission valid(Permission vo);

}
