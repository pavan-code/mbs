package com.pavan.mbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pavan.mbs.entity.*;
import com.pavan.mbs.repo.AddressRepo;
import com.pavan.mbs.repo.CustomerRepo;
import com.pavan.mbs.repo.MobileRepo;

@Service
public class CustomerService {

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	AddressRepo addressRepo;

	@Autowired
	MobileRepo mobileRepo;

	static final String message = "Message";
	static final String status = "Status";
	static final String statusCode = "StatusCode";
	String fals = "false";

	Map<String, String> body = new HashMap<>();

	public ResponseEntity<Map<String, String>> addCustomer(Customer customer) {			
		Customer c = customerRepo.save(customer);
		body.put(message, customer.getType() + " Added Successfully");
		body.put(status, "true");
		body.put(statusCode, "201");
		body.put("data", c.toString());
		return new ResponseEntity<>(body, HttpStatus.CREATED);
	}
	
	public List<Customer> getCustomerByStaffId(int id) {
		return customerRepo.findByStaffId(id);
	}
	public ResponseEntity<Map<String, String>> checkValid(String aadhar) {
		Customer c = customerRepo.findByAadharNumber(aadhar);
		if(c != null) {
			body.put(message, "Customer has registered with Aadhar: " + aadhar);
			body.put(status, "true");
			body.put(statusCode, "302");
			body.put("data", c.toString());
			return new ResponseEntity<>(body, HttpStatus.FOUND);
		} else {
			body.put(message, "Customer not found with Aadhar: " + aadhar);
			body.put(status, fals);
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
	public DataResponse<Customer> getCustomer(int id) {
		Optional<Customer> c = customerRepo.findById(id);
		if(c.isPresent()) {			
			return new DataResponse<>(true, 200, "Customer details", null, c.get());
		} else {			
			return new DataResponse<>(false, 404, "customer not found", null, null);
		}
	}
	
	public Customer getCustomerByEmail(String email) {
		Customer c = customerRepo.findByEmail(email);
		if(c != null) {
			return c;
		} return new Customer();
	}
	public DataResponse<Customer> getCustomers() {
		List<Customer> customers = customerRepo.findByType("customer");		
		return new DataResponse<>(true, 200, "Users data", customers, null);
		
	}
	public DataResponse<Customer> updatePassword(int cid, String newPassword) {
		body = new HashMap<>();
		Customer c = customerRepo.getById(cid);
		c.setPassword(newPassword);
		customerRepo.save(c);
		return new DataResponse<>(true, 200, "Password updated Successfully", null, null);
//		if(c.getPassword().equals(request.getExistingPassword())) {
//			if(request.getNewPassword().equals(request.getConfirmNewPassword())) {
//				c.setPassword(request.getNewPassword());
//				customerRepo.save(c);
//				body.put(message, "Password changed successfully");
//				body.put(status, "true");
//				body.put(statusCode, "200");		
//				return new ResponseEntity<>(body, HttpStatus.OK);
//			} else {
//				body.put("error", "New password and Confirm password are not equal!");
//				body.put(status, fals);
//				body.put(statusCode, "403");		
//				return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
//			}
//		} else {
//			body.put("error", "Current password is incorrect!");
//			body.put(status, fals);
//			body.put(statusCode, "403");		
//			return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
//		}
	}

	public ResponseEntity<Map<String, String>> getAdmin() {
		Customer admin = customerRepo.findByType("admin").get(0);
		body.put(message, "Admin Details");
		body.put(status, "true");
		body.put(statusCode, "302");
		body.put("data", admin.toString());
		return new ResponseEntity<>(body, HttpStatus.FOUND);
	}
	public ResponseEntity<Map<String, String>> getMobiles(int id) {
		Optional<Customer> c = customerRepo.findById(id);
		if(c.isPresent()) {
			List<Mobile> mobiles = c.get().getMobiles();			
			body.put(message, "Customer mobile numbers");
			body.put(status, "true");
			body.put(statusCode, "302");
			body.put("data", mobiles.toString());
			return new ResponseEntity<>(body, HttpStatus.FOUND);
		} else {
			body.put(message, "Customer not found id: " + id);
			body.put(status, fals);
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<Map<String, String>> changeOperator(Mobile mobile, int cid, int mid) {
		Optional<Customer> c = customerRepo.findById(cid);
		if(c.isPresent()) {
			Customer cust = c.get();
			Mobile mobi;
			List<Mobile> m = cust.getMobiles().stream().filter(mob -> mob.getId()==mid).collect(Collectors.toList());
			if(!m.isEmpty()) {
				mobi = m.get(0);
				mobi.setOperator(mobile.getOperator());
				mobi = mobileRepo.save(mobi);
				body.put(message, "Operator updated Successfully");
				body.put(status, "true");
				body.put(statusCode, "200");
				body.put("data", mobi.toString());
				return new ResponseEntity<>(body, HttpStatus.OK);
			}	else {
				body.put(message, "Mobile not found with id: " + mid);
				body.put(status, fals);
				body.put(statusCode, "404");
				body.put("data", null);
				return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
			}
		} else {
			body.put(message, "Customer not found with id: " + cid);
			body.put(status, fals);
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Map<String, String>> changeStatus(Mobile mobile, int cid, int mid) {
		Optional<Customer> c = customerRepo.findById(cid);
		if(c.isPresent()) {
			Customer cust = c.get();
			Mobile mobi;
			List<Mobile> m = cust.getMobiles().stream().filter(mob -> mob.getId()==mid).collect(Collectors.toList());
			if(!m.isEmpty()) {
				mobi = m.get(0);
				mobi.setActive(mobile.isActive());
				mobi = mobileRepo.save(mobi);
				body.put(message, "Status updated Successfully");
				body.put(status, "true");
				body.put(statusCode, "200");
				body.put("data", mobi.toString());
				return new ResponseEntity<>(body, HttpStatus.OK);
			}	else {
				body.put(message, "Mobile not found with id: " + mid);
				body.put(status, fals);
				body.put(statusCode, "404");
				body.put("data", null);
				return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
			}
		} else {
			body.put(message, "Customer not found with id: " + cid);
			body.put(status, fals);
			body.put(statusCode, "404");
			body.put("data", null);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
}
