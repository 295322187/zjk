package com.zjk.module.common.redis.annotation;


import com.zjk.module.common.redis.configuration.CommonRedisConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({CommonRedisConfiguration.class})
public @interface EnableCommonRedisConfiguration {
}
