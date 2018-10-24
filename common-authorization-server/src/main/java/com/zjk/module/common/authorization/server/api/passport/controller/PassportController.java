package com.zjk.module.common.authorization.server.api.passport.controller;

import com.zjk.module.common.authorization.client.api.passport.domain.ChangePassword;
import com.zjk.module.common.authorization.client.api.passport.domain.ForgetPassword;
import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.server.api.passport.biz.IPassportService;
import com.zjk.module.common.base.annotation.CreateApiDocs;
import com.zjk.module.common.base.controller.BaseController;
import com.zjk.module.common.base.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/common/authorization/api/passport")
public class PassportController extends BaseController {

	@Autowired
	private IPassportService service;

	@ApiOperation(value = "登录", notes = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public JsonContainer<User> login(@RequestParam @NotBlank String username, @RequestParam @NotBlank String password) {
		return setSuccessMessage(service.login(username, password));
	}

	@ApiOperation(value = "登录简单版", notes = "登录简单版")
	@RequestMapping(value = "/login/simple", method = RequestMethod.GET)
	public JsonContainer<User> loginSimple(@RequestParam @NotBlank String username, @RequestParam(required = false) String plugin) {
		return setSuccessMessage(service.loginSimple(username, plugin));
	}

	@ApiOperation(value = "更新最后登陆时间", notes = "更新最后登陆时间")
	@RequestMapping(value = "/updateLastLogin", method = RequestMethod.PUT)
	public JsonContainer updateLastLogin(@RequestParam @NotBlank String code) {
		service.updateLastLogin(code);
		return setSuccessMessage();
	}

	@ApiOperation(value = "用户注册", notes = "用户注册，没有插件手机号和邮箱校验非空，重复，有插件手机号和邮箱校验重复，非空逻辑在业务层判断")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public JsonContainer<User> register(@RequestBody @Validated Register register, @RequestParam(required = false) String plugin) {
		return setSuccessMessage(service.register(register, plugin));
	}

	@ApiOperation(value = "更新姓名", notes = "更新姓名")
	@RequestMapping(value = "/updateName", method = RequestMethod.PUT)
	public JsonContainer updateName(@RequestParam @NotBlank String code, @RequestParam @NotBlank String name) {
		service.updateName(code, name);
		return setSuccessMessage();
	}

	@ApiOperation(value = "更新证件信息", notes = "更新证件信息")
	@RequestMapping(value = "/updateNameAndIdCard", method = RequestMethod.PUT)
	public JsonContainer updateNameAndIdCard(@RequestParam @NotBlank String code, @RequestParam @NotBlank String name,
											 @RequestParam @NotBlank String idCard, @RequestParam @NotNull Integer idCardType) {
		service.updateNameAndIdCard(code, name, idCard, idCardType);
		return setSuccessMessage();
	}

	@ApiOperation(value = "忘记密码", notes = "忘记密码")
	@RequestMapping(value = "/forget/password", method = RequestMethod.PUT)
	public JsonContainer forgetPassword(@RequestBody @Validated ForgetPassword vo) {
		service.forgetPassword(vo);
		return setSuccessMessage();
	}

	@ApiOperation(value = "修改密码", notes = "修改密码")
	@RequestMapping(value = "/change/password", method = RequestMethod.PUT)
	public JsonContainer changePassword(@RequestBody @Validated ChangePassword vo) {
		service.changePassword(vo);
		return setSuccessMessage();
	}

	@ApiOperation(value = "用户信息", notes = "用户信息")
	@RequestMapping(value = "/user/{userCode}", method = RequestMethod.GET)
	public JsonContainer<User> findOneByCode(@PathVariable @NotBlank String userCode) {
		return setSuccessMessage(service.findOneByCode(userCode));
	}

	@ApiOperation(value = "更新用户信息", notes = "更新用户信息")
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	public JsonContainer<User> updateUser(@RequestBody @Validated User user, @RequestParam(required = false) String plugin) {
		return setSuccessMessage(service.updateUser(user, plugin));
	}

	@ApiOperation(value = "删除用户", notes = "删除用户")
	@RequestMapping(value = "/user/{userCode}", method = RequestMethod.DELETE)
	public JsonContainer deleteByCode(@PathVariable @NotBlank String userCode, @RequestParam(required = false) String plugin) {
		service.deleteByCode(userCode, plugin);
		return setSuccessMessage();
	}
}
