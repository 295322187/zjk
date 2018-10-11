package com.zjk.module.common.authorization.client.api.serialcode.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SerialCodeRemark {

	/**
	 * 备注
	 *
	 * @return
	 */
	String remark() default "";

}
