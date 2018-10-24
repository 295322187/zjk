package com.zjk.module.common.authorization.server.api.passport.biz;

import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.user.domain.User;

public interface IPassportPluginService {

	String getPlugin();

	User login(String username);

	User register(Register register);

	void deleteByCode(String code);

	void updateUser(User user);
}
