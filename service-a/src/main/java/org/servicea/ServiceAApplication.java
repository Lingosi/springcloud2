package org.servicea;

import org.servicea.config.RestConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@Import({
    RestConfig.class
})
public class ServiceAApplication {
	public static void main(String[] args) {
        SpringApplication.run(ServiceAApplication.class, args);
    }

}
