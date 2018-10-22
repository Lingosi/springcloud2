package org.gateway.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;



/**
 * 如果不加@primary，则会报错
 * Description:

Parameter 0 of constructor in springfox.documentation.swagger.web.ApiResourceController required a single bean, but 2 were found:
	- swaggerConfig: defined in file [D:\Develop\workspace\learning\springcloud2\gateway-api\target\classes\org\gateway\api\config\SwaggerConfig.class]
	- inMemorySwaggerResourcesProvider: defined in URL [jar:file:/D:/Develop/apache-maven-3.2.3/repository/io/springfox/springfox-swagger-common/2.6.1/springfox-swagger-common-2.6.1.jar!/springfox/documentation/swagger/web/InMemorySwaggerResourcesProvider.class]


Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed

 * @author Lingosi
 *
 */

@Component
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {
	private final RouteLocator routeLocator;
	
	public SwaggerConfig(RouteLocator routeLocator){
		this.routeLocator = routeLocator;
	}

	private SwaggerResource swaggerResource(String name, String location) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion("2.0");
		return swaggerResource;
	}
	
	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		List<Route> routes = routeLocator.getRoutes();
		for (Route route:routes) {
			System.out.println("id：" + route.getId());
			System.out.println("fullpath：" + route.getFullPath());
			System.out.println("location：" + route.getLocation());
			System.out.println("path：" + route.getPath());
			System.out.println("prefix：" + route.getPrefix());
		    resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs")));
		}
		return resources;
	}

}
