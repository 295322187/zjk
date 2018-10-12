package com.zjk.module.common.authorization.server.api.jsonwebtoken.biz;


import com.zjk.module.common.authorization.client.api.jsonwebtoken.domain.JSONWebToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface IJSONWebTokenService {

	String token(JSONWebToken jsonWebToken);

	Jws<Claims> parse(String token);

	void valid(String login, String token);

}
