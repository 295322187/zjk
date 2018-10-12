package com.zjk.module.common.authorization.server.api.verificationcode.controller;

import com.zjk.module.common.authorization.client.api.verificationcode.domain.VerificationCode;
import com.zjk.module.common.authorization.client.api.verificationcode.domain.VerificationCodeCheck;
import com.zjk.module.common.authorization.client.api.verificationcode.domain.VerificationCodeSetting;
import com.zjk.module.common.authorization.server.api.verificationcode.biz.IVerificationCodeService;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/common/authorization/api/verificationcode")
public class VerificationCodeController extends BaseController {

	@Autowired
	private IVerificationCodeService service;

	@ApiOperation(value = "生成验证码", notes = "生成验证码")
	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	public JsonContainer<VerificationCode> generate(@RequestBody @Validated VerificationCodeSetting setting) {
		return setSuccessMessage(service.generate(setting));
	}

	@ApiOperation(value = "校验验证码", notes = "校验验证码")
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public JsonContainer check(@RequestBody @Validated VerificationCodeCheck check) {
		service.check(check);
		return setSuccessMessage();
	}

}
