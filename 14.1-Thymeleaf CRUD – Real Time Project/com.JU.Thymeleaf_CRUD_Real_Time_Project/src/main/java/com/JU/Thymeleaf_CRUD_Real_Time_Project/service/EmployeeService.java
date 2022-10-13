package com.JU.Thymeleaf_CRUD_Real_Time_Project.service;

import java.util.List; 

import com.JU.Thymeleaf_CRUD_Real_Time_Project.entity.Employee;



public interface EmployeeService {

public List<Employee>findAll();
	
public Employee findEmployeeById(int employeeId);

public void addEmployee(Employee employee);

public List<Employee> getEmployeeEmail(String employeeEmail);

public void updateEmployee(Employee employee);

public void deleteEmployeeById(int employeeId);
	


}
