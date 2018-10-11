package com.zjk.module.common.data.annotation;


import com.zjk.module.common.data.configuration.CommonDataConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({CommonDataConfiguration.class})
public @interface EnableCommonDataConfiguration {
}
