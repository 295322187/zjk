package com.zjk.module.common.zuul.annotation;


import com.zjk.module.common.zuul.configuration.CommonZuulConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({CommonZuulConfiguration.class})
public @interface EnableCommonZuulConfiguration {
}
