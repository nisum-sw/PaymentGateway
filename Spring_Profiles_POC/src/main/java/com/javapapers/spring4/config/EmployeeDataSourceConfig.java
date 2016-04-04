package com.javapapers.spring4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.javapapers.spring4.util.DataSource;
import com.javapapers.spring4.util.DevDatabaseUtil;
import com.javapapers.spring4.util.ProductionDatabaseUtil;

@Configuration
public class EmployeeDataSourceConfig{
	  
	  @Bean(name="dataSource")
	  @Profile("dev")
	  public DataSource getDevDataSource() {
		  return new DevDatabaseUtil();
	  }	  
	  
	  @Bean(name="dataSource")
	  @Profile("prod")
	  public DataSource getProdDataSource() {
		  return new ProductionDatabaseUtil();
	  }	 
}
