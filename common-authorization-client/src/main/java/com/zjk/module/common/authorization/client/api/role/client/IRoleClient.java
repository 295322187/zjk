package com.zjk.module.common.authorization.client.api.role.client;

import com.zjk.module.common.authorization.client.api.role.domain.Role;
import com.zjk.module.common.base.domain.JsonContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/role")
public interface IRoleClient {

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	JsonContainer save(@RequestBody Role vo);

	@RequestMapping(value = "/findOneByRole", method = RequestMethod.GET)
	JsonContainer<Role> findOneByRole(@RequestParam(value = "role") @NotBlank String role);
}
