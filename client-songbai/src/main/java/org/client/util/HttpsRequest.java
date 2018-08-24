package org.client.util;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpParamsNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 项目描述:ats-task-service
 * 类  描  述:封装HTTP工具类,用于提供HTTP POST请求
 * 创  建  人:Abbey(彭磊)
 * 创建时间:2016-9-19
 */
public class HttpsRequest{
	protected Logger log = org.slf4j.LoggerFactory.getLogger(HttpsRequest.class);

    //表示请求器是否已经做了初始化工作
    private boolean hasInit = false;

    //连接超时时间，默认10秒
    private int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private int connectTimeout = 30000;

    //请求器的配置
    private RequestConfig requestConfig;

    //HTTP请求器
    private CloseableHttpClient httpClient;

    public HttpsRequest() {
        init();
    }
    
    public HttpsRequest(Integer connTimeOut, Integer socketTimeOut){
    	init();
    	this.socketTimeout = socketTimeOut;
    	this.connectTimeout = connTimeOut;
        resetRequestConfig();
    }

    private void init(){
        //httpClient = HttpClients.createDefault();
    	
        HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler(){  
            @Override  
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context){  
                if (executionCount >= 1){  
                    // Do not retry if over max retry count  
                    return false;  
                }  
                if (exception instanceof java.io.InterruptedIOException){  
                    // Timeout  
                    return false;  
                }  
                if (exception instanceof UnknownHostException){  
                    // Unknown host  
                    return false;  
                }  
                if (exception instanceof ConnectException){  
                    // Connection refused  
                    return false;  
                }  
                if (exception instanceof SSLException){  
                    // SSL handshake exception  
                    return false;  
                }  
                HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);  
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);  
                if (idempotent){  
                    // Retry if the request is considered idempotent  
                    return true;  
                }  
                return false;  
            }
 
          
        }; 
        httpClient = HttpClients.custom().setRetryHandler(myRetryHandler).build();
        //根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        hasInit = true;
    }

    /**
     * 提供通过接口发送HTTP请求到指定的URL
     * @param url HTTP请求地址
     * @param postDataXML 需要发送的字符串
     * @return
     */
    public String sendPost(String url, String postDataXML){

        if (!hasInit) {
            init();
        }

        String result = null;

        HttpPost httpPost = new HttpPost(url);

        log.info("API，POST过去的数据是：");
        log.info(postDataXML);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(requestConfig);
        log.info("executing request" + httpPost.getRequestLine());

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ConnectionPoolTimeoutException e) {
            log.error("http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
            log.error("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
            log.error("http get throw SocketTimeoutException");

        } catch (Exception e) {
            log.error("http get throw Exception");

        } finally {
            httpPost.abort();
        }

        return result;
    }
    
    public String sendPost(String url, Map<String, String> header, Map<String, String> parameters, Map<String, String> cookies, String charset){

        if (!hasInit) {
            init();
        }

        String result = null;

        HttpPost httpPost = new HttpPost(url);

        //设置请求器的配置
        httpPost.setConfig(requestConfig);
        log.info("executing request" + httpPost.getRequestLine());

        try {
        	if(null != parameters) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (String name : parameters.keySet()) {
                    nvps.add(new BasicNameValuePair(name, parameters.get(name)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            }
        	Iterator it = header.keySet().iterator();
	        while(it.hasNext()){
	        	String tmpKey = it.next().toString();
	        	String tmpValue = header.get(tmpKey);
	        	if(!StringUtils.isEmpty(tmpKey) && !StringUtils.isEmpty(tmpValue)){
	        		httpPost.addHeader(tmpKey, tmpValue);
	        	}
	        }
	        StringBuilder strCookie = new StringBuilder();
	        for (String key :cookies.keySet()){  
	        	strCookie.append(key).append("=").append(cookies.get(key)).append(";");
	        }
	        httpPost.addHeader(new BasicHeader("Cookie", strCookie.toString()));
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ConnectionPoolTimeoutException e) {
            log.error("http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
            log.error("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
            log.error("http get throw SocketTimeoutException");

        } catch (Exception e) {
            log.error("http get throw Exception");

        } finally {
            httpPost.abort();
        }

        return result;
    }
    
    public String sendGet(String url, Map<String, String> header, Map<String, String> parameters, Map<String, String> cookies, String charset){
    	String result = null;
    	try{
	    	if (!hasInit) {
	            init();
	        }
	        
	        List<NameValuePair> pairs = new ArrayList<NameValuePair>(parameters.size());  
	        for (String key :parameters.keySet()){  
	            pairs.add(new BasicNameValuePair(key, parameters.get(key)));  
	        }  
	        url +="?"+EntityUtils.toString(new UrlEncodedFormEntity(pairs), charset); 
	        HttpGet httpGet = new HttpGet(url);
	        Iterator it = header.keySet().iterator();
	        while(it.hasNext()){
	        	String tmpKey = it.next().toString();
	        	String tmpValue = header.get(tmpKey);
	        	if(!StringUtils.isEmpty(tmpKey) && !StringUtils.isEmpty(tmpValue)){
	        		httpGet.addHeader(tmpKey, tmpValue);
	        	}
	        }
	        StringBuilder strCookie = new StringBuilder();
	        for (String key :cookies.keySet()){  
	        	strCookie.append(key).append("=").append(cookies.get(key)).append(";");
	        }
	        httpGet.addHeader(new BasicHeader("Cookie", strCookie.toString()));
	        HttpResponse response = httpClient.execute(httpGet);
	        HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, charset);
    	}catch(Exception ex){
    		log.error("Get请求发生异常：{}", ex);
    	}
        return result;
    }
    
    
    /**
     * 提供通过接口发送HTTP请求到指定的URL
     * @param url HTTP请求地址
     * @param postDataXML 需要发送的字符串
     * @return
     */
    public String sendJsonPost(HttpPost httpPost, String postDataXML){

        if (!hasInit) {
            init();
        }

        String result = null;

        log.info("API，POST过去的数据是：");
        log.info(postDataXML);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(requestConfig);

        log.info("executing request" + httpPost.getRequestLine());

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ConnectionPoolTimeoutException e) {
            log.error("http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
            log.error("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
            log.error("http get throw SocketTimeoutException");

        } catch (Exception e) {
            log.error("http get throw Exception");

        } finally {
            httpPost.abort();
        }

        return result;
    }

    /**
     * 设置连接超时时间
     *
     * @param socketTimeout 连接时长，默认10秒
     */
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        resetRequestConfig();
    }

    /**
     * 设置传输超时时间
     *
     * @param connectTimeout 传输时长，默认30秒
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        resetRequestConfig();
    }

    private void resetRequestConfig(){
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    }

    /**
     * 允许商户自己做更高级更复杂的请求器配置
     *
     * @param requestConfig 设置HttpsRequest的请求器配置
     */
    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }
}
