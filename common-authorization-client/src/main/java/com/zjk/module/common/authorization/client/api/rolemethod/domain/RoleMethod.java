package com.zjk.module.common.authorization.client.api.rolemethod.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleMethod {

	@ApiModelProperty(value = "角色编号")
	@NotBlank
	private String roleCode;

	@ApiModelProperty(value = "方法编号")
	@NotBlank
	private String methodCode;

	public RoleMethod() {
	}

	public RoleMethod(String roleCode, String methodCode) {

		this.roleCode = roleCode;
		this.methodCode = methodCode;
	}
}
