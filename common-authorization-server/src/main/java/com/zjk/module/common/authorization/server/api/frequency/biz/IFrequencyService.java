package com.zjk.module.common.authorization.server.api.frequency.biz;


import com.zjk.module.common.authorization.client.api.frequency.domain.Frequency;

public interface IFrequencyService {

	/**
	 * 检查频率
	 *
	 * @param vo
	 */
	void checkFrequency(Frequency vo);

}
