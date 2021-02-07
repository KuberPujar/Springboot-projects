package com.juno.restservices.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.juno.restservices.dto.UserMsDto;
import com.juno.restservices.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mappings
	({
	@Mapping(source ="email",target = "emailAddress" ),
	@Mapping(source ="role",target = "roleName" )
	})
	UserMsDto userTYoMsDTO(User user);

	//@Mapping(target ="email",source = "emailAddress" )
	List<UserMsDto> userToMsDTOs(List<User> users);
}
