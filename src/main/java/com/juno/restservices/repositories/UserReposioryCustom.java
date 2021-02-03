package com.juno.restservices.repositories;

import com.juno.restservices.entities.User;

public interface UserReposioryCustom {
	User findUserByName(final String name);
}
