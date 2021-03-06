package com.zjk.module.common.authorization.client.api.frequency.domain;

import lombok.Data;

@Data
public class Frequency {

	private Integer count = 0;

	/**
	 * ip
	 */
	private String ip;

	/**
	 * 用户编号
	 */
	private String login;

	/**
	 * 方法
	 */
	private String method;

	/**
	 * 路径
	 */
	private String path;

	/**
	 * 60秒
	 */
	private Long timeout = 60L;

	/**
	 * 限制30次
	 */
	private Integer limit = 30;

	private String key;

	private Long expire;

	public Frequency(String ip, String login, String method, String path) {
		this.ip = ip;
		this.login = login;
		this.method = method;
		this.path = path;
	}

	public Frequency(String ip, String login, String method, String path, Long timeout, Integer limit) {
		this.ip = ip;
		this.login = login;
		this.method = method;
		this.path = path;
		this.timeout = timeout;
		this.limit = limit;
	}

	public Frequency() {
	}
}
