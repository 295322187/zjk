package com.zjk.module.common.base.adaptor.feign;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@FeignClient(name = "feignadaptor")
public interface FeignAdaptor {

	@RequestLine("GET")
	ResponseEntity<byte[]> getBytes(URI baseUri);


}
