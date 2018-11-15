package com.zjk.module.common.base.adaptor.feign.impl;

import com.zjk.module.common.base.adaptor.feign.FeignAdaptor;
import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@Import(FeignClientsConfiguration.class)
public class FeignAdaptorProvider implements FeignAdaptor {

	private FeignAdaptor feignAdaptor;

	@Autowired
	public FeignAdaptorProvider(Decoder decoder, Encoder encoder) {
		feignAdaptor = Feign.builder()
				.encoder(encoder)
				.decoder(decoder)
				.target(Target.EmptyTarget.create(FeignAdaptor.class));
	}

	@Override
	public ResponseEntity<byte[]> getBytes(URI baseUri) {
		return feignAdaptor.getBytes(baseUri);
	}
}
