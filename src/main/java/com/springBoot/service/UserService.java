package com.springBoot.service;

import java.util.List;

import com.springBoot.dto.UsersDto;
import com.springBoot.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    List<UsersDto> getUsersById(long userId);
    List<UsersDto> getAllUsers();
    void save(Users user);
    Users findByUsLogin(String usLogin);
}
