package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Employee;
import com.stacksimplify.restservices.exceptions.EmployeeExistException;
import com.stacksimplify.restservices.exceptions.EmployeeNotFoundException;
import com.stacksimplify.restservices.repositories.EmployeeRepository;

//Debo cambiar todo por Employee

@Service
public class EmployeeService {
	//Autowired the EmployeeRepository
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//Create getAllEmployee Method - OJO que aqui en el curso refieren al findAll de JPA
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	//Create employee Method	
	public Employee createEmployee(Employee employee)  throws EmployeeExistException{
		//if employee exist using empname
		Employee existingEmp= employeeRepository.findByEmpname(employee.getEmpname());
		//if not exists throw UserExistException
		if(existingEmp!=null) {
			throw new EmployeeExistException("Employee already exist in repository");
		}
		 
		return employeeRepository.save(employee);
	}
	
	//getUserById	
	public Optional<Employee> getEmployeeById(Long id) throws EmployeeNotFoundException {
		Optional<Employee> employee=employeeRepository.findById(id);
		
		if(!employee.isPresent())
		{
			throw new EmployeeNotFoundException("Employee not found in employee Repository");
		}
		
		return employee;
	}
	
	//Update User By Id	
	public Employee updateEmployeeById(Long id,Employee employee) throws EmployeeNotFoundException {
		Optional<Employee> optionalEmployee=employeeRepository.findById(id);
		
		if(!optionalEmployee.isPresent())
		{
			throw new EmployeeNotFoundException("Employee not found in employee Repository, provide the correct employee id");
		}
		
		employee.setEmpid(id);
		return employeeRepository.save(employee);		
	}
	
	//DeleteemployeeById
	public void deleteEmployeeById(Long id) {
		
		Optional<Employee> optionalEmployee=employeeRepository.findById(id);
		
		if(!optionalEmployee.isPresent())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Employee not found in employee Repository, provide the correct employee id");
		}
		employeeRepository.deleteById(id);
	}
	


}
