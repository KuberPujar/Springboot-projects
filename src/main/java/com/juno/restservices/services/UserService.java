package com.juno.restservices.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.juno.restservices.entities.User;
import com.juno.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> getUserByID(long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}

	public User updateUserByID(long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}

	public void deleteUserByID(long id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	public User getUserByUserName(String userName) {
		return userRepository.findUserByUserName(userName);
	}
}
