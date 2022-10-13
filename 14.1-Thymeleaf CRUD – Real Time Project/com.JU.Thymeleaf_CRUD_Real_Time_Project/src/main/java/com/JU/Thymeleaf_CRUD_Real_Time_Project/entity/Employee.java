package com.JU.Thymeleaf_CRUD_Real_Time_Project.entity;

import javax.persistence.Column;   
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Here we map a Java class to a given database table,

@Entity // to tell spring this is entity class that is mapped to database
@Table(name="employee")  // using the same table name as we created in the database in order for this class to be mapped to
public class Employee {

	// define fields

	@Id // to tell this field is ID meaning primary key which is uniques as well can not contain null value
	@GeneratedValue(strategy=GenerationType.IDENTITY) // autoincrement
	@Column(name="employee_id") // to map the fields to the customer table columns and need to match exact column name in the table
	private int id;	
	
	
	@Column(name="first_name")// to map the fields to the customer table columns and need to match exact column name in the table
	private String firstName;
	
	@Column(name="last_name")// to map the fields to the customer table columns and need to match exact column name in the table
	private String lastName;
	
	@Column(name="email")// to map the fields to the customer table columns and need to match exact column name in the table
	private String email;
	
	// define constructors
	
	public Employee() { // no-arg constructor is required for Hibernate API
		
		
		
	}
	
	


	public Employee(int id, String firstName, String lastName, String email) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Employee(int id, String firstName, String lastName) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;

	}




	public Employee(String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// define getter/setter

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	// define toString

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
}
