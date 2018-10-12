package com.zjk.module.common.authorization.client.api.passport.client;

import com.zjk.module.common.base.domain.JsonContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/passport/check")
public interface IPassportCheckClient {

	@RequestMapping(value = "/exist/email", method = RequestMethod.GET)
	JsonContainer existEmail(@RequestParam(value = "email") @NotBlank String email);

	@RequestMapping(value = "/exist/code", method = RequestMethod.GET)
	JsonContainer existCode(@RequestParam(value = "code") @NotBlank String code);

}
