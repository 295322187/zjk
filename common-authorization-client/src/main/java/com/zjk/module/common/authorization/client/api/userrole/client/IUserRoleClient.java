package com.zjk.module.common.authorization.client.api.userrole.client;

import com.zjk.module.common.authorization.client.api.userrole.domain.UserRole;
import com.zjk.module.common.base.domain.JsonContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "${common.authorization.application.name:common-authorization}")
@RequestMapping("/common/authorization/api/userrole")
public interface IUserRoleClient {

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	JsonContainer save(@RequestBody @Validated List<UserRole> vos);

}
