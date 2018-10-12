package com.zjk.module.common.authorization.client.api.passport.client;

import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.base.domain.JsonContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@FeignClient(value = "${common.authorization.application.name:common-authorization}")
@RequestMapping("/common/authorization/api/passport")
public interface IPassportClient {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	JsonContainer<User> login(@RequestParam(value = "username") @NotBlank String username, @RequestParam(value = "password") @NotBlank String password);

	@RequestMapping(value = "/login/simple", method = RequestMethod.GET)
	JsonContainer<User> loginSimple(@RequestParam(value = "username") @NotBlank String username);

	@RequestMapping(value = "/updateLastLogin", method = RequestMethod.PUT)
	JsonContainer updateLastLogin(@RequestParam(value = "code") @NotBlank String code);

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	JsonContainer<User> register(@RequestBody @Validated Register register);

	@RequestMapping(value = "/updateName", method = RequestMethod.PUT)
	JsonContainer updateName(@RequestParam(value = "code") @NotBlank String code, @RequestParam(value = "name") @NotBlank String name);

	@RequestMapping(value = "/updateNameAndIdCard", method = RequestMethod.PUT)
	JsonContainer updateNameAndIdCard(@RequestParam(value = "code") @NotBlank String code, @RequestParam(value = "name") @NotBlank String name,
									  @RequestParam(value = "idCard") @NotBlank String idCard, @RequestParam(value = "idCardType") @NotNull Integer idCardType);

	@RequestMapping(value = "/user/{userCode}", method = RequestMethod.GET)
	JsonContainer<User> findOneByCode(@PathVariable(value = "userCode") @NotBlank String userCode);

	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	JsonContainer updateUser(@RequestBody @Validated User vo);

	@RequestMapping(value = "/user/{userCode}", method = RequestMethod.DELETE)
	JsonContainer deleteByCode(@PathVariable(value = "userCode") @NotBlank String userCode);

}
