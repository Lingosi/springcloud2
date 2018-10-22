package org.client.b.service;

import org.springframework.stereotype.Component;

@Component
public class ServiceBFallback implements IServiceBService{

	@Override
	public String test3(String name) {
		
		return "出错了test3";
	}

	@Override
	public String test2() {
		return "出错了test2";
	}

}
