package com.zjk.module.common.annotation;

import com.zjk.module.common.configuration.CommonConfiguration;
import com.zjk.module.common.configuration.FeignConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({CommonConfiguration.class, FeignConfiguration.class})
public @interface EnableCommonConfiguration {
}
