package com.zjk.module.common.authorization.client.runner;

import com.alibaba.fastjson.JSON;
import com.zjk.module.common.authorization.client.annotation.EnableRegisterRunner;
import com.zjk.module.common.authorization.client.api.serialcode.constant.ISerialCode;
import com.zjk.module.common.authorization.client.api.serialcode.domain.SerialCode;
import com.zjk.module.common.authorization.client.runner.handler.RegisterRunnerHandler;
import com.zjk.module.common.base.biz.impl.RunnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class SerialCodeRegisterRunner extends RunnerServiceImpl implements ApplicationRunner {

	@Autowired
	private RegisterRunnerHandler handler;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<SerialCode> vos = handler.process(runnerHandler(EnableRegisterRunner.class, e -> handler.handle(e)),
				ISerialCode.class, e -> convert(e));
		System.out.println(JSON.toJSONString(vos));
	}


	private SerialCode convert(ISerialCode serialCode) {
		return new SerialCode(serialCode.getSerialGroup(), serialCode.getRemark());
	}
}
