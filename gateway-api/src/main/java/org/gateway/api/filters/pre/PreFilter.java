package org.gateway.api.filters.pre;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.gateway.api.dto.LogDTO;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PreFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() {
		System.out.println("pre................................................");
		RequestContext ctx = RequestContext.getCurrentContext();  
        HttpServletRequest request = ctx.getRequest(); 
        String tmpUri = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        LogDTO log = new LogDTO(uuid, tmpUri, new Date());
        request.setAttribute("traceLog", log);
        System.out.println("uri：" + tmpUri + "，traceId：" + uuid);
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
