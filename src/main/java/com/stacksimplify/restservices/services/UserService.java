package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

//Service
@Service
public class UserService {
	
	//Autowired the UserRepository
	@Autowired
	private UserRepository userRepository;
	
	//Create getAllUsers Method - OJO que aqui en el curso refieren al findAll de JPA
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//Create User Method	
	public User createUser(User user)  throws UserExistException{
		//if user exist using username
		User existingUser= userRepository.findByUsername(user.getUsername());
		//if not exists throw UserExistException
		if(existingUser!=null) {
			throw new UserExistException("User already exist in repository");
		}
		 
		return userRepository.save(user);
	}
	
	//getUserById	
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user=userRepository.findById(id);
		
		if(!user.isPresent())
		{
			throw new UserNotFoundException("User not found in user Repository");
		}
		
		return user;
	}
	
	//Update User By Id	
	public User updateUserById(Long id,User user) throws UserNotFoundException {
		Optional<User> optionalUser=userRepository.findById(id);
		
		if(!optionalUser.isPresent())
		{
			throw new UserNotFoundException("User not found in user Repository, provide the correct user id");
		}
		
		user.setUserid(id);
		return userRepository.save(user);		
	}
	
	//DeleteUserById
	public void deleteUserById(Long id) {
		
		Optional<User> optionalUser=userRepository.findById(id);
		
		if(!optionalUser.isPresent())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in user Repository, provide the correct user id");
		}
		userRepository.deleteById(id);
	}
	
	//getUserByUsername
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
