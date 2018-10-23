package com.zjk.module.common.authorization.client.weixin.plugin.exception;


import com.zjk.module.common.base.exception.IBusinessCode;

public enum WeixinAuthorizationCode implements IBusinessCode {

	/******************** passport ********************/
	WX0001("WX0001", "openid不能为空", "openid不能为空");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	WeixinAuthorizationCode(String code, String message, String customerMessage) {
		this.clazz = this.getClass().getName();
		this.code = code;
		this.message = message;
		this.customerMessage = customerMessage;
	}

	@Override
	public String getClazz() {
		return clazz;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getCustomerMessage() {
		return customerMessage;
	}

	@Override
	public Object getData() {
		return null;
	}
}
