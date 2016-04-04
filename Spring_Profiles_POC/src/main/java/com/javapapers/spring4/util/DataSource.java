package com.javapapers.spring4.util;

import java.util.List;
import com.javapapers.spring4.domain.Employee;

public interface DataSource {
	List<Employee> getEmployeeDetails();
}