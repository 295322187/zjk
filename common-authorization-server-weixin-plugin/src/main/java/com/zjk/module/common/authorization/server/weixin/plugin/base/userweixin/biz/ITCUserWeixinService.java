package com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.biz;

import com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.domain.TCUserWeixin;
import com.zjk.module.common.data.biz.IDataService;

public interface ITCUserWeixinService extends IDataService<TCUserWeixin, Integer> {

	TCUserWeixin findOneByCode(String code);

	TCUserWeixin findOneByOpenid(String openid);

	void deleteByCode(String code);
}
