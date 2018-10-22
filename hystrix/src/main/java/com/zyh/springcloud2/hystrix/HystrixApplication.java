package com.zyh.springcloud2.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@SpringBootApplication
@EnableTurbine
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableDiscoveryClient
@RestController
public class HystrixApplication {
    public static void main(String[] args ){
    	SpringApplication.run(HystrixApplication.class, args);
        System.out.println( "Hello hystrix!" );
    }
    
    //这部分如果不加的话，打开http://localhost:8817/hystrix/hystrix监控http://localhost:8817/hystrix/hystrix.stream会报错：Unable to connect to Command Metric Stream.
    /*@Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    } */ 
    
    @GetMapping("/test1")
    public String test1(){
    	return "hystrix";
    }
}
