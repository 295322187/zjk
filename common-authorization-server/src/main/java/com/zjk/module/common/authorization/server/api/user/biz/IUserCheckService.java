package com.zjk.module.common.authorization.server.api.user.biz;


public interface IUserCheckService {

	/********** 检查（不存在） **********/

	/**
	 * 手机号不存在
	 *
	 * @param mobile
	 */
	void isNotExistMobile(String mobile);

	/**
	 * 邮箱不存在
	 *
	 * @param email
	 */
	void isNotExistEmail(String email);

	/**
	 * 证件不存在
	 *
	 * @param idCard
	 */
	void isNotExistIdCard(String idCard);

	/********** 检查（存在） **********/

	/**
	 * 手机号存在
	 *
	 * @param mobile
	 */
	void existMobile(String mobile);

	/**
	 * 邮箱存在
	 *
	 * @param email
	 */
	void existEmail(String email);

	/**
	 * 证件存在
	 *
	 * @param idCard
	 */
	void existIdCard(String idCard);

	/**
	 * 用户编号存在
	 *
	 * @param code
	 */
	void existCode(String code);

	/**
	 * 用户拥有指定角色
	 *
	 * @param userCode
	 * @param role
	 */
	void existUserRole(String userCode, String role);

}
