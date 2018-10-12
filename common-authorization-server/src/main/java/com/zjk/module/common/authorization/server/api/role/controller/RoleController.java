package com.zjk.module.common.authorization.server.api.role.controller;

import com.zjk.module.common.authorization.client.api.role.domain.Role;
import com.zjk.module.common.authorization.server.api.role.biz.IRoleService;
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

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService service;

	@ApiOperation(value = "添加或修改角色", notes = "添加或修改角色")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody Role vo) {
		service.save(vo);
		return setSuccessMessage();
	}

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<Role>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "根据角色查询", notes = "根据角色查询")
	@RequestMapping(value = "/findOneByRole", method = RequestMethod.GET)
	public JsonContainer<Role> findOneByRole(@RequestParam @NotBlank String role) {
		return setSuccessMessage(service.findOneByRole(role));
	}

}
