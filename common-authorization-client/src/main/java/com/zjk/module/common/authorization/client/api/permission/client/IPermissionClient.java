package com.zjk.module.common.authorization.client.api.permission.client;

import com.zjk.module.common.authorization.client.api.permission.domain.Permission;
import com.zjk.module.common.base.domain.JsonContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${common.authorization.application.name:common-authorization}")
@RequestMapping("/common/authorization/api/permission")
public interface IPermissionClient {

	@RequestMapping(value = "/valid", method = RequestMethod.POST)
	JsonContainer<Permission> valid(@RequestBody @Validated Permission permission);

}
