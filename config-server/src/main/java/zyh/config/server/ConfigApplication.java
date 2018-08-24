package zyh.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
//开启配置服务
@EnableConfigServer  
public class ConfigApplication {
    public static void main(String[] args){
        System.out.println( "config Hello World!" );
        SpringApplication.run(ConfigApplication.class, args);
    }
}
