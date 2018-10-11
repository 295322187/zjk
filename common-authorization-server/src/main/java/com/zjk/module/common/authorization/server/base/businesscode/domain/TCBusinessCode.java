package com.zjk.module.common.authorization.server.base.businesscode.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_c_business_code")
@Data
public class TCBusinessCode extends DataEntity {

	private String code;

	private String businessClazz;

	private String businessCode;

	private String message;

	private String customerMessage;

}
