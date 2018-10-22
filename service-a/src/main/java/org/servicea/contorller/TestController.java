package org.servicea.contorller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpStatus;
import org.servicea.exception.BusinessException;
import org.servicea.model.Person;
import org.servicea.publisher.PublishClient;
import org.servicea.service.IPersonService;
import org.servicea.util.MyPage;
import org.servicea.util.Response;
import org.servicea.util.Result;
import org.servicea.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sa")
@Api("swaggerDemoController相关的api")
public class TestController {
	/*@Value("${spring.application.name}")
    private String name;
    @Value("${server.port}")
    private String port;*/
    
    @Value("${env.name}")
    private String testName;
    @Value("${env.password}")
    private String password;
    
    @Autowired
    IPersonService personService;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private PublishClient pushClient;

    @RequestMapping(value="/hi")
    @ResponseBody
    public String hi(@RequestParam String id){
        //return "hi, " + id + ", " + name + ":" + port;
    	//return "hi, " + id + ", " + testName + " " + password + ":" + port;
    	return "123";
    }
    
    @GetMapping(value="/redis")
    @ResponseBody
    public String testRedis(){
    	//pushClient.send("hello............");
    	//pushClient.send("no hehehe ****************");
    	redisTemplate.opsForValue().increment("sa:redis", 1L);
    	redisTemplate.opsForValue().set("sa:redis:string", "你好");
    	return "redis";
    }
    
    @ApiOperation(value="更新信息", notes="根据url的id来指定更新用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "name", value = "用户名称", required = false, dataType = "String")
	})
    @GetMapping(value="/test1")
    @ResponseBody
    public String test1(Integer id, String name){
    	return "test1";
    }
    
    @GetMapping(value="/test2")
    @ResponseBody
    public String test2(){
    	throw new BusinessException("我要故意抛个异常");
    }
    
    @GetMapping(value="/test3")
    @ResponseBody
    public String test3(){
    	List<Person> lsPersons = new ArrayList<Person>();
    	Integer maxId = personService.getMaxId();
    	for(int i = 1;i<4;i++){
    		maxId++;
    		Person per = new Person();
    		per.setId(maxId);
    		per.setName("Test" + maxId);
    		Date tmpNow = new Date();
    		per.setBirthday(DateUtils.addDays(tmpNow, maxId * -1));
    		lsPersons.add(per);
    	}
    	personService.insertPersons(lsPersons);
    	return "test3";
    }
    
    @GetMapping(value="/test4")
	public Page test4(@RequestParam(defaultValue="6") Integer pageSize, @RequestParam(defaultValue="1") Integer pageNumber){
		Page<Person> page = new Page<Person>(pageNumber, pageSize);
		personService.getAllPerson2(page);
		System.out.println("有结果1了" + page);
		return page;
	}
    
    @PostMapping(value="/test5")
    public Result test5(@RequestParam(defaultValue="6") Integer pageSize, @RequestParam(defaultValue="1") Integer pageNumber, Person per){
    	Page<Person> page = new Page<Person>(pageNumber, pageSize);
    	Person person = personService.getPerson(page, per);
    	return ResultUtil.SUCCESS(person);
    }
    
    @PostMapping(value="/test6")
    public Page test6(@RequestParam(defaultValue="6") Integer pageSize, @RequestParam(defaultValue="1") Integer pageNumber, Person per){
    	System.out.println("当前页：" + pageNumber + "，每页：" + pageSize);
    	Page<Person> page = new Page<Person>(pageNumber, pageSize);
    	System.out.println("每页：" + page.getPages());
    	personService.getAllPerson3(page, per);
    	return page;
    }
    
    @PostMapping(value="/test7")
    public Response test7(@RequestParam(defaultValue="6") Integer pageSize, @RequestParam(defaultValue="1") Integer pageNumber, Person per){
    	System.out.println("当前页：" + pageNumber + "，每页：" + pageSize);
    	Page<Person> page = new Page<Person>(pageNumber, pageSize);
    	System.out.println("每页：" + page.getPages());
    	personService.getAllPerson3(page, per);
    	int offset = pageSize*pageNumber;
    	MyPage myPage = new MyPage<>(offset, page.getSize(), page.getTotal(), page.getRecords());
    	return Response.success(page);
    }
}
