package com.stacksimplify.restservices.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.entities.Employee;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.entities.Views;
import com.stacksimplify.restservices.entities.Views2;
import com.stacksimplify.restservices.exceptions.EmployeeNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.EmployeeService;

import jakarta.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(value="/jsonview/employees")
public class EmployeeJsonViewController {
	
	//Autowired the UserService
			@Autowired
			private EmployeeService employeeService;
			//getUserById - External
			@JsonView(Views2.Normal.class)
			@GetMapping("/normal/{id}")
			public Optional<Employee> getEmployeeById(@PathVariable("id") @Min(1) Long id){
				try {
					return employeeService.getEmployeeById(id);
				} catch (EmployeeNotFoundException ex) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
				} 	
			}
			
			//getUserById - Internal
			@GetMapping("/manager/{id}")
			@JsonView(Views2.Manager.class)
			public Optional<Employee> getEmployeeById2(@PathVariable("id") @Min(1) Long id){
				try {
					return employeeService.getEmployeeById(id);
				} catch (EmployeeNotFoundException ex) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
				} 	
			}
			
			//getUserById - Internal
			@GetMapping("/hr/{id}")
			@JsonView(Views2.Hr.class)
			public Optional<Employee> getEmployeeById3(@PathVariable("id") @Min(1) Long id){
				try {
					return employeeService.getEmployeeById(id);
				} catch (EmployeeNotFoundException ex) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
				} 	
			}


}
