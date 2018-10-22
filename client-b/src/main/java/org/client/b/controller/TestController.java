package org.client.b.controller;

import org.client.b.service.IServiceBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientb")
public class TestController {
	
	@Autowired
    IServiceBService bService;
	
	@GetMapping(value="/test1")
	public String test1(String name){
		return bService.test3(name);
	}
	
	@GetMapping(value="/test2")
	public String test2(){
		return bService.test2();
	}
}
