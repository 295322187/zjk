package com.zjk.module.common.authorization.client.api.method.client;

import com.zjk.module.common.authorization.client.api.method.domain.Method;
import com.zjk.module.common.base.domain.JsonContainer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/method")
public interface IMethodClient {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	JsonContainer<List<Method>> register(@RequestBody List<Method> vos);

	@RequestMapping(value = "/findAllByApplicationName", method = RequestMethod.GET)
	JsonContainer<List<Method>> findAllByApplicationName(@RequestParam(value = "applicationName") @NotBlank String applicationName);

}
