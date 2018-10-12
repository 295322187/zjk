package com.zjk.module.common.authorization.client.api.businesscode.client;

import com.zjk.module.common.authorization.client.api.businesscode.domain.BusinessCode;
import com.zjk.module.common.base.domain.JsonContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.util.List;

@FeignClient(value = "${common.authorization.application.name:common-authorization}")
@RequestMapping("/common/authorization/api/businesscode")
public interface IBusinessCodeClient {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	JsonContainer<List<BusinessCode>> register(@RequestBody List<BusinessCode> vos);

	@RequestMapping(value = "/findOneByBusinessClazzAndBusinessCode", method = RequestMethod.GET)
	JsonContainer<BusinessCode> findOneByBusinessClazzAndBusinessCode(@RequestParam(value = "businessClazz") @NotBlank String businessClazz, @RequestParam(value = "businessCode") @NotBlank String businessCode);
}
