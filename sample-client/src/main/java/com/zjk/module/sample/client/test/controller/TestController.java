package com.zjk.module.sample.client.test.controller;

import com.alibaba.fastjson.JSON;
import com.zjk.module.common.authorization.client.api.passport.client.IPassportClient;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/test")
public class TestController extends BaseController {

	@Autowired
	private IPassportClient client;

	@ApiOperation(value = "test", notes = "test")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public JsonContainer save() {

		JsonContainer<User> user = client.loginSimple("admin", null);
		System.out.println(JSON.toJSONString(user));
		return setSuccessMessage();
	}
}
