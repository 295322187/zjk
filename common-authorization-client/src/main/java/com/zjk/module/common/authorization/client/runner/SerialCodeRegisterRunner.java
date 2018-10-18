package com.zjk.module.common.authorization.client.runner;

import com.zjk.module.common.base.annotation.EnableRegisterRunner;
import com.zjk.module.common.authorization.client.api.serialcode.biz.ISerialCodeRegisterService;
import com.zjk.module.common.authorization.client.api.serialcode.client.ISerialCodeClient;
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
		process(ISerialCodeRegisterService.class, e -> log(e.register(vos)),
				ISerialCodeClient.class, e -> log(checkJsonContainer(e.register(vos))));
	}

	private SerialCode convert(ISerialCode serialCode) {
		return new SerialCode(serialCode.getSerialGroup(), serialCode.getRemark());
	}
}
