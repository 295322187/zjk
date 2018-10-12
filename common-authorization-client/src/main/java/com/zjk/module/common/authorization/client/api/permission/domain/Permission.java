package com.zjk.module.common.authorization.client.api.permission.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Permission {

	@ApiModelProperty(value = "用户编号")
	private String login;

	@ApiModelProperty(value = "口令")
	private String token;

	@ApiModelProperty(value = "路径")
	@NotBlank
	private String path;

	@ApiModelProperty(value = "方法")
	@NotBlank
	private String method;

	@ApiModelProperty(value = "IP")
	@NotBlank
	private String ip;

	public Permission(String login, String token, String path, String method, String ip) {
		this.login = login;
		this.token = token;
		this.path = path;
		this.method = method;
		this.ip = ip;
	}

	public Permission() {

	}
}