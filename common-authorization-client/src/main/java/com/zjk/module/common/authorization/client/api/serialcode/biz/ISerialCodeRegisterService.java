package com.zjk.module.common.authorization.client.api.serialcode.biz;


import com.zjk.module.common.authorization.client.api.serialcode.domain.SerialCode;

import java.util.List;

public interface ISerialCodeRegisterService {

	/**
	 * 注册序列号
	 *
	 * @param vos
	 * @return
	 */
	List<SerialCode> register(List<SerialCode> vos);

}
