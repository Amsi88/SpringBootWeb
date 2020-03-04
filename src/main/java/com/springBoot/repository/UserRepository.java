package com.springBoot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springBoot.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
    List<Users> findByUsId(long userId);
    List<Users> findAll();
    Users findByUsLogin(String usLogin);
}