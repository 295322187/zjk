package com.zjk.module.common.authorization.server.base.method.biz;

import com.zjk.module.common.authorization.server.base.method.domain.TCMethod;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITCMethodService extends IDataService<TCMethod, Integer> {

	TCMethod newInstance();

	TCMethod findOneByApplicationNameAndMethodAndAndPattern(String applicationName, String method, String pattern);

	List<TCMethod> findAllByApplicationName(String applicationName);
}
