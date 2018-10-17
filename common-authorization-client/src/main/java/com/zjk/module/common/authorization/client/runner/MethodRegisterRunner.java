package com.zjk.module.common.authorization.client.runner;

import com.alibaba.fastjson.JSON;
import com.zjk.module.common.authorization.client.annotation.EnableRegisterRunner;
import com.zjk.module.common.authorization.client.api.method.constant.MethodConstant;
import com.zjk.module.common.authorization.client.api.method.domain.Method;
import com.zjk.module.common.authorization.client.exception.AuthorizationCode;
import com.zjk.module.common.authorization.client.runner.handler.RegisterRunnerHandler;
import com.zjk.module.common.base.biz.impl.RunnerServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Order(3)
public class MethodRegisterRunner extends RunnerServiceImpl implements ApplicationRunner {

	@Autowired
	private RegisterRunnerHandler handler;

	@Value("${spring.application.name}")
	private String applicationName;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Method> vos = process();
		System.out.println(JSON.toJSONString(vos));


	}

	private List<Method> process() {
		List<Method> vos = new ArrayList<>();
		List<String> basePackages = handler.arrayToString(runnerHandler(EnableRegisterRunner.class, e -> handler.handle(e)));
		// 获取所有RequestMappingHandlerMapping 如果需要其他再添加
		RequestMappingHandlerMapping requestMappingHandlerMapping = provider.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
		map.keySet().forEach(e -> {
			HandlerMethod method = map.get(e);
			// 方法类以basePackage开头，不包含.client.
			if (basePackages.stream().anyMatch(s -> method.getBeanType().getName().startsWith(s))) {
				if (e.getMethodsCondition().getMethods().size() != 1) {
					throw new BusinessException(AuthorizationCode.M0001, new Object[]{method.getBeanType().getName(), method.getMethod().getName()});
				}
				if (e.getPatternsCondition().getPatterns().size() != 1) {
					throw new BusinessException(AuthorizationCode.M0002, new Object[]{method.getBeanType().getName(), method.getMethod().getName()});
				}
				RequestMethod requestMethod = e.getMethodsCondition().getMethods().iterator().next();
				String pattern = e.getPatternsCondition().getPatterns().iterator().next();
				// 加入列表
				vos.add(new Method(applicationName, requestMethod.toString(), pattern, MethodConstant.VALID_FLAG_1, null));
			}
		});
		return vos;
	}

}
