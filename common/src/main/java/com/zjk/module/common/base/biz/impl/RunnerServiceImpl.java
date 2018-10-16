package com.zjk.module.common.base.biz.impl;

import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RunnerServiceImpl extends BusinessServiceImpl {

	protected List<Map.Entry<String, Object>> getClazz(Class<? extends Annotation> clazz) {
		Map<String, Object> map = provider.getBeansWithAnnotation(clazz);
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(e -> {
					Order order = e.getClass().getAnnotation(Order.class);
					return order == null ? 9999 : order.value();
				}
		))).collect(Collectors.toList());
	}

	protected <R> List<R> runnerHandler(Class<? extends Annotation> clazz, Function<? super Map.Entry<String, Object>, ? extends R> mapper) {
		return runnerHandler(getClazz(clazz), mapper);
	}

	protected <R> List<R> runnerHandler(List<Map.Entry<String, Object>> list, Function<? super Map.Entry<String, Object>, ? extends R> mapper) {
		return map(list, mapper);
	}


}
