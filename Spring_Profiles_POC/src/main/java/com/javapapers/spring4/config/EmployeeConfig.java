package com.javapapers.spring4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.javapapers.spring4.dao.EmployeeDAO;
import com.javapapers.spring4.service.EmployeeService;
import com.javapapers.spring4.util.DataSource;

@Configuration
public class EmployeeConfig{
	  @Autowired
	  private DataSource dataSource;
	  
	  @Bean
	  public EmployeeService employeeService() {
		  return new EmployeeService(employeeDAO());
	  }
	  
	  @Bean
	  public EmployeeDAO employeeDAO() {
		  return new EmployeeDAO(dataSource);
	  }
}
