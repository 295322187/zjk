package com.zjk.module.common.authorization.server.api.serialcode.controller;


import com.zjk.module.common.authorization.client.api.serialcode.biz.ISerialCodeRegisterService;
import com.zjk.module.common.authorization.client.api.serialcode.domain.SerialCode;
import com.zjk.module.common.authorization.server.api.serialcode.biz.ISerialCodeService;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/common/authorization/api/serialcode")
public class SerialCodeController extends BaseController {

	@Autowired
	private ISerialCodeRegisterService registerService;

	@Autowired
	private ISerialCodeService service;

	@ApiOperation(value = "获取下一编号", notes = "获取下一编号")
	@RequestMapping(value = "/next/{serialGroup}", method = RequestMethod.GET)
	public JsonContainer<String> next(@PathVariable String serialGroup) {
		return setSuccessMessage(service.next(serialGroup));
	}

	@ApiOperation(value = "注册序列号", notes = "注册序列号")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public JsonContainer<List<SerialCode>> register(@RequestBody List<SerialCode> vos) {
		registerService.register(vos);
		return setSuccessMessage(vos);
	}

}
