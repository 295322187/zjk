package com.zjk.module.common.authorization.server.api.businesscode.controller;

import com.zjk.module.common.authorization.client.api.businesscode.biz.IBusinessCodeRegisterService;
import com.zjk.module.common.authorization.client.api.businesscode.domain.BusinessCode;
import com.zjk.module.common.authorization.server.api.businesscode.biz.IBusinessCodeService;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/common/authorization/api/businesscode")
public class BusinessCodeController extends BaseController {

	@Autowired
	private IBusinessCodeRegisterService registerService;

	@Autowired
	private IBusinessCodeService service;

	@ApiOperation(value = "注册业务代码", notes = "注册业务代码")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public JsonContainer<List<BusinessCode>> register(@RequestBody List<BusinessCode> vos) {
		return setSuccessMessage(registerService.register(vos));
	}

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<BusinessCode>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "刷新数据", notes = "刷新数据")
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public JsonContainer refresh() {
		service.refresh();
		return setSuccessMessage();
	}

	@ApiOperation(value = "根据业务类和业务代码查询", notes = "根据业务类和业务代码查询")
	@RequestMapping(value = "/findOneByBusinessClazzAndBusinessCode", method = RequestMethod.GET)
	public JsonContainer<BusinessCode> findOneByBusinessClazzAndBusinessCode(@RequestParam @NotBlank String businessClazz, @RequestParam @NotBlank String businessCode) {
		return setSuccessMessage(registerService.findOneByBusinessClazzAndBusinessCode(businessClazz, businessCode));
	}

	@ApiOperation(value = "添加或修改业务代码语言", notes = "添加或修改业务代码语言")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody @Validated BusinessCode vo) {
		service.save(vo);
		return setSuccessMessage();
	}

}
