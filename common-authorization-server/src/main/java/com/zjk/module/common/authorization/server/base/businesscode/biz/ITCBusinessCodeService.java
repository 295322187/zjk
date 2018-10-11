package com.zjk.module.common.authorization.server.base.businesscode.biz;


import com.zjk.module.common.authorization.server.base.businesscode.domain.TCBusinessCode;
import com.zjk.module.common.data.biz.IDataService;

public interface ITCBusinessCodeService extends IDataService<TCBusinessCode, Integer> {

	TCBusinessCode newInstance();

	TCBusinessCode findOneByCode(String code);

	TCBusinessCode findOneByBusinessClazzAndBusinessCode(String businessClazz, String businessCode);
}
