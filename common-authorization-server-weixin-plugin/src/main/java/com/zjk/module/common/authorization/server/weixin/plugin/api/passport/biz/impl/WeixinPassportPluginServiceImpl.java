package com.zjk.module.common.authorization.server.weixin.plugin.api.passport.biz.impl;

import com.zjk.module.common.authorization.client.api.user.domain.User;
import com.zjk.module.common.authorization.client.weixin.plugin.api.passport.constant.WeixinPluginConstant;
import com.zjk.module.common.authorization.server.api.passport.biz.IPassportPluginService;
import com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.biz.ITCUserWeixinService;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WeixinPassportPluginServiceImpl extends CommonServiceImpl implements IPassportPluginService {

	@Autowired
	private ITCUserWeixinService userWeixinService;

	@Override
	public String getPlugin() {
		return WeixinPluginConstant.WEIXIN_PLUGIN;
	}

	@Override
	public User login(String username) {
		return null;
	}

	@Override
	public User register(Map<String, Object> plugin) {

		return null;
	}
}
