package com.zjk.module.common.authorization.server.configuration;

import com.zjk.module.common.authorization.client.annotation.EnableCommonAuthorizationClientConfiguration;
import com.zjk.module.common.data.annotation.EnableCommonDataConfiguration;
import com.zjk.module.common.redis.annotation.EnableCommonRedisConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(
		basePackages = {"com.zjk.module.common.authorization.server"}
)
@EnableJpaRepositories(basePackages = {"com.zjk.module.common.authorization.server"})
@EntityScan(basePackages = {"com.zjk.module.common.authorization.server"})
@EnableCommonDataConfiguration
@EnableCommonRedisConfiguration
@EnableCommonAuthorizationClientConfiguration
public class CommonAuthorizationServerConfiguration {

}
