package org.gateway.api.filters.post;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

@Component
public class PostFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() {
		System.out.println("PostFilter......");
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
