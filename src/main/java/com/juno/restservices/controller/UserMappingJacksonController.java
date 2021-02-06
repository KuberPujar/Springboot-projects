package com.juno.restservices.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.juno.restservices.entities.User;
import com.juno.restservices.exceptions.UserNotFoundException;
import com.juno.restservices.services.UserService;

@RestController
@RequestMapping(value = "/jacksonfilter/user")
@Validated
public class UserMappingJacksonController {
	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") long id) {
		try {
			Optional<User> optionalUser=userService.getUserByID(id);
			Set<String> filterFields=new HashSet<String>();
			filterFields.add("id");
			filterFields.add("userName");
			filterFields.add("ssn");
			filterFields.add("orders");
			/*
			 * Set<String> OrderFilterFields=new HashSet<String>();
			 * filterFields.add("orderDescription");
			 */
			FilterProvider filterProvider=new SimpleFilterProvider()
					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(filterFields));
				//	.addFilter("orderFilter", SimpleBeanPropertyFilter.filterOutAllExcept(OrderFilterFields));
			
			MappingJacksonValue mapper=new MappingJacksonValue(optionalUser.get());
			mapper.setFilters(filterProvider);
			return mapper;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
