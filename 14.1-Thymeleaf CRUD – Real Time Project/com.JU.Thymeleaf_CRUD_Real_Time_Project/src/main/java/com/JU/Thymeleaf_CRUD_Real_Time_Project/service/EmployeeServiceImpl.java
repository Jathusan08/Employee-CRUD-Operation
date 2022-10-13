package com.JU.Thymeleaf_CRUD_Real_Time_Project.service;

import java.util.List;  
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JU.Thymeleaf_CRUD_Real_Time_Project.entity.Employee;

import com.JU.Thymeleaf_CRUD_Real_Time_Project.dao.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
 
	private EmployeeRepository employeeRepository;
	
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) { // constructor injection
		
		this.employeeRepository = employeeRepository;
		
	}


	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return this.employeeRepository.findAll();
		
		//this.employeeRepository.findAllByOrderByLastNameAsc();
	}


	@Override
	public Employee findEmployeeById(int employeeId) {
	
		Optional<Employee> result = employeeRepository.findById(employeeId);
		
		// OPtional is different pattern instead of having to check for nulls you can see if a given value is
		// present
		
		Employee theEmployee = null;
		
		if (result.isPresent()) { // if value is present we can retrieve that given value
			theEmployee = result.get();
		}
		else {
			
			// we didn't find the employee
						throw new RuntimeException("Did not find employee id - " + employeeId);
			// we didn't find the employee
			//return theEmployee;
		}
		
		return theEmployee;
		
	}


	@Override
	public void addEmployee(Employee employee) {
		
		 this.employeeRepository.save(employee);
		
	}


	@Override
	public List<Employee> getEmployeeEmail(String employeeEmail) {
		// TODO Auto-generated method stub
		return this.employeeRepository.getEmployeeEmail(employeeEmail);
	}


	@Override
	public void updateEmployee(Employee employee) {
	
		 this.employeeRepository.save(employee);
		
	}


	@Override
	public void deleteEmployeeById(int employeeId) {
		 this.employeeRepository.deleteById(employeeId);
		
	}



	
}
