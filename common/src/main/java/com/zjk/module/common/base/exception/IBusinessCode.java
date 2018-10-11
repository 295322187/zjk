package com.zjk.module.common.base.exception;

public interface IBusinessCode {

	String getClazz();

	String getCode();

	String getMessage();

	String getCustomerMessage();

	Object getData();
}
