package com.springBoot.controller;


import com.springBoot.dto.UsersDto;
import com.springBoot.mapper.UserMapper;
import com.springBoot.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods= {RequestMethod.GET,RequestMethod.POST})
public class BaseRestController {

	@Autowired
    UserService userService;

	UserMapper mapper = Mappers.getMapper(UserMapper.class);

	@RequestMapping(value = "/usersListJson", method = RequestMethod.GET, produces = "application/json")
	public List<UsersDto> firstPage() {
		return userService.getAllUsers();
	}

	@GetMapping("/usersList")
	public List<UsersDto> getUsersList() {
		return userService.getAllUsers();
	}

	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public UsersDto validateLogin() {
		return new UsersDto();
	}

//	public UsersDto validateLogin(@RequestBody String user) {
//		return mapper.usersToUsersDto(userService.findByUsLogin(user));
//	}

}
