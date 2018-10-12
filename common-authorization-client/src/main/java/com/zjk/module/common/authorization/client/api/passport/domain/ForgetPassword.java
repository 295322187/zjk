package com.zjk.module.common.authorization.client.api.passport.domain;

import com.zjk.module.common.authorization.client.api.verificationcode.domain.BaseVerificationCodeCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ForgetPassword extends BaseVerificationCodeCheck {

	@ApiModelProperty(value = "用户名")
	@NotBlank
	private String username;

	@ApiModelProperty(value = "新密码")
	@NotBlank
	private String newPassword;

}
