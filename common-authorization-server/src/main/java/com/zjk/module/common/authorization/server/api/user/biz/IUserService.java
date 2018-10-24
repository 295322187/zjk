package com.zjk.module.common.authorization.server.api.user.biz;


import com.zjk.module.common.authorization.client.api.passport.domain.Register;
import com.zjk.module.common.authorization.client.api.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

	User save(Register register);

	void save(User user);

	String encryptPassword(String password);

	Page<User> findAll(Pageable pageable);

	User findOneByCode(String code);

	User findOneByUsername(String username);

	void updateLastLogin(String code);

	void updateName(String code, String name);

	void updateNameAndIdCard(String code, String name, String idCard, Integer idCardType);

	void updatePassword(String code, String password);

	void deleteByCode(String code);
}
