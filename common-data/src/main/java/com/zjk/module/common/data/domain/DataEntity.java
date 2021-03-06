package com.zjk.module.common.data.domain;

import com.zjk.module.common.base.domain.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class DataEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "timestamp", updatable = false)
	private Date created;

	@Column(columnDefinition = "timestamp")
	private Date updated;

	private Integer state;

}
