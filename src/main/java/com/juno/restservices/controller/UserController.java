package com.juno.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.juno.restservices.entities.User;
import com.juno.restservices.exceptions.UserExistsException;
import com.juno.restservices.exceptions.UserNameNotFoundException;
import com.juno.restservices.exceptions.UserNotFoundException;
import com.juno.restservices.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "User management RESTFul service",value = "UserController",description = "Controller for usermanagement service")
@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Retrieve list of users")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@ApiOperation(value = "Creates a new user")
	@PostMapping
	public ResponseEntity<Void> createUser(@ApiParam("User informatiopn for new user o be created") @Valid @RequestBody  User user, UriComponentsBuilder uriBuilder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(uriBuilder.path("users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") long id) {
		try {
			return userService.getUserByID(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") @Min(1) long id, @RequestBody User user) {
		try {
			return userService.updateUserByID(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") long id) {
		userService.deleteUserByID(id);
	}

	@GetMapping("/byusername/{userName}")
	public User getUserByUserName(@PathVariable("userName") String userName) throws UserNameNotFoundException {
		User user= userService.getUserByUserName(userName);
		if(user==null)
		{
			throw new UserNameNotFoundException("Username:"+userName+" not found in user repository");
		}
		return user;
	}
}
