package org.client.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.time.DateUtils;
import org.client.util.HttpsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@RestController
public class TestController {
	private static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
    RestTemplate restTemplate;

    @RequestMapping(value="/hi")
    public String hi(@RequestParam String id){
    	System.out.println("hi..................");
    	//因为这里你不能直接访问地址，需要把地址改成你所调用的ur在eureka上注册的application.name。jobj2那里会报错
    	//可以正常取到结果
    	JSONObject jobj1 = restTemplate.getForEntity("http://exchange-admin:3001/admin/adminUser/activate.do?id="+id, JSONObject.class).getBody();
    	System.out.println("jobj1======================" + jobj1);
    	//使用restTemplate就不能用地址啥的，但是用httpclient就可以。这里会抛出异常：java.lang.IllegalStateException: No instances available for localhost
    	JSONObject jobj2 = restTemplate.getForObject("http://localhost:3001/admin/adminUser/activate.do?id="+id, JSONObject.class);
        return "12";
    }
    
    
    @RequestMapping(value="/otc")
    public String otc(){
    	System.out.println("otc..................");
        return restTemplate.getForObject("http://exchange-otc/account/balance", String.class);
    }
    
    @ResponseBody
    @RequestMapping(value="/h2")
    public String h2(){
    	System.out.println("h2..................");
    	return "h2";
    }
    
