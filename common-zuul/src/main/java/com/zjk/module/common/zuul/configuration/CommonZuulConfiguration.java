package com.zjk.module.common.zuul.configuration;

import com.zjk.module.common.authorization.client.annotation.EnableCommonAuthorizationClientConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@ComponentScan(basePackages = {"com.zjk.module.common.zuul"})
@EnableZuulProxy
@EnableFeignClients(basePackages = {"com.zjk.module.common.zuul"})
@EnableCommonAuthorizationClientConfiguration
public class CommonZuulConfiguration {

	/**
	 * 添加cors处理
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.addExposedHeader("Content-Type, Login, Authorization, Lang, Source");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new org.springframework.web.filter.CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
}
