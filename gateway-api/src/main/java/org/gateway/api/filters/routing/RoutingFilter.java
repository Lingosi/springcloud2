package org.gateway.api.filters.routing;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

@Component
public class RoutingFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() {
		System.out.println("routingfilter........");
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "route";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
