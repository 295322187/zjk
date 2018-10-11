package com.zjk.module.common.base.annotation;


import com.zjk.module.common.base.configuration.CommonBaseConfiguration;
import com.zjk.module.common.base.configuration.FeignConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({CommonBaseConfiguration.class, FeignConfiguration.class})
public @interface EnableCommonBaseConfiguration {
}
