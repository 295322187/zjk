package com.zjk.module.common.authorization.client.api.serialcode.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SerialCode {

	@ApiModelProperty(value = "序列组")
	@NotBlank
	private String serialGroup;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "操作")
	private String operate;

	public SerialCode() {
	}

	public SerialCode(String serialGroup, String remark) {
		this.serialGroup = serialGroup;
		this.remark = remark;
	}
}
