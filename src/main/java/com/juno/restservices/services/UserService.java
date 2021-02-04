package com.juno.restservices.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.juno.restservices.entities.User;
import com.juno.restservices.exceptions.UserExistsException;
import com.juno.restservices.exceptions.UserNotFoundException;
import com.juno.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepository.findUserByUserName(user.getUserName());
		if (existingUser != null) {
			throw new UserExistsException("User Already Exists in Respository");
		}
		return userRepository.save(user);
	}

	public Optional<User> getUserByID(long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in UserRepository");
		}
		return user;
	}

	public User updateUserByID(long id, User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in UserRepository, provide the correct userId");
		}
		user.setId(id);
		return userRepository.save(user);
	}

	public void deleteUserByID(long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User not found in UserRepository, provide the correct userId");
		}
		userRepository.deleteById(id);
	}

	public User getUserByUserName(String userName) {
		return userRepository.findUserByUserName(userName);
	}
}
