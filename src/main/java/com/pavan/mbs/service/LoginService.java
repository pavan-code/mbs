package com.pavan.mbs.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavan.mbs.entity.Customer;
import com.pavan.mbs.entity.LoginResponse;
import com.pavan.mbs.entity.Request;
import com.pavan.mbs.entity.UserData;
import com.pavan.mbs.repo.CustomerRepo;

@Service
public class LoginService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	static final String message = "Message";
	static final String status = "Status";
	static final String statusCode = "StatusCode";
	
	public LoginResponse login(Request request) {
		String username = request.getEmail();
		String password = request.getPassword();
		String type = request.getType();				
		Customer customer = customerRepo.findByEmail(username);
		if(customer == null) {			
			return new LoginResponse("User not found", false, 404, null);
		} else {
			if(username.equals(customer.getEmail())) {
				if(password.equals(customer.getPassword())) {
					if(type.equals(customer.getType())) {						
						return new LoginResponse("Login Successfull", true, 200, new UserData(customer.getId(), customer.getFirstname(), customer.getType()));					
					} else {						
						return new LoginResponse("Unauthorized Credentials for " + type , false, 403, null);
					}
				} else {
					return new LoginResponse("Password Incorrect", false, 401, null);
				}
			} else {				
				return new LoginResponse("Invalid Username", false, 401, null);
			}
		}
	}
}
