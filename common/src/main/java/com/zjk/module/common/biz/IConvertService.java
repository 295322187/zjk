package com.zjk.module.common.biz;


import com.zjk.module.common.domain.BaseEntity;

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
