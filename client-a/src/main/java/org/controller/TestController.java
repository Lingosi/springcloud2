package org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
	@Autowired
    RestTemplate restTemplate;

    @RequestMapping(value="/hi")
    public String hi(@RequestParam String id){
        return restTemplate.getForObject("http://service/s-a/sa/hi?id="+id, String.class);
    }
    
    @RequestMapping(value="/test2")
    public String test2(){
    	//请求地址：application.name + context-path + interface
        return restTemplate.getForObject("http://service-b/s-b/sb/test2", String.class);
    }
}
