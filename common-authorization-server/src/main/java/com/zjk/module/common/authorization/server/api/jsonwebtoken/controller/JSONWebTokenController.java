package com.zjk.module.common.authorization.server.api.jsonwebtoken.controller;

import com.zjk.module.common.authorization.client.api.jsonwebtoken.domain.JSONWebToken;
import com.zjk.module.common.authorization.server.api.jsonwebtoken.biz.IJSONWebTokenService;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/common/authorization/api/jsonwebtoken")
public class JSONWebTokenController extends BaseController {

	@Autowired
	private IJSONWebTokenService service;

	@ApiOperation(value = "生成token", notes = "生成token")
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public JsonContainer<String> token(@RequestBody @Validated JSONWebToken jsonWebToken) {
		return setSuccessMessage(service.token(jsonWebToken));
	}

	@ApiOperation(value = "校验token", notes = "校验token")
	@RequestMapping(value = "/valid", method = RequestMethod.POST)
	public JsonContainer valid(@RequestParam String login, @RequestParam String token) {
		service.valid(login, token);
		return setSuccessMessage();
	}

}
