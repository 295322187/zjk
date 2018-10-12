package com.zjk.module.common.authorization.client.api.rolemethod.client;

import com.zjk.module.common.authorization.client.api.rolemethod.domain.RoleMethod;
import com.zjk.module.common.base.domain.JsonContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.util.List;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/rolemethod")
public interface IRoleMethodClient {

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	JsonContainer save(@RequestBody @Validated List<RoleMethod> vos);

	@RequestMapping(value = "/findOneByRoleCodeAndMethodCode", method = RequestMethod.GET)
	JsonContainer<RoleMethod> findOneByRoleCodeAndMethodCode(@RequestParam(value = "roleCode") @NotBlank String roleCode,
															 @RequestParam(value = "methodCode") @NotBlank String methodCode);
}
