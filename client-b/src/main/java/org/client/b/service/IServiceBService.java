package org.client.b.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-b", fallback=ServiceBFallback.class)
public interface IServiceBService {
	
	@RequestMapping(value="/s-b/sb/test3",method = RequestMethod.GET)
	//需指定参数名称，否则报错feign.FeignException: status 400 reading IServiceBService#test3(String); content:
	public String test3(@RequestParam(value="name") String name);
	
	@RequestMapping(value="/sb/test2",method = RequestMethod.GET)
	public String test2();
}
