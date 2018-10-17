package com.zjk.module.common.authorization.client.runner;

import com.zjk.module.common.authorization.client.annotation.EnableRegisterRunner;
import com.zjk.module.common.authorization.client.api.businesscode.biz.IBusinessCodeRegisterService;
import com.zjk.module.common.authorization.client.api.businesscode.client.IBusinessCodeClient;
import com.zjk.module.common.authorization.client.api.businesscode.constant.BusinessCodeLangConstant;
import com.zjk.module.common.authorization.client.api.businesscode.domain.BusinessCode;
import com.zjk.module.common.authorization.client.api.businesscode.domain.BusinessCodeLang;
import com.zjk.module.common.authorization.client.runner.handler.RegisterRunnerHandler;
import com.zjk.module.common.base.biz.impl.RunnerServiceImpl;
import com.zjk.module.common.base.exception.IBusinessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class BusinessCodeRegisterRunner extends RunnerServiceImpl implements ApplicationRunner {

	@Autowired
	private RegisterRunnerHandler handler;
	@Autowired
	private IBusinessCodeClient client;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<BusinessCode> vos = handler.process(runnerHandler(EnableRegisterRunner.class, e -> handler.handle(e)),
				IBusinessCode.class, e -> convert(e));
		process(IBusinessCodeRegisterService.class, e -> log(e.register(vos)),
				IBusinessCodeClient.class, e -> log(checkJsonContainer(e.register(vos))));
	}


	private BusinessCode convert(IBusinessCode businessCode) {
		BusinessCode vo = new BusinessCode(businessCode.getClazz(), businessCode.getCode(), businessCode.getMessage(), businessCode.getCustomerMessage());
		vo.getBusinessCodeLangs().add(new BusinessCodeLang(BusinessCodeLangConstant.ZH_CN, businessCode.getCustomerMessage()));
		return vo;
	}
}
