package org.admin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.config.EnableAdminServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class AdminServerApplication {
    public static void main(String[] args){
        System.out.println( "Hello World!" );
        SpringApplication.run(AdminServerApplication.class, args);
    }
}
