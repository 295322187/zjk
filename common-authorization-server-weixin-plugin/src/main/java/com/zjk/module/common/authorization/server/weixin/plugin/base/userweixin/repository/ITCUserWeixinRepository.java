package com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.repository;

import com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.domain.TCUserWeixin;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "userweixin")
public interface ITCUserWeixinRepository extends IDataRepository<TCUserWeixin, Integer> {

	TCUserWeixin findOneByCode(String code);

	TCUserWeixin findOneByOpenid(String openid);

	void deleteByCode(String code);
}
