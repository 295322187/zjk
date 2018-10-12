package com.zjk.module.common.authorization.server.base.usersettings.repository;

import com.zjk.module.common.authorization.server.base.usersettings.domain.TCUserSettings;
import com.zjk.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "usersettings")
public interface ITCUserSettingsRepository extends IDataRepository<TCUserSettings, Integer> {

	TCUserSettings findOneByCode(String code);

	TCUserSettings findOneByIdCard(String idCard);

	void deleteByCode(String code);
}
