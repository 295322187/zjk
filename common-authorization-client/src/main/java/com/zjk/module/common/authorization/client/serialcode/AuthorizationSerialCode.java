package com.zjk.module.common.authorization.client.serialcode;


import com.zjk.module.common.authorization.client.api.serialcode.constant.ISerialCode;

public enum AuthorizationSerialCode implements ISerialCode {

	/******************** 权限 ********************/
	CUSER("CUSER", "用户"),
	CROLE("CROLE", "角色"),
	CMETHOD("CMETHOD", "方法"),
	CBUSINESSCODE("CBUSINESSCODE", "业务代码");


	private String serialGroup;

	private String remark;

	AuthorizationSerialCode(String serialGroup, String remark) {
		this.serialGroup = serialGroup;
		this.remark = remark;
	}

	@Override
	public String getSerialGroup() {
		return serialGroup;
	}

	@Override
	public String getRemark() {
		return remark;
	}
}
