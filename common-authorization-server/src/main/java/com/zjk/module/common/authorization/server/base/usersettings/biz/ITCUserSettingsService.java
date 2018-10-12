package com.zjk.module.common.authorization.server.base.usersettings.biz;

import com.zjk.module.common.authorization.server.base.usersettings.domain.TCUserSettings;
import com.zjk.module.common.data.biz.IDataService;

public interface ITCUserSettingsService extends IDataService<TCUserSettings, Integer> {

	TCUserSettings findOneByCode(String code);

	TCUserSettings findOneByIdCard(String idCard);

	void deleteByCode(String code);
}
