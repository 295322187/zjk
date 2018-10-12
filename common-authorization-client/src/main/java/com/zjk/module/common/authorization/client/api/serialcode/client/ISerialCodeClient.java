package com.zjk.module.common.authorization.client.api.serialcode.client;


import com.zjk.module.common.authorization.client.api.serialcode.domain.SerialCode;
import com.zjk.module.common.base.domain.JsonContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "${common.authorization.application.name:common-authorization}")
@RequestMapping("/common/authorization/api/serialcode")
public interface ISerialCodeClient {

	@RequestMapping(value = "/next/{serialGroup}", method = RequestMethod.GET)
	JsonContainer<String> next(@PathVariable(value = "serialGroup") String serialGroup);

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	JsonContainer<List<SerialCode>> register(@RequestBody List<SerialCode> vos);

}
