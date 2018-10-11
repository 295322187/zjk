package com.zjk.module.common.authorization.client.api.businesscode.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BusinessCodeLang {

	@ApiModelProperty(value = "语言")
	private String lang;

	@ApiModelProperty(value = "客户消息")
	private String customerMessage;

	public BusinessCodeLang() {
	}

	public BusinessCodeLang(String lang, String customerMessage) {
		this.lang = lang;
		this.customerMessage = customerMessage;
	}
}
