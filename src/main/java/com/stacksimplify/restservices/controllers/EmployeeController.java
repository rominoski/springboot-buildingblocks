package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.Employee;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.EmployeeExistException;
import com.stacksimplify.restservices.exceptions.EmployeeNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.EmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

//Controller
@RestController
@Validated
@RequestMapping(value="/employees")
public class EmployeeController {

	//Autowired the UserService
		@Autowired
		private EmployeeService employeeService;
		
		//Implement getAllUsers Method -
		@GetMapping
		public List<Employee> getAllEmployee(){
			return employeeService.getAllEmployees();
		}
		//Create User Method
		//@RequestBody Annotation
		//@PostMapping Annotation
		@PostMapping
		public ResponseEntity<Void> createEmployee(@Valid @RequestBody Employee employee, UriComponentsBuilder builder) {
			try {
			 employeeService.createEmployee(employee);
			 HttpHeaders headers= new HttpHeaders();
			 headers.setLocation(builder.path("/employees/{id}").buildAndExpand(employee.getEmpid()).toUri());
			 return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
			}catch(EmployeeExistException ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
			}
		}
		
		//getUserById
		@GetMapping("/{id}")
		public Optional<Employee> getEmployeeById(@PathVariable("id") @Min(1) Long id){
			try {
				return employeeService.getEmployeeById(id);
			} catch (EmployeeNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			} 	
		}
		
		//updateUserById
		@PutMapping("/{id}")
		public Employee updateEmployeeById(@PathVariable("id") Long id, @RequestBody Employee employee) {
			try {
				return employeeService.updateEmployeeById(id, employee);
			} catch (EmployeeNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
			}
		}
		
		//deleteUserById
		@DeleteMapping("/{id}")
		public void deleteUserById(@PathVariable("id") Long id) {
			employeeService.deleteEmployeeById(id);		
		} 
		
		
}
