package com.zjk.module.common.authorization.client.api.jsonwebtoken.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
public class JSONWebToken {

	@ApiModelProperty(value = "用户编号")
	@NotBlank
	private String login;

	@ApiModelProperty(value = "其他参数")
	private Map<String, Object> claims;

	public JSONWebToken(String login, Map<String, Object> claims) {
		this.login = login;
		this.claims = claims;
	}

	public JSONWebToken(String login) {

		this.login = login;
	}

	public JSONWebToken() {

	}
}
