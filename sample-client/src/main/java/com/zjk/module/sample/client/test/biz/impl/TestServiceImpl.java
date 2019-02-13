package com.zjk.module.sample.client.test.biz.impl;

import com.zjk.module.sample.client.test.biz.ITestService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {
	@Override
	@SneakyThrows
	public void threadTest(String test) {
		for (int i = 0; i < 5; i++) {
			System.out.println(test + " : " + i);
			Thread.sleep(1000);
		}
	}
}
