package com.zjk.module.common.authorization.server.api.jsonwebtoken.biz.impl;

import com.zjk.module.common.authorization.client.api.jsonwebtoken.domain.JSONWebToken;
import com.zjk.module.common.authorization.server.api.jsonwebtoken.biz.IJSONWebTokenService;
import com.zjk.module.common.authorization.server.api.jsonwebtoken.provider.JSONWebTokenProvider;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JSONWebTokenServiceImpl extends CommonServiceImpl implements IJSONWebTokenService {

	@Autowired
	private JSONWebTokenProvider util;

	@Override
	public String token(JSONWebToken jsonWebToken) {
		return util.token(jsonWebToken);
	}

	@Override
	public Jws<Claims> parse(String token) {
		return util.parse(token);
	}

	@Override
	public void valid(String login, String token) {
		util.valid(login, token);
	}
}
