package com.zjk.module.common.base.biz.impl;

import com.zjk.module.common.base.provider.SpringContextProvider;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessServiceImpl extends CommonServiceImpl {

	@Autowired
	public SpringContextProvider provider;

}
