package com.zjk.module.common.authorization.server.api.verificationcode.biz;


import com.zjk.module.common.authorization.client.api.verificationcode.domain.VerificationCode;
import com.zjk.module.common.authorization.client.api.verificationcode.domain.VerificationCodeCheck;
import com.zjk.module.common.authorization.client.api.verificationcode.domain.VerificationCodeSetting;

public interface IVerificationCodeService {

	/**
	 * 生成验证码
	 *
	 * @param setting
	 * @return
	 */
	VerificationCode generate(VerificationCodeSetting setting);

	/**
	 * 校验验证码
	 *
	 * @param check
	 */
	void check(VerificationCodeCheck check);

}
