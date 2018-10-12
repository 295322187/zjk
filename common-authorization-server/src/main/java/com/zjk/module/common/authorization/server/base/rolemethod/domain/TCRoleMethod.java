package com.zjk.module.common.authorization.server.base.rolemethod.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_c_role_method")
@Data
public class TCRoleMethod extends DataEntity {

	private String roleCode;

	private String methodCode;

	public TCRoleMethod(String roleCode, String methodCode) {
		this.roleCode = roleCode;
		this.methodCode = methodCode;
	}

	public TCRoleMethod() {

	}
}
