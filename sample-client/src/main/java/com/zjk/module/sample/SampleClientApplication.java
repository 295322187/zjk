package com.zjk.module.sample;

import com.zjk.module.common.authorization.client.annotation.EnableCommonAuthorizationClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCommonAuthorizationClientConfiguration
public class SampleClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleClientApplication.class, args);
	}
}
