package com.springBoot.controller;

import com.springBoot.dto.UsersDto;
import com.springBoot.entity.Users;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class UserController implements WebMvcConfigurer{

	private static final String US_LOGIN = "usLogin";

	private static final String BUNDLE_VALIDATE_LOGIN_NAME_EXISTS = "validate.loginName.exists";

	private static final String BUNDLE_MESSAGES = "messages";

	private static final String SAME_USER = "same.user";

	@Autowired
    UserService userService;

	UserMapper mapper = Mappers.getMapper(UserMapper.class);

	@GetMapping("/formUser")
	public String userForm(Model model) {
		model.addAttribute("userToEditDto", new UsersDto());
		return "editUserInfo";
	}

	@GetMapping("/formUserEdit")
	public String userFormForEdit(HttpServletRequest request, Model model) {
		UsersDto user = (UsersDto)request.getSession().getAttribute("userDto");
		model.addAttribute("userToEditDto", user);
		return "editUserInfo";
	}

	@PostMapping("/formUserCreate")
	public UsersDto create(@RequestBody UsersDto userDto) {
		List<UsersDto> listUser = userService.getAllUsers();
		// Validate same Login name
		for (UsersDto us : listUser) {
			if (us.getUsLogin().equals(userDto.getUsLogin())) {
				return userDto;
			}
		}

		// GetiD
		userDto.setUsId((long) (listUser.size() + 1));

		userService.save(mapper.usersDtoToUsers(userDto));
		System.out.println(userDto);
		return userDto;
	}

	@PostMapping("/formUser")
	public String userSubmit(@ModelAttribute("userDto") @Valid UsersDto userDto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "formUser";
		}

		List<UsersDto> listUser = userService.getAllUsers();
		// Validate same Login name
		for (UsersDto us : listUser) {
			if (us.getUsLogin().equals(userDto.getUsLogin())) {
				result.rejectValue(US_LOGIN, SAME_USER,
						ResourceBundle.getBundle(BUNDLE_MESSAGES).getString(BUNDLE_VALIDATE_LOGIN_NAME_EXISTS));
				return "formUser";
			}
		}

		// GetiD
		userDto.setUsId((long) (listUser.size() + 1));

		userService.save(mapper.usersDtoToUsers(userDto));
		model.addAttribute("userDto", userDto);
		return "login";
	}

	@PostMapping("/formUserEdit")
	public String userEdit(@ModelAttribute("userDto") @Valid UsersDto userDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("userToEditDto", userDto);
			return "editUserInfo";
		}

		Users userFromDb  = userService.findByUsLogin(userDto.getUsLogin());
		userDto.setUsId(userFromDb.getUsId());
		userService.save(mapper.usersDtoToUsers(userDto));
		model.addAttribute("userToEditDto", userDto);
		redirectAttributes.addFlashAttribute("message", "Successful!");
		return "editUserInfo";
	}
}
