package com.zjk.module.common.authorization.server.base.businesscodelang.biz;


import com.zjk.module.common.authorization.server.base.businesscodelang.domain.TCBusinessCodeLang;
import com.zjk.module.common.data.biz.IDataService;

import java.util.List;

public interface ITCBusinessCodeLangService extends IDataService<TCBusinessCodeLang, Integer> {

	List<TCBusinessCodeLang> findAllByCode(String code);

	TCBusinessCodeLang findOneByCodeAndLang(String code, String lang);

}
