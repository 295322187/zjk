package com.zjk.module.common.authorization.server.configuration;

import com.zjk.module.common.base.annotation.EnableCommonBaseConfiguration;
import com.zjk.module.common.data.annotation.EnableCommonDataConfiguration;
import com.zjk.module.common.redis.annotation.EnableCommonRedisConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
		basePackages = {"com.zjk.module.common.authorization.server"}
)
@EnableCommonBaseConfiguration
@EnableCommonDataConfiguration
@EnableCommonRedisConfiguration
public class CommonAuthorizationServerConfiguration {


}
