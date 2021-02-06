package com.juno.restservices.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.juno.restservices.entities.Order;
import com.juno.restservices.entities.User;
import com.juno.restservices.exceptions.UserNotFoundException;
import com.juno.restservices.repositories.OrderRepository;
import com.juno.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class OrderHateoasController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{id}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		List<Order> allOrders=user.get().getOrders();
		for(Order order:allOrders)
		{
			Link selfLink=WebMvcLinkBuilder.linkTo(this.getClass()).slash(order.getOrderId()).withSelfRel();
			order.add(selfLink);
		}
		CollectionModel<Order> orderCollection=CollectionModel.of(allOrders);
		return orderCollection;
	}
}
