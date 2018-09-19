package com.zjk.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SampleClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleClientApplication.class, args);
	}
}
