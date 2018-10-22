package org.service.b;

import org.commons.config.EnableCache;
import org.commons.config.EnableRest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableRest
@EnableCache
@EnableSwagger2
@EnableDiscoveryClient
public class ServiceBApplication {
    public static void main(String[] args){
        System.out.println( "Hello World!" );
        SpringApplication.run(ServiceBApplication.class, args);
    }
}
