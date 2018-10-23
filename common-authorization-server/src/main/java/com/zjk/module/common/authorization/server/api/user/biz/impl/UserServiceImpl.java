package com.zjk.module.common.authorization.server.api.user.biz.impl;

import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.user.constant.UserConstant;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.api.user.domain.UserSettings;
import com.zjk.module.common.authorization.server.api.passport.biz.IPassportCheckService;
import com.zjk.module.common.authorization.server.api.user.biz.IUserService;
import com.zjk.module.common.authorization.server.api.userrole.biz.IUserRoleService;
import com.zjk.module.common.authorization.server.base.user.biz.ITCUserService;
import com.zjk.module.common.authorization.server.base.user.domain.TCUser;
import com.zjk.module.common.authorization.server.base.usersettings.biz.ITCUserSettingsService;
import com.zjk.module.common.authorization.server.base.usersettings.domain.TCUserSettings;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.util.CodecUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl extends CommonServiceImpl implements IUserService {

	@Autowired
	private ITCUserService service;
	@Autowired
	private ITCUserSettingsService userSettingsService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IPassportCheckService passportCheckService;

	@Override
	@Transactional
	public User save(Register register) {
		User user = new User();
		// 手机
		if (StringUtils.isNotBlank(register.getMobile())) {
			// 校验手机
			passportCheckService.isNotExistMobile(user.getMobile());
			user.setMobile(register.getMobile());
			user.setMobileVerified(register.getMobileVerified());
		}
		// 邮箱
		if (StringUtils.isNotBlank(register.getEmail())) {
			// 校验邮箱
			passportCheckService.isNotExistEmail(user.getEmail());
			user.setEmail(register.getEmail());
			user.setEmailVerified(register.getEmailVerified());
		}
		// 密码加密
		if (null != register.getPassword()) {
			user.setPassword(encryptPassword(register.getPassword()));
		}
		user.getSettings().setLang(register.getLang());
		user.getSettings().setInternational(register.getInternational());
		save(user);
		return user;
	}

	@Override
	@Transactional
	public void save(User vo) {
		TCUser po = service.findOneByCode(vo.getCode());
		if (null == po) {
			po = service.newInstance();
		}
		po.setEmail(vo.getEmail());
		po.setMobile(vo.getMobile());
		po.setPassword(vo.getPassword());
		po.setLastLogin(vo.getLastLogin());
		po.setEmailVerified(vo.getEmailVerified());
		po.setMobileVerified(vo.getMobileVerified());
		service.save(po);
		// 设置code
		vo.setCode(po.getCode());
		// 保存settings
		TCUserSettings settings = userSettingsService.findOneByCode(po.getCode());
		if (null == settings) {
			settings = new TCUserSettings();
			settings.setCode(po.getCode());
		}
		settings.setName(vo.getSettings().getName());
		settings.setIdCard(vo.getSettings().getIdCard());
		settings.setIdCardType(vo.getSettings().getIdCardType());
		settings.setIdCardVerified(vo.getSettings().getIdCardVerified());
		settings.setIdCardFront(vo.getSettings().getIdCardFront());
		settings.setIdCardBack(vo.getSettings().getIdCardBack());
		settings.setLang(vo.getSettings().getLang());
		settings.setInternational(vo.getSettings().getInternational());
		userSettingsService.save(settings);
	}

	/**
	 * 密码加密
	 *
	 * @param password
	 */
	public String encryptPassword(String password) {
		return CodecUtil.sha1Hex(password);
	}

	/**
	 * 转换
	 *
	 * @param e
	 * @return
	 */
	private User convert(TCUser e) {
		return new User(e.getCode(), e.getEmail(), e.getMobile(), e.getPassword(), e.getLastLogin(), e.getEmailVerified(), e.getMobileVerified(),
				mapOneIfNotNull(userSettingsService.findOneByCode(e.getCode()), s -> new UserSettings(s.getName(), s.getIdCard(), s.getIdCardType(), s.getIdCardVerified(), s.getIdCardFront(), s.getIdCardBack(), s.getLang(), s.getInternational())));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		return service.findAll(pageable).map(e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public User findOneByCode(String code) {
		return mapOneIfNotNull(service.findOneByCode(code), e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public User findOneByUsername(String username) {
		return mapOneIfNotNull(service.findOneByUsername(username), e -> convert(e));
	}

	@Override
	@Transactional
	public void updateLastLogin(String code) {
		TCUser po = service.findOneByCode(code);
		po.setLastLogin(new Date());
		service.save(po);
	}

	@Override
	@Transactional
	public void updateName(String code, String name) {
		TCUserSettings settings = userSettingsService.findOneByCode(code);
		if (null == settings) {
			settings = new TCUserSettings();
			settings.setCode(code);
		}
		settings.setName(name);
		userSettingsService.save(settings);
	}

	@Override
	@Transactional
	public void updateNameAndIdCard(String code, String name, String idCard, Integer idCardType) {
		TCUserSettings settings = userSettingsService.findOneByCode(code);
		if (null == settings) {
			settings = new TCUserSettings();
			settings.setCode(code);
		}
		settings.setName(name);
		settings.setIdCard(idCard);
		settings.setIdCardType(idCardType);
		settings.setIdCardVerified(UserConstant.VERIFIED_1);
		userSettingsService.save(settings);
	}

	@Override
	@Transactional
	public void updatePassword(String code, String password) {
		TCUser po = service.findOneByCode(code);
		po.setPassword(encryptPassword(password));
		service.save(po);
	}

	@Override
	@Transactional
	public void deleteByCode(String code) {
		service.deleteByCode(code);
		userSettingsService.deleteByCode(code);
		userRoleService.deleteByUserCode(code);
	}
}
