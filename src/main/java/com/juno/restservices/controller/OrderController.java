package com.juno.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juno.restservices.entities.Order;
import com.juno.restservices.entities.User;
import com.juno.restservices.exceptions.OrderNotFoundException;
import com.juno.restservices.exceptions.UserNotFoundException;
import com.juno.restservices.repositories.OrderRepository;
import com.juno.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
@Validated
public class OrderController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{id}/orders")
	public List<Order> getAllOrders(@PathVariable Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		return user.get().getOrders();
	}

	@PostMapping("/{id}/orders")
	public Order createOrder(@PathVariable Long id, @Valid @RequestBody Order order) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user = optionalUser.get();
		order.setUser(user);
		return orderRepository.save(order);
	}

	@GetMapping("/{userId}/orders/{orderId}")
	public Order getOrderByOrderId(@PathVariable Long userId, @PathVariable Long orderId)
			throws UserNotFoundException, OrderNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User Not Found in User repository");
		}

		Optional<Order> optionalOrder = orderRepository.findById(orderId);

		if (!optionalOrder.isPresent()) {
			throw new OrderNotFoundException("Order Not Found in Order repository");
		}
		Order order = null;
		User user = optionalOrder.get().getUser();
		List<Order> orders = optionalUser.get().getOrders();
		for (Order o : orders) {
			if (o.getUser().getId() == user.getId()) {
				order = optionalOrder.get();
			}
			else
			{
				throw new OrderNotFoundException("Order Not Found in Order repository");
			}
		}
		return order;
	}
}
