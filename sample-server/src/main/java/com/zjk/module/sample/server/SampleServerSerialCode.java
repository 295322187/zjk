package com.zjk.module.sample.server;


import com.zjk.module.common.authorization.client.api.serialcode.constant.ISerialCode;

public enum SampleServerSerialCode implements ISerialCode {

	/******************** 权限 ********************/
	SAMPLE("SAMPLE", "样例");


	private String serialGroup;

	private String remark;

	SampleServerSerialCode(String serialGroup, String remark) {
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
