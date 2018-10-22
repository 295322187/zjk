package com.zjk.module.common.authorization.server.weixin.plugin.base.userweixin.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_c_user_weixin")
@Data
public class TCUserWeixin extends DataEntity {

	private String code;

	private String openid;

	private String nickname;

	private Integer sex;

	private String language;

	private String city;

	private String province;

	private String country;

	@Column(columnDefinition = "text")
	private String headimgurl;

	private String unionid;


}
