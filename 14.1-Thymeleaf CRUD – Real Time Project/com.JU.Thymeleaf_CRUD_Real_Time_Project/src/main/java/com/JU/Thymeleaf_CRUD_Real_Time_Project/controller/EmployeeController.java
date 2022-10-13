package com.JU.Thymeleaf_CRUD_Real_Time_Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.JU.Thymeleaf_CRUD_Real_Time_Project.entity.Employee;
import com.JU.Thymeleaf_CRUD_Real_Time_Project.service.EmployeeService;

@Controller // to let spring know this is controller class
@RequestMapping("/employees")
public class EmployeeController {
	
	// need to inject the  EmployeeDAO in this controller
		//	@Autowired
			private EmployeeService employeeService; 
			
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) { // construtor injection
		
		
		this.employeeService = employeeService; /// This injects the dependency. 'EmployeeService' is interface in this case Spring will look for 
		// concrete class that implements the interface and also the concrete class already annotated with
		//'@Repository' which is subclass that inherits from super class '@component'. Spring will scan for 
		// a component that implements EmployeeService interface.  Spring Boot will scan automatically component.
		
	}
	
	
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
	
		// get employees from the database
		List<Employee> theEmployees = this.employeeService.findAll();
		
		// add to the Spring  model
		//							(1) 		  (2)
		theModel.addAttribute("EmployeeList", theEmployees);
		// (1) you pass the attribute name and can be anything and this attribute name we need refer in the 
				// view template to get result of the object
				
		// (2) you pass the actual object - our Thymeleaf template will access this data using the attribute
		//		name.
				
		
		return "List_Of_Employees"; // directingg to this html page which is placed under src/main/resources/template
	}
	
	// getmapping for the add form for new employee use the same name as you set href link in the view page
	@GetMapping("/ShowEmployeeForm")
	public String ShowFormForAdd(Model theModel) {

		
		// In our Spring Controller, before you show your form,  you must add a model attribute
		// This is an object that will hold the form data for the data binding
		
		
		// create model attribute to bind form data
		Employee employee = new Employee();
		
		theModel.addAttribute("NewEmployee", employee); // this reference name that we're going to use in
		// the view template page. In other words, Our Thymeleaf template will access this data for binding
		// form data
		
		return "showFormForAddEmployee"; // directingg to this html page which is placed under src/main/resources/template
	}
	
	
	// passing those user inputs for ADD new employee
	// putmapping to retrive those user data that send to the URL map
		@PostMapping("/addEmployee")
		public String addEmployee(@ModelAttribute("NewEmployee") Employee newEmployee) {
			
			
			if(this.employeeService.getEmployeeEmail(newEmployee.getEmail()).size() !=0) {
				
				
				// use a redirect to prevent duplicate submissons
				return "redirect:/employees/AddEmployeeForm"; // this is to help duplicate submissons just in case the user hits reload
			}
			
			// Save the Employee
			this.employeeService.addEmployee(newEmployee);
			
			
			// use a redirect to prevent duplicate submissons
			return "redirect:/employees/list"; // this is to help duplicate submissons just in case the user hits reload
			
		}
		
		// getmapping for the update form for existing employee employee use the same name as you set href link in the view page
		@GetMapping("/UpdateEmployeeForm")
		public String ShowFormForUpdate(@RequestParam("employeeId") int employeeId,Model theModel) {

			// @Request param is that parameter actually passed over by the link that we created in the HTML page
			
			// In our Spring Controller, before you show your form,  you must add a model attribute
			// This is an object that will hold the form data for the data binding
			
			// get the employee from the service
			Employee updateEmployee = this.employeeService.findEmployeeById(employeeId);
			
			// set employee as a model attribute to pre-populate the form	
			theModel.addAttribute("updateEmployee", updateEmployee); // this reference name that we're going to use in
			// the view template page. In other words, Our Thymeleaf template will access this data for binding
			// form data
			
			// send over to our form
			return "showFormForUpdateEmployee"; // directingg to this html page which is placed under src/main/resources/template
		}
		
		// passing those user inputs for UPDATE
		// putmapping to retrive those user data that send to the URL map
			@PostMapping("/updateEmployee")
			public String updateEmployee(@ModelAttribute("updateEmployee") Employee updateEmployee) {
				
				
				if(this.employeeService.getEmployeeEmail(updateEmployee.getEmail()).size() !=0) {
					
					
					// use a redirect to prevent duplicate submissons
					return "redirect:/employees/list"; // this is to help duplicate submissons just in case the user hits reload
				}
				
				// update the Employee
				this.employeeService.updateEmployee(updateEmployee);
				
				// use a redirect to prevent duplicate submissons
				return "redirect:/employees/list"; // this is to help duplicate submissons just in case the user hits reload
				
			}
			
			// getmapping for the delete an existing employee employee use the same name as you set href link in the view page
			@GetMapping("/DeleteEmployee")
			public String deleteEmployee(@RequestParam("employeeId") int employeeId) {

				// To delete an employee you need to know the id
				
				 this.employeeService.deleteEmployeeById(employeeId);
				
				
				// use a redirect to prevent duplicate submissons
				return "redirect:/employees/list"; // this is to help duplicate submissons just in case the user hits reload
				
			}
	
	
}
