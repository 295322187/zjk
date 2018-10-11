package com.zjk.module.common.base.biz;


import com.zjk.module.common.base.domain.BaseEntity;

public interface IConvertService<M extends BaseEntity, N> {

	/**
	 * 转换
	 *
	 * @param po
	 * @param vo
	 * @return
	 */
	M convert(M po, N vo);

}
