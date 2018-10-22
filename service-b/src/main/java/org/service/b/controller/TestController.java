package org.service.b.controller;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sb")
@Api("这是个实验品")
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	@Value("${spring.application.name}")
    private String name;
    @Value("${server.port}")
    private String port;
    
    @Value("${env.name}")
    private String testName;
    @Value("${env.password}")
    private String password;
    @Value("${test.test1}")
    private String test11;
    @Value("${test.test2}")
    private String test22;
    
    @Autowired
    RestTemplate restTemplate;
    
    @ResponseBody
    @GetMapping(value="/test1")
    @ApiOperation(value="查询数据", notes="根据用户的信息分页查询展示数据")
    public String test1(){
    	//这里的路径为对应被调用服务的：${spring.application.name}/${server.context-path}/..
    	ResponseEntity<String> resp = restTemplate.getForEntity("http://service-a/s-a/sa/hi?id=1", String.class);
    	if(HttpStatus.SC_OK == resp.getStatusCodeValue()){
    		System.out.println("成功");
    		String tmpResult = resp.getBody();
    		System.out.println("结果是：" + tmpResult);
    	}
    	return name + ":" + port + "返回名称：" + testName;
    }
    
    @ResponseBody
    @GetMapping(value="/test2")
    public String test2(){
    	logger.info("呵呵，我进来test2了");
    	System.out.println("test11的结果：" + test11);
    	System.out.println("test22的结果：" + test22);
    	logger.info("logstash默认的索引就是logstash-*");
    	logger.info("测试代码");
    	logger.info("需要测试吗");
    	logger.info("那就测吧");
    	logger.info("神马？");
    	logger.info("哦，知道了");
    	return "test2";
    }
    
    @GetMapping(value="/test3")
    public String test3(String name){
    	System.out.println("取到的客户端传值为：" + name);
    	return "welcome：" + name;
    }
}
