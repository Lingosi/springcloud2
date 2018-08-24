package org.gateway.api.filters.error;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

@Component
public class ErrorFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() {
		System.out.println("ErrorFilter..........");
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "error";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
