package com.zjk.module.common.authorization.client.api.passport.domain;

import com.zjk.module.common.authorization.client.api.verificationcode.domain.BaseVerificationCodeCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePassword extends BaseVerificationCodeCheck {

	@ApiModelProperty(value = "用户编号")
	@NotBlank
	private String code;

	@ApiModelProperty(value = "旧密码")
	@NotBlank
	private String oldPassword;

	@ApiModelProperty(value = "新密码")
	@NotBlank
	private String newPassword;

}
