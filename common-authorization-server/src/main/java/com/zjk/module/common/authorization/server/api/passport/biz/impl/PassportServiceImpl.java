package com.zjk.module.common.authorization.server.api.passport.biz.impl;

import com.zjk.module.common.authorization.client.api.passport.domain.ChangePassword;
import com.zjk.module.common.authorization.client.api.passport.domain.ForgetPassword;
import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.exception.AuthorizationCode;
import com.zjk.module.common.authorization.server.api.passport.biz.IPassportCheckService;
import com.zjk.module.common.authorization.server.api.passport.biz.IPassportPluginService;
import com.zjk.module.common.authorization.server.api.passport.biz.IPassportService;
import com.zjk.module.common.authorization.server.api.user.biz.IUserService;
import com.zjk.module.common.authorization.server.api.verificationcode.biz.IVerificationCodeService;
import com.zjk.module.common.base.biz.impl.BusinessServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import com.zjk.module.common.base.util.CodecUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class PassportServiceImpl extends BusinessServiceImpl implements IPassportService {

	@Autowired
	private IUserService service;
	@Autowired
	private IPassportCheckService passportCheckService;
	@Autowired
	private IVerificationCodeService verificationCodeService;

	private IPassportPluginService getIPassportPluginService(String plugin) {
		Map<String, IPassportPluginService> map = provider.getBeansOfType(IPassportPluginService.class);
		return map.entrySet().stream().filter(e -> plugin.equalsIgnoreCase(e.getValue().getPlugin())).findFirst().orElse(null).getValue();
	}

	/*******************************************************************************************************/

	@Override
	public User login(String username, String password) {
		User user = service.findOneByUsername(username);
		// 用户名错误
		if (null == user) {
			throw new BusinessException(AuthorizationCode.PP0001);
		}
		// 密码错误
		if (!CodecUtil.sha1Hex(password).equals(user.getPassword())) {
			throw new BusinessException(AuthorizationCode.PP0002);
		}
		return user;
	}

	@Override
	public User loginSimple(String username, String plugin) {
		User user;
		if (StringUtils.isNotBlank(plugin)) {
			IPassportPluginService passportPluginService = checkIfNullThrowException(getIPassportPluginService(plugin), new BusinessException(AuthorizationCode.PP0012, new Object[]{plugin}));
			user = passportPluginService.login(username);
		} else {
			user = service.findOneByUsername(username);
		}
		// 用户名错误
		if (null == user) {
			throw new BusinessException(AuthorizationCode.PP0001);
		}
		return user;
	}

	@Override
	@Transactional
	public void updateLastLogin(String code) {
		passportCheckService.existCode(code);
		service.updateLastLogin(code);
	}

	@Override
	@Transactional
	public User register(Register register, String plugin) {
		User user;
		// 执行插件
		if (StringUtils.isNotBlank(plugin)) {
			IPassportPluginService passportPluginService = checkIfNullThrowException(getIPassportPluginService(plugin), new BusinessException(AuthorizationCode.PP0012, new Object[]{plugin}));
			user = passportPluginService.register(register);
		} else {
			// 没有插件手机号和邮箱不能为空
			if (StringUtils.isBlank(register.getMobile())) {
				throw new BusinessException(AuthorizationCode.PP0013);
			}
			if (StringUtils.isBlank(register.getEmail())) {
				throw new BusinessException(AuthorizationCode.PP0014);
			}
			// 执行默认
			user = service.save(register);
		}
		return user;
	}

	@Override
	@Transactional
	public void updateName(String code, String name) {
		passportCheckService.existCode(code);
		service.updateName(code, name);
	}

	@Override
	@Transactional
	public void updateNameAndIdCard(String code, String name, String idCard, Integer idCardType) {
		passportCheckService.existCode(code);
		passportCheckService.isNotExistIdCard(idCard);
		service.updateNameAndIdCard(code, name, idCard, idCardType);
	}

	@Override
	@Transactional
	public void forgetPassword(ForgetPassword vo) {
		verificationCodeService.check(vo.getVerificationCodeCheck());
		User user = service.findOneByUsername(vo.getUsername());
		// 用户名错误
		if (null == user) {
			throw new BusinessException(AuthorizationCode.PP0001);
		}
		service.updatePassword(user.getCode(), vo.getNewPassword());
	}

	@Override
	@Transactional
	public void changePassword(ChangePassword vo) {
		verificationCodeService.check(vo.getVerificationCodeCheck());
		User user = service.findOneByCode(vo.getCode());
		// 密码错误
		if (!CodecUtil.sha1Hex(vo.getOldPassword()).equals(user.getPassword())) {
			throw new BusinessException(AuthorizationCode.PP0002);
		}
		service.updatePassword(user.getCode(), vo.getNewPassword());
	}

	@Override
	public User findOneByCode(String userCode, String plugin) {
		User user;
		if (StringUtils.isNotBlank(plugin)) {
			IPassportPluginService passportPluginService = checkIfNullThrowException(getIPassportPluginService(plugin), new BusinessException(AuthorizationCode.PP0012, new Object[]{plugin}));
			user = passportPluginService.findOneByCode(userCode);
		} else {
			user = service.findOneByCode(userCode);
		}
		return user;
	}

	@Override
	@Transactional
	public User updateUser(User user, String plugin) {
		if (StringUtils.isNotBlank(plugin)) {
			IPassportPluginService passportPluginService = checkIfNullThrowException(getIPassportPluginService(plugin), new BusinessException(AuthorizationCode.PP0012, new Object[]{plugin}));
			passportPluginService.updateUser(user);
		} else {
			service.save(user);
		}
		return user;
	}

	@Override
	@Transactional
	public void deleteByCode(String code, String plugin) {
		if (StringUtils.isNotBlank(plugin)) {
			IPassportPluginService passportPluginService = checkIfNullThrowException(getIPassportPluginService(plugin), new BusinessException(AuthorizationCode.PP0012, new Object[]{plugin}));
			passportPluginService.deleteByCode(code);
		} else {
			service.deleteByCode(code);
		}
	}
}
