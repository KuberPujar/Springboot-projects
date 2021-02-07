package com.juno.restservices.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.juno.restservices.dto.UserDtoV1;
import com.juno.restservices.dto.UserDtoV2;
import com.juno.restservices.entities.User;
import com.juno.restservices.exceptions.UserNotFoundException;
import com.juno.restservices.services.UserService;

@RestController
@RequestMapping(value = "/versioning/mediatype/users")
public class UserMediaTypeVersionController {
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{id}",produces =  "application/vnd.juno.app-v1+json")
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) long id) {
		try {
			Optional<User> optionalUser = userService.getUserByID(id);
			User user = optionalUser.get();
			UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
			return userDtoV1;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping(value = "/{id}",produces =  "application/vnd.juno.app-v2+json")
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) long id) {
		try {
			Optional<User> optionalUser = userService.getUserByID(id);
			User user = optionalUser.get();
			UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
			return userDtoV2;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}