package com.zjk.module.common.authorization.server.weixin.plugin.api.passport.biz.impl;

import com.alibaba.fastjson.JSON;
import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.exception.AuthorizationCode;
import com.zjk.module.common.authorization.client.weixin.plugin.api.passport.constant.WeixinPluginConstant;
import com.zjk.module.common.authorization.client.weixin.plugin.api.passport.domain.UserWeixin;
import com.zjk.module.common.authorization.client.weixin.plugin.exception.WeixinAuthorizationCode;
import com.zjk.module.common.authorization.server.api.passport.biz.IPassportPluginService;
import com.zjk.module.common.authorization.server.api.user.biz.IUserService;
import com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.biz.ITCUserWeixinService;
import com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.domain.TCUserWeixin;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeixinPassportPluginServiceImpl extends CommonServiceImpl implements IPassportPluginService {

	@Autowired
	private ITCUserWeixinService userWeixinService;
	@Autowired
	private IUserService userService;

	@Override
	public String getPlugin() {
		return WeixinPluginConstant.WEIXIN_PLUGIN;
	}

	private UserWeixin convert(TCUserWeixin e) {
		return new UserWeixin(e.getCode(), e.getOpenid(), e.getNickname(), e.getSex(), e.getLanguage(), e.getCity(), e.getProvince(), e.getCountry(), e.getHeadimgurl(), e.getUnionid());
	}

	@Override
	public User login(String username) {
		User user = null;
		// 通过openid查询userweixin
		UserWeixin userWeixin = mapOneIfNotNull(userWeixinService.findOneByOpenid(username), e -> convert(e));
		if (null != userWeixin) {
			// 通过code查询user
			user = userService.findOneByCode(userWeixin.getCode());
			// 把userweixin放入plugin中
			user.getPlugin().put(WeixinPluginConstant.WEIXIN_PLUGIN, userWeixin);
		}
		return user;
	}

	@Override
	@Transactional
	public User register(Register register) {
		Object object = checkIfNullThrowException(register.getPlugin().get(WeixinPluginConstant.WEIXIN_PLUGIN), new BusinessException(AuthorizationCode.PP0015, new Object[]{WeixinPluginConstant.WEIXIN_PLUGIN}));
		UserWeixin vo = JSON.parseObject(JSON.toJSONString(object), UserWeixin.class);
		// openid不能为空，不能重复
		if (StringUtils.isBlank(vo.getOpenid())) {
			throw new BusinessException(WeixinAuthorizationCode.WX0001);
		} else {
			checkIfNotNullThrowException(userWeixinService.findOneByOpenid(vo.getOpenid()), new BusinessException(WeixinAuthorizationCode.WX0002, new Object[]{vo.getOpenid()}));
		}
		// 保存user
		User user = userService.save(register);
		vo.setCode(user.getCode());
		// 保存userweixin
		save(vo);
		user.getPlugin().put(WeixinPluginConstant.WEIXIN_PLUGIN, vo);
		return user;
	}

	public void save(UserWeixin vo) {
		TCUserWeixin po = userWeixinService.findOneByCode(vo.getCode());
		if (null == po) {
			po = new TCUserWeixin();
			po.setCode(vo.getCode());
		}
		po.setOpenid(vo.getOpenid());
		po.setNickname(vo.getNickname());
		po.setSex(vo.getSex());
		po.setLanguage(vo.getLanguage());
		po.setCity(vo.getCity());
		po.setProvince(vo.getProvince());
		po.setCountry(vo.getCountry());
		po.setHeadimgurl(vo.getHeadimgurl());
		po.setUnionid(vo.getUnionid());
		userWeixinService.save(po);
	}

	@Override
	@Transactional
	public void deleteByCode(String code) {
		userService.deleteByCode(code);
		userWeixinService.deleteByCode(code);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		Object object = checkIfNullThrowException(user.getPlugin().get(WeixinPluginConstant.WEIXIN_PLUGIN), new BusinessException(AuthorizationCode.PP0015, new Object[]{WeixinPluginConstant.WEIXIN_PLUGIN}));
		UserWeixin vo = JSON.parseObject(JSON.toJSONString(object), UserWeixin.class);
		// openid不能为空
		if (StringUtils.isBlank(vo.getOpenid())) {
			throw new BusinessException(WeixinAuthorizationCode.WX0001);
		}
		// 更新user
		userService.save(user);
		// 更新userweixin
		vo.setCode(user.getCode());
		save(vo);
		user.getPlugin().put(WeixinPluginConstant.WEIXIN_PLUGIN, vo);
	}

	@Override
	public User findOneByCode(String userCode) {
		User user = userService.findOneByCode(userCode);
		if (null != user) {
			user.getPlugin().put(WeixinPluginConstant.WEIXIN_PLUGIN, mapOneIfNotNull(userWeixinService.findOneByCode(userCode), e -> convert(e)));
		}
		return user;
	}
}
