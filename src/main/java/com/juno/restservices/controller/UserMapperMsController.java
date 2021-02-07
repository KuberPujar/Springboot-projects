package com.juno.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juno.restservices.dto.UserMsDto;
import com.juno.restservices.entities.User;
import com.juno.restservices.mapper.UserMapper;
import com.juno.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/mapstruct/users")
public class UserMapperMsController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@GetMapping
	public List<UserMsDto> userToMsUserDTOs() {
		return userMapper.userToMsDTOs(userRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UserMsDto getUserByIdMsDto(@PathVariable("id") Long id) {
		 Optional<User> optionalUser=userRepository.findById(id);
		 User user=optionalUser.get();
		 return userMapper.userTYoMsDTO(user);
	}
}
