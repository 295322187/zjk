package com.zjk.module.common.base.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface EnableRegisterRunner {

	@AliasFor("basePackages")
	String[] value() default {};

	@AliasFor("value")
	String[] basePackages() default {};

	String pattern() default ".configuration";
}
