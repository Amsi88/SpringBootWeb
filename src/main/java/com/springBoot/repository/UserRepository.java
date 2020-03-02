package com.springBoot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springBoot.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
    public List<Users> findByUsId(long userId);
    public List<Users> findAll();
    public Users findByUsLogin(String usLogin);
}