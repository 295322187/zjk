package com.zjk.module.common.authorization.client.runner.handler;

import com.zjk.module.common.base.annotation.EnableRegisterRunner;
import com.zjk.module.common.base.biz.impl.RunnerServiceImpl;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Component
public class RegisterRunnerHandler extends RunnerServiceImpl {

	/**
	 * 处理annotation
	 *
	 * @param entry
	 * @return
	 */
	public String[] handle(Map.Entry<String, Object> entry) {
		Object object = entry.getValue();
		// 获取annotation
		EnableRegisterRunner annotation = object.getClass().getAnnotation(EnableRegisterRunner.class);
		// 处理basePackages，有basePackages用basePackages
		String[] basePackages = annotation.basePackages();
		// 没有取包名
		if (0 == basePackages.length) {
			String packageName = object.getClass().getPackage().getName();
			if (packageName.endsWith(annotation.pattern())) {
				packageName.substring(0, packageName.length() - annotation.pattern().length());
			}
			basePackages = new String[]{packageName};
		}
		return basePackages;
	}

	/**
	 * 数组转字符串
	 *
	 * @param result
	 * @return
	 */
	public List<String> arrayToString(List<String[]> result) {
		List<String> basePackages = new ArrayList<>();
		result.forEach(e -> {
			for (String s : e) {
				basePackages.add(s);
			}
		});
		return basePackages;
	}


	/**
	 * 处理实例
	 *
	 * @param result
	 * @param clazz
	 * @param mapper
	 * @param <T>
	 * @param <R>
	 * @return
	 */
	public <T, R> List<R> process(List<String[]> result, Class<T> clazz, Function<? super T, ? extends R> mapper) {
		List<String> basePackages = arrayToString(result);
		List<R> vos = new ArrayList<>();
		Reflections reflections = new Reflections(basePackages);
		Set<Class<? extends T>> set = reflections.getSubTypesOf(clazz);
		for (Class<? extends T> tmp : set) {
			// 判断枚举
			if (tmp.isEnum()) {
				T[] ts = tmp.getEnumConstants();
				// 遍历枚举实例
				for (T t : ts) {
					vos.add(mapper.apply(t));
				}
			}
		}
		return vos;
	}


}
