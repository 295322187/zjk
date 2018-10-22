package com.zjk.module.common.authorization.server.api.passport.biz;

import com.zjk.module.common.authorization.client.api.user.domain.User;

import java.util.Map;

public interface IPassportPluginService {

	String getPlugin();

	User login(String username);

	User register(Map<String, Object> plugin);

}
