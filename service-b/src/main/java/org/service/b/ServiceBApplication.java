package org.service.b;

import org.commons.config.EnableCache;
import org.commons.config.EnableRest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableRest
@EnableCache
@EnableSwagger2
public class ServiceBApplication {
    public static void main(String[] args){
        System.out.println( "Hello World!" );
        SpringApplication.run(ServiceBApplication.class, args);
    }
}
