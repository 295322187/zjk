package com.zjk.module.common.authorization.server.base.userrole.domain;

import com.zjk.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_c_user_role")
@Data
public class TCUserRole extends DataEntity {

	private String userCode;

	private String roleCode;

	public TCUserRole(String userCode, String roleCode) {
		this.userCode = userCode;
		this.roleCode = roleCode;
	}

	public TCUserRole() {

	}
}
