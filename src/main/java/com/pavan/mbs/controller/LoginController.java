package com.pavan.mbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.mbs.entity.LoginResponse;
import com.pavan.mbs.entity.Request;
import com.pavan.mbs.service.LoginService;


@RestController
@CrossOrigin("http://localhost:4200")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody Request request) {			
		return loginService.login(request);
	}
	
}
