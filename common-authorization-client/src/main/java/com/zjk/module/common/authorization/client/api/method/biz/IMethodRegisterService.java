package com.zjk.module.common.authorization.client.api.method.biz;

import com.zjk.module.common.authorization.client.api.method.domain.Method;

import java.util.List;

public interface IMethodRegisterService {

	/**
	 * 注册方法
	 *
	 * @param vos
	 */
	void register(List<Method> vos);

}
