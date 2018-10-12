package com.zjk.module.common.authorization.server.base.method.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_c_method")
@Data
public class TCMethod extends DataEntity {

	private String code;

	private String applicationName;

	private String method;

	private String pattern;

	private Integer validFlag;

	private String remark;

}
