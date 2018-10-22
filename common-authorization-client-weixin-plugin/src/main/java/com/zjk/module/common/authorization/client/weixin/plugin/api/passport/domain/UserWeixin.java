package com.zjk.module.common.authorization.client.weixin.plugin.api.passport.domain;

import io.swagger.annotations.ApiModelProperty;

public class UserWeixin {

	@ApiModelProperty(value = "openid")
	private String openid;

	@ApiModelProperty(value = "昵称")
	private String nickname;

	@ApiModelProperty(value = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
	private Integer sex;

	@ApiModelProperty(value = "语言")
	private String language;

	@ApiModelProperty(value = "用户个人资料填写的省份")
	private String city;

	@ApiModelProperty(value = "普通用户个人资料填写的城市")
	private String province;

	@ApiModelProperty(value = "国家，如中国为CN")
	private String country;

	@ApiModelProperty(value = "用户头像")
	private String headimgurl;

	@ApiModelProperty(value = "只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。")
	private String unionid;

	public UserWeixin(String openid, String nickname, Integer sex, String language, String city, String province, String country, String headimgurl, String unionid) {
		this.openid = openid;
		this.nickname = nickname;
		this.sex = sex;
		this.language = language;
		this.city = city;
		this.province = province;
		this.country = country;
		this.headimgurl = headimgurl;
		this.unionid = unionid;
	}

	public UserWeixin() {
	}
}
