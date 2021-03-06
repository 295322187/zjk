package com.zjk.module.common.authorization.server.api.userrole.controller;

import com.zjk.module.common.authorization.client.api.userrole.domain.UserRole;
import com.zjk.module.common.authorization.server.api.userrole.biz.IUserRoleService;
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
import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/common/authorization/api/userrole")
public class UserRoleController extends BaseController {

	@Autowired
	private IUserRoleService service;

	@ApiOperation(value = "绑定用户角色", notes = "绑定用户角色")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody @Valid List<UserRole> vos) {
		service.save(vos);
		return setSuccessMessage();
	}

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<UserRole>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "查询用户全部角色", notes = "查询用户全部角色")
	@RequestMapping(value = "/findAllByUserCode/{userCode}", method = RequestMethod.GET)
	public JsonContainer<List<UserRole>> findAllByUserCode(@PathVariable String userCode) {
		return setSuccessMessage(service.findAllByUserCode(userCode));
	}

}
