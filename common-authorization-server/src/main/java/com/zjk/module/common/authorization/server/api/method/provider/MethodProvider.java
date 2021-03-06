package com.zjk.module.common.authorization.server.api.method.provider;

import com.zjk.module.common.authorization.client.api.method.domain.Method;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@Validated
public class MethodProvider {

	/**
	 * spring ant path 校验器
	 */
	private static AntPathMatcher MATCHER = new AntPathMatcher();

	/**
	 * 匹配资源
	 *
	 * @param path
	 * @param method
	 * @param e
	 */
	public boolean match(@NotBlank String path, @NotBlank String method, @NotNull Method e) {
		return method.equals(e.getMethod()) && MATCHER.match(getPath(e.getApplicationName(), e.getPattern()), path);
	}

	/**
	 * 获取路径
	 *
	 * @param applicationName
	 * @param pattern
	 * @return
	 */
	public String getPath(String applicationName, String pattern) {
		return new StringBuffer(AntPathMatcher.DEFAULT_PATH_SEPARATOR).append(applicationName).append(pattern).toString();
	}
}
