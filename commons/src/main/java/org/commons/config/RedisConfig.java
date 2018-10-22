package org.commons.config;

import org.commons.util.RedisJsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import redis.clients.jedis.JedisPoolConfig;

@Configuration  
@EnableAutoConfiguration  
public class RedisConfig {
	private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	//获取springboot配置文件的值 (get的时候获取)  
    @Value("${spring.redis.hostName}")  
    private String host;  
  
    @Value("${spring.redis.password}")  
    private String password;  
  
  
    /** 
     * @Bean 和 @ConfigurationProperties 
     * 该功能在官方文档是没有提到的，我们可以把@ConfigurationProperties和@Bean和在一起使用。 
     * 举个例子，我们需要用@Bean配置一个Config对象，Config对象有a，b，c成员变量需要配置， 
     * 那么我们只要在yml或properties中定义了a=1,b=2,c=3， 
     * 然后通过@ConfigurationProperties就能把值注入进Config对象中 
     * @return 
     */  
    @Bean  
    @ConfigurationProperties(prefix = "spring.redis.pool")  
    public JedisPoolConfig getRedisConfig() {  
        JedisPoolConfig config = new JedisPoolConfig();  
        return config;  
    }  
  
    @Bean  
    @ConfigurationProperties(prefix = "spring.redis")  
    public JedisConnectionFactory getConnectionFactory() {  
        JedisConnectionFactory factory = new JedisConnectionFactory();  
        factory.setUsePool(true);  
        JedisPoolConfig config = getRedisConfig();  
        factory.setPoolConfig(config);  
        logger.info("JedisConnectionFactory bean init success.");  
        return factory;  
    }  
  
  
    @Bean  
    public RedisTemplate<?, ?> getRedisTemplate() {  
    	System.out.println("获取template============================");
    	System.out.println("*************redis：" + host + "，密码：" + password + "*************");
        JedisConnectionFactory factory = getConnectionFactory();  
        logger.info(this.host+","+factory.getHostName()+","+factory.getDatabase());  
        logger.info(this.password+","+factory.getPassword());  
        logger.info(factory.getPoolConfig().getMaxIdle() + "");
        //RedisTemplate<?, ?> template = new StringRedisTemplate(getConnectionFactory()); //这样就只能操作字符串了 
        
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        
        template.setKeySerializer(new StringRedisSerializer());
        template.setDefaultSerializer(new RedisJsonSerializer<Object>());
        
        template.afterPropertiesSet();
        return template;  
    }  
}
