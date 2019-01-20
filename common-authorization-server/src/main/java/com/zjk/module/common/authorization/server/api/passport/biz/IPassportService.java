package com.zjk.module.common.authorization.server.api.passport.biz;


import com.zjk.module.common.authorization.client.api.passport.domain.ChangePassword;
import com.zjk.module.common.authorization.client.api.passport.domain.ForgetPassword;
import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.user.domain.User;

public interface IPassportService {

	/********** 登录 **********/

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

	/**
	 * 登录简单版
	 *
	 * @param username
	 * @param plugin
	 * @return
	 */
	User loginSimple(String username, String plugin);

	/**
	 * 更新最后登录时间
	 *
	 * @param code
	 */
	void updateLastLogin(String code);

	/********** 注册 **********/

	/**
	 * 用户注册
	 * 没有插件手机号和邮箱校验非空，重复
	 * 有插件手机号和邮箱校验重复，非空逻辑在业务层判断
	 *
	 * @param register
	 * @param plugin
	 * @return
	 */
	User register(Register register, String plugin);

	/**
	 * 更新姓名
	 *
	 * @param code
	 * @param name
	 */
	void updateName(String code, String name);

	/**
	 * 更新姓名和职业
	 *
	 * @param code
	 * @param name
	 * @param profession
	 */
	void updateNameAndProfession(String code, String name, String profession);

	/**
	 * 更新证件信息
	 *
	 * @param code
	 * @param name
	 * @param idCard
	 * @param idCardType
	 */
	void updateNameAndIdCard(String code, String name, String idCard, Integer idCardType);

	/**
	 * 忘记密码
	 *
	 * @param vo
	 */
	void forgetPassword(ForgetPassword vo);

	/**
	 * 修改密码
	 *
	 * @param vo
	 */
	void changePassword(ChangePassword vo);

	/**
	 * 通过用户编号查询用户信息
	 *
	 * @param userCode
	 * @param plugin
	 * @return
	 */
	User findOneByCode(String userCode, String plugin);

	/**
	 * 通过用户名查询用户信息
	 *
	 * @param username
	 * @param plugin
	 * @return
	 */
	User findOneByUsername(String username, String plugin);

	/**
	 * 更新用户信息
	 *
	 * @param user
	 * @param plugin
	 * @return
	 */
	User updateUser(User user, String plugin);

	/**
	 * 通过编号删除
	 *
	 * @param code
	 * @param plugin
	 */
	void deleteByCode(String code, String plugin);
}
