package com.JU.Thymeleaf_CRUD_Real_Time_Project.dao;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.JU.Thymeleaf_CRUD_Real_Time_Project.entity.Employee;


@Repository // add '@Repository' annotaion when a repository interface extending to JpaReposiroty interface
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// by extending to the 'JpaRepository' interface it provide some methods CRUD methods as well as other methods
	// to interact with our database
	
	//				    (1)		 (2)
	// JpaRepository<Employee, Integer>
	
	// JpaRepository is generic type, where you need to insert the following things in order :
	
	// (1) you provide the entity type which is the class that maps to the table and it annotated with 
	// '@Entity' annotation
	
	// (2) you provide the  primary key, in our case the Employee table our id type is number so is int. 
	// 		However you can't provide non-primitve type so you need to use it wrapper class int --> Integer
	
	
	
	// These are some of the methods that JpaRepository provide us that we will use it:
	
	// -	findAll() -  this will get all the records
	
	// -	findByID(..) - this will get single record based on an id
	
	// -	save(..)   - this will add or update based on the user id. 
	//				  Let say the user id is 0 means it will add record 
	// 				  in the table and if it is not equal to 0 then 
	//				  instead it will update
	
	// -	deleteById(..) - this will delete single record based on an id
	
	// Here are the other methods provided by repository : https://www.amitph.com/spring-data-jpa-query-methods/ 

	
	// custom queries
	@Query(value ="SELECT * FROM Employee where email = :theEmployeeEmail",nativeQuery= true)
	@Modifying
	@Transactional
	public List<Employee> getEmployeeEmail(@Param("theEmployeeEmail")String employeeEmail) ;
	
	// when writing custom queryes you the classnamd and field name from the class not actual table name and column name
	
	// add a method to sort by last name
	
	public List<Employee> findAllByOrderByLastNameAsc(); // Spring Data JPA will parse the method name. It looks for a
														// specific format and pattern creates appropriate query..
														// behind the scenes
	
	

}
