package com.juno.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juno.restservices.entities.User;

//Repository
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findUserByUserName(String userName);
}
