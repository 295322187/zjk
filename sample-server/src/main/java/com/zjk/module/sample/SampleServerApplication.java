package com.zjk.module.sample;

import com.zjk.module.common.authorization.server.annotation.EnableCommonAuthorizationServerConfiguration;
import com.zjk.module.common.base.annotation.EnableCommonBaseConfiguration;
import com.zjk.module.common.redis.annotation.EnableCommonRedisConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableCommonAuthorizationServerConfiguration
@EnableCommonBaseConfiguration
@EnableCommonRedisConfiguration
public class SampleServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleServerApplication.class, args);
	}
}
