package org.gateway.api.filters.post;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.gateway.api.dto.LogDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

@Component
public class PostFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() {
		try{
			System.out.println("PostFilter......");
			/*RequestContext ctx = RequestContext.getCurrentContext();  
	        */ 
	        RequestContext ctx = getCurrentContext();
	        HttpServletRequest request = ctx.getRequest();
	        InputStream stream = ctx.getResponseDataStream();
	        String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
	        String tmpUri = request.getRequestURI();
	        Object uuid = request.getAttribute("traceLog");
	        if(null != uuid){
	        	LogDTO log = (LogDTO)uuid;
	        	Long tmpNow = System.currentTimeMillis();
	        	Long tmpStart = log.getStartTime().getTime();
	        	System.out.println("====uri：" + log.getUri() + "，traceId：" + log.getTraceId() + "，耗时：" + (tmpNow - tmpStart));
	        }
	        System.out.println("uri：" + tmpUri + "，traceId：" + uuid + "，返回值：" + body);
	        ctx.setResponseBody(body);
		}catch(Exception ex){
			throw new RuntimeException();
		}
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
