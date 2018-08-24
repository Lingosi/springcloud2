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
        return restTemplate.getForObject("http://service-a/hi?id="+id, String.class);
    }
}
