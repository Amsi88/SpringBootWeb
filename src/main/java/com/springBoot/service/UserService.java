package com.springBoot.service;

import java.util.List;

import com.springBoot.dto.UsersDto;
import com.springBoot.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public List<UsersDto> getUsersById(long userId);
    public List<UsersDto> getAllUsers();
    public Users save(Users user);
    public Users findByUsLogin(String usLogin);
}
