package com.zjk.module.common.authorization.client.api.verificationcode.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VerificationCodeCheck {

	@ApiModelProperty(value = "键")
	@NotBlank
	private String key;

	@ApiModelProperty(value = "验证码")
	@NotBlank
	private String code;

	@ApiModelProperty(value = "关键字")
	@NotBlank
	private String keyword;

	@ApiModelProperty(value = "校验类型")
	private String verifyType;

	public VerificationCodeCheck() {
	}

	public VerificationCodeCheck(String key, String code, String keyword) {
		this.key = key;
		this.code = code;
		this.keyword = keyword;
	}
}
