package com.zjk.module.common.authorization.server.annotation;


import com.zjk.module.common.authorization.server.configuration.CommonAuthorizationServerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({CommonAuthorizationServerConfiguration.class})
public @interface EnableCommonAuthorizationServerConfiguration {
}
