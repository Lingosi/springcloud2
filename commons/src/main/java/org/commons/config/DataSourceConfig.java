package org.commons.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


import com.alibaba.druid.pool.DruidDataSource;


@Configuration
public class DataSourceConfig {
	@Bean(name="dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource(){
		System.out.println("获取数据源=====================");
        return new DruidDataSource();
    } 

    // 配置事物管理器
    @Bean(name="transactionManager") 
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
}
