package com.zjk.module.common.authorization.client.configuration;

import com.zjk.module.common.base.annotation.EnableRegisterRunner;
import com.zjk.module.common.base.annotation.EnableCommonBaseConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.zjk.module.common.authorization.client"})
@EnableCommonBaseConfiguration
@EnableFeignClients(basePackages = {"com.zjk.module.common.authorization.client"})
@EnableRegisterRunner(basePackages = {"com.zjk.module.common.authorization.client"})
public class CommonAuthorizationClientConfiguration {


}
