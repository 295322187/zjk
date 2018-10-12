package com.zjk.module.common.authorization.server.api.rolemethod.controller;

import com.zjk.module.common.authorization.client.api.rolemethod.domain.RoleMethod;
import com.zjk.module.common.authorization.server.api.rolemethod.biz.IRoleMethodService;
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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/common/authorization/api/rolemethod")
public class RoleMethodController extends BaseController {

	@Autowired
	private IRoleMethodService service;

	@ApiOperation(value = "绑定角色方法", notes = "绑定角色方法")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody @Valid List<RoleMethod> vos) {
		service.save(vos);
		return setSuccessMessage();
	}

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<RoleMethod>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "根据角色编号，方法编号查询关联关系", notes = "根据角色编号，方法编号查询关联关系")
	@RequestMapping(value = "/findOneByRoleCodeAndMethodCode", method = RequestMethod.GET)
	public JsonContainer<RoleMethod> findOneByRoleCodeAndMethodCode(@RequestParam @NotBlank String roleCode, @RequestParam @NotBlank String methodCode) {
		return setSuccessMessage(service.findOneByRoleCodeAndMethodCode(roleCode, methodCode));
	}


}
