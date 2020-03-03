package com.springBoot.controller;

import com.springBoot.dto.UsersDto;
import com.springBoot.mapper.UserMapper;
import com.springBoot.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class BaseController implements WebMvcConfigurer {

	private static final String US_LOGIN = "usLogin";

	private static final String BUNDLE_VALIDATE_LOGIN_NAME_EXISTS = "validate.loginName.exists";

	private static final String BUNDLE_MESSAGES = "messages";

	private static final String SAME_USER = "same.user";

	@Autowired
    UserService userService;

	UserMapper mapper = Mappers.getMapper(UserMapper.class);

	@GetMapping("/indexMenu")
	public String userFormCreate(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		if (session != null) {

			String usLogin = "";
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				usLogin = ((UserDetails) principal).getUsername();
			} else {
				usLogin = principal.toString();
			}

			session.setAttribute("userDto", mapper.usersToUsersDto(userService.findByUsLogin(usLogin)));
		}
		return "indexMenu";
	}
}
