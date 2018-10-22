package org.gateway.api.fuse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
/**
 * 服务熔断
 * @author Lingosi
 *
 */
public class ZuulFuse implements ZuulFallbackProvider{

	@Override
	public ClientHttpResponse fallbackResponse() {
		
		return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }
 
            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }
 
            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }
 
            @Override
            public void close() {
 
            }
 
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("s-a不可用".getBytes());
            }
 
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = MediaType.APPLICATION_JSON_UTF8;
                headers.setContentType(mt);
                return headers;
            }
        };
	}

	@Override
	public String getRoute() {
		
		return "s-a";
	}

}
