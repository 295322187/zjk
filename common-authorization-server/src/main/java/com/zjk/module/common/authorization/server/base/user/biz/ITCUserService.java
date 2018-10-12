package com.zjk.module.common.authorization.server.base.user.biz;


import com.zjk.module.common.authorization.server.base.user.domain.TCUser;
import com.zjk.module.common.data.biz.IDataService;

public interface ITCUserService extends IDataService<TCUser, Integer> {

	TCUser newInstance();

	TCUser findOneByCode(String code);

	TCUser findOneByUsername(String username);

	TCUser findOneByEmail(String email);

	TCUser findOneByMobile(String mobile);

	void deleteByCode(String code);
}
