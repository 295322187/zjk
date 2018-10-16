package com.zjk.module.common.authorization.server.runner;

import com.alibaba.fastjson.JSON;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SerialCodeRegisterRunner extends CommonServiceImpl implements ApplicationRunner {


	private static final String BASE = "com.sbm.module.";

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("111111111111111111111");
		System.out.println(JSON.toJSONString(args));
	}

}
