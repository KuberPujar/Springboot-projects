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
import com.juno.restservices.dto.UserMmDTO;
import com.juno.restservices.entities.User;
import com.juno.restservices.exceptions.UserNotFoundException;
import com.juno.restservices.services.UserService;

@RestController
@RequestMapping(value = "/modelmapper/users")
public class UserModelMapperController {
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/{id}")
	public UserMmDTO getUserById(@PathVariable("id") @Min(1) long id) {
		try {
			Optional<User> optionalUser = userService.getUserByID(id);
			User user = optionalUser.get();
			UserMmDTO userDto = modelMapper.map(user, UserMmDTO.class);
			return userDto;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
