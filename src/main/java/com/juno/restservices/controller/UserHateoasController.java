package com.juno.restservices.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.juno.restservices.entities.Order;
import com.juno.restservices.entities.User;
import com.juno.restservices.exceptions.UserNotFoundException;
import com.juno.restservices.repositories.UserRepository;
import com.juno.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		List<User> listUsers = userService.getAllUsers();
		CollectionModel<User> collectionModel=null;
		Link relLink=null;
		for (User user : listUsers) {
			// selfLink
			Link link = WebMvcLinkBuilder.linkTo(this.getClass()).slash(user.getId()).withSelfRel();
			user.add(link);
			
			//RelationLinks
			CollectionModel<Order> orderCollectionModel=WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(user.getId());
			relLink=WebMvcLinkBuilder.linkTo(orderCollectionModel).withRel("all-orders");
			user.add(relLink);
		}
		Link allUserLink=WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		collectionModel=CollectionModel.of(listUsers);
		collectionModel.add(allUserLink);
		return collectionModel;
	}

	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> optionalUser = userService.getUserByID(id);
			User user = optionalUser.get();
			Long userId = user.getId();
			EntityModel<User> entityUser =EntityModel.of(user);
			Link link = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			entityUser.add(link);
			return entityUser;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
