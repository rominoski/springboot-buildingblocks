package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

import jakarta.validation.constraints.Min;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping(value="/users")
public class OrderController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	//Create Method get All Orders for a user

	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		Optional<User> userOptional = userRepository.findById(userid);
		
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");
		return userOptional.get().getOrders();
	}
	//Create Order
	@PostMapping("/{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");
		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);
		
	}
	//getOrderByOrderId
	@GetMapping("/{userid}/orders/{orderid}")
	public Optional<Order> getOrderById(@PathVariable Long userid,@PathVariable("orderid") @Min(1) Long orderid) throws OrderNotFoundException{
		log.info("Entrando en GetOrderById");
		Optional<User> userOptional = userRepository.findById(userid);
		log.info("Usuario Encontrado "+userOptional.get().getFisrtname());		
		if (!userOptional.isPresent())
			throw new OrderNotFoundException("User Not Found");
		List<Order> orders = userOptional.get().getOrders();
		Optional<Order> orderOptional = orderRepository.findById(orderid);
		log.info("Orden Encontrada "+orderOptional.get().getOrderid());
		if (!orderOptional.isPresent())
			throw new OrderNotFoundException("Order Not Found");
		//Order orderFound = (Order)orders.stream().filter(order->order.getOrderid().equals(orderid));
		//orderFound.getOrderid();
		return orderOptional;
	}
		
}