    @ResponseBody
    @RequestMapping(value="/h3")
    public String h3(){
    	System.out.println("h3..................");
    	try{
    		//这里可以正常得到返回结果
	    	HttpsRequest httpReq = new HttpsRequest();
	    	httpReq.setConnectTimeout(20000);
	    	httpReq.setSocketTimeout(60000);
	    	String tmpValue = httpReq.sendPost("http://localhost:3001/admin/adminUser/activate.do", "123");
	    	System.out.println("value:" + tmpValue);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return "h3";
    }
    
    @ResponseBody
    @RequestMapping(value="/connect")
    public String chat1(){
    	System.out.println("connect..................");
    	//因为这里你不能直接访问地址，需要把地址改成你所调用的ur在eureka上注册的application.name。jobj2那里会报错
    	//可以正常取到结果
    	JSONObject jobj1 = restTemplate.postForEntity("http://exchange-user:3002/user/chat/connect.do", null, JSONObject.class).getBody();
    	System.out.println("jobj1======================" + jobj1);
    	return "connect";
    }
    
    
    @ResponseBody
    @RequestMapping(value="/login")
    public String login(){
    	System.out.println("login..................");
    	//因为这里你不能直接访问地址，需要把地址改成你所调用的ur在eureka上注册的application.name。jobj2那里会报错
    	//可以正常取到结果
    	
    	//http://ex.esongbai.xyz/api/user/user/login.do
    	MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("deviceid", "12345678");
        /*requestEntity.add("platform", "3");
        requestEntity.add("imgCode", "fdqo");
        requestEntity.add("phone", "17682315892");*/
        HttpHeaders headers = new HttpHeaders();
        headers.add("token1", "eXZ4aHdleXZhbHJnZ3Vud2drbW5pbG1keXloMw==");
        headers.add("token2", "YzY5M2ZkMTgwMTcwODUyMDIwMGIwNjRlMTIxZGZjODI=");
        HttpEntity<String> requestHeaders = new HttpEntity<String>(null, headers);
        ResponseEntity<JSONObject> rss = restTemplate.exchange("http://exchange-user:3002/user/chat/connect.do", HttpMethod.POST, requestHeaders, JSONObject.class, requestEntity);
        JSONObject jobj1 = rss.getBody();
        //JSONObject jobj1 = restTemplate.postForEntity("http://ex.esongbai.xyz/api/user/user/login.do", requestEntity, JSONObject.class).getBody();
    	System.out.println("jobj1======================" + jobj1);
    	return "login";
    }
    
    @ResponseBody
    @RequestMapping(value="/send")
    public String chat2(){
    	System.out.println("hi..................");
    	//因为这里你不能直接访问地址，需要把地址改成你所调用的ur在eureka上注册的application.name。jobj2那里会报错
    	//可以正常取到结果
    	JSONObject jobj1 = restTemplate.postForEntity("http://exchange-user:3002/user/chat/send.do", null, JSONObject.class).getBody();
    	System.out.println("jobj1======================" + jobj1);
    	return "connect";
    }
    
    @ResponseBody
    @RequestMapping(value="/page")
    public String chat3(){
    	System.out.println("hi..................");
    	HttpsRequest httpReq = new HttpsRequest(20000, 60000);
    	Map<String, String> mapCookies = new HashMap<String, String>();
    	mapCookies.put("token1", "ZTR4dGRleGthYWVnYm9uZnprbGVpZndkeWhqMQ==");
    	mapCookies.put("token2", "MzM1MGQ1NTgwYzkxMDRhMzhkMGM0NWExNzNkN2VkZTg=");
    	Map<String, String> mapHeaders = new HashMap<String, String>();
    	
    	Map<String, Object> mapParas = new HashMap<String, Object>();
    	mapParas.put("deviceid", "1234567");
    	mapParas.put("startTime", (new Date()).getTime());
    	mapParas.put("pageSize", "10");
    	//String tmpValue = httpReq.sendPost("http://localhost:3002/user/chat/page.do", mapHeaders, mapParas, mapCookies, "UTF-8");
    	//System.out.println("value:" + tmpValue);
    	//因为这里你不能直接访问地址，需要把地址改成你所调用的ur在eureka上注册的application.name。jobj2那里会报错
    	
    	
    	//?code=60&startTime=" + (new Date()).getTime()
    	Date tmpEnd = new Date();
    	Date tmpStart = DateUtils.addHours(tmpEnd, -1);
    	//ResponseEntity<Object> o = restTemplate.getForEntity("http://exchange-quota:3006/quota/quota/{1}/k.do?code={2}&type={3}&limit={4}&startTime={5}&endTime={6}", Object.class, "btc_usdt", "btc_usdt", "60", 100, sdfTime.format(tmpStart), sdfTime.format(tmpEnd));
    	ResponseEntity<JSONArray> o = restTemplate.getForEntity("http://exchange-quota/quota/quota/{1}/k.do?code={2}&type={3}&limit={4}&startTime={5}&endTime={6}", JSONArray.class, "bfb_usdt", "bfb_usdt", "1", 100, sdfTime.format(tmpStart), sdfTime.format(tmpEnd));
    	if(o.getStatusCodeValue() == 200){
    		System.out.println("成功");
    		JSONArray tmpValue = o.getBody();
    		System.out.println("结果：" + tmpValue);
    		for(int i = 0;i < tmpValue.size();i++){
    			JSONObject ji = tmpValue.getJSONObject(i);
    			Double tmpDv = ji.getDouble("closePrice");
    			System.out.println("closePrice：" + tmpDv);
    		}
    		//JSONObject jo = JSONObject.parseObject(tmpValue.toString());
    		//JSONArray jarr = JSONArray.parseArray(tmpValue.toString());
    		
    		System.out.println("结果：" + tmpValue);
    	}
    	System.out.println("XXXXX----->" + o);
    	//MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        //requestEntity.add("deviceid", "12345678");
        ///*requestEntity.add("platform", "3");
        //requestEntity.add("imgCode", "fdqo");
        //requestEntity.add("phone", "17682315892");*/
        //HttpHeaders headers = new HttpHeaders();
        //headers.add("token1", "eXZ4aHdleXZhbHJnZ3Vud2drbW5pbG1keXloMw==");
        //headers.add("token2", "YzY5M2ZkMTgwMTcwODUyMDIwMGIwNjRlMTIxZGZjODI=");
        //HttpEntity<String> requestHeaders = new HttpEntity<String>(null, headers);
        //ResponseEntity<JSONObject> rss = restTemplate.exchange("http://exchange-user:3002/user/chat/page.do?deviceid=123456", HttpMethod.GET, requestHeaders, JSONObject.class, requestEntity);
        //JSONObject jobj1 = rss.getBody();
    	
    	
    	//System.out.println("jobj1======================" + jobj1);
    	return "page";
    }
    
    @ResponseBody
    @RequestMapping(value="/chat4")
    public String chat4(){
    	//可以正常取到结果
    	//因为这里你不能直接访问地址，需要把地址改成你所调用的uri在eureka上注册的application.name。jobj2那里会报错
    	//************************************post这样传参************************************
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    	MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
    	map.add("deviceid", "1234567");
    	map.add("startTime", (new Date()).getTime() + "");

    	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

    	JSONObject jobj1 = restTemplate.postForEntity("http://exchange-user:3002/user/chat/page.do", request, JSONObject.class).getBody();
    	
    	//************************************post这样传参完毕************************************
    	return "success";
    }
}
