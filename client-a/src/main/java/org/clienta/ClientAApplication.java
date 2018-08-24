package org.clienta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
//这里需要指定restful接口的包路径，否则访问时报Whitelabel Error Page
@ComponentScan(basePackages = {"org.controller"})
public class ClientAApplication {
    public static void main(String[] args){
    	SpringApplication.run(ClientAApplication.class, args);
    }
    
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
