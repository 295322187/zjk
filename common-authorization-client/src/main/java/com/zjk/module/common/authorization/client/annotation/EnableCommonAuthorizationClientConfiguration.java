package com.zjk.module.common.authorization.client.annotation;


import com.zjk.module.common.authorization.client.configuration.CommonAuthorizationClientConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({CommonAuthorizationClientConfiguration.class})
public @interface EnableCommonAuthorizationClientConfiguration {
}
