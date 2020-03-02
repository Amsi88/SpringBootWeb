package com.springBoot.controller;

import com.springBoot.dto.UsersDto;
import com.springBoot.mapper.UserMapper;
import com.springBoot.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class BaseErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "404";
			}
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "error";
			}
		}
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
