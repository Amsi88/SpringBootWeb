package com.springBoot.mapper;

import com.springBoot.dto.UsersDto;
import org.mapstruct.Mapper;

import com.springBoot.entity.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
 
    UsersDto usersToUsersDto(Users user);
    Users usersDtoTousers(UsersDto user);
}
