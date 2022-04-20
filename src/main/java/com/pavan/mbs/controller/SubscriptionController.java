package com.pavan.mbs.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.mbs.entity.Customer;
import com.pavan.mbs.entity.DataResponse;
import com.pavan.mbs.entity.Subscription;
import com.pavan.mbs.service.CustomerService;
import com.pavan.mbs.service.EmailService;
import com.pavan.mbs.service.SubscriptionService;

@RestController
@CrossOrigin("*")
public class SubscriptionController {

	String otp = null;

	@Autowired
	private EmailService emailService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private SubscriptionService subsService;

	@PostMapping("/customer/{cid}/mobile/{mid}/subscribe")
	public ResponseEntity<Map<String, String>> subscribe(@RequestBody Subscription subs, @PathVariable int cid,
			@PathVariable int mid) {
		return subsService.subscribe(cid, mid, subs);
	}

	@GetMapping("/customer/{cid}/mobile/{mid}/subscriptions")
	public ResponseEntity<Map<String, String>> getSubscriptions(@PathVariable int cid, @PathVariable int mid) {
		return subsService.getSubscriptions(cid, mid);
	}

	@GetMapping("/generate-otp/{email}")
	public DataResponse<String> generateOTP(@PathVariable String email) throws javax.mail.MessagingException {
		System.out.println(email);
		Customer c = customerService.getCustomerByEmail(email);	

		if (c.getEmail() != null) {

			String numbers = "0123456789";
			Random rndm_method = new Random();
			String otp = "";
			for (int i = 0; i < 6; i++)
				otp += numbers.charAt(rndm_method.nextInt(numbers.length()));
			this.otp = otp;
			System.out.println(this.otp);
			try {
				emailService.sendEmail(email, "OTP", this.otp);
				return new DataResponse<String>(true, 200, this.otp, null, null);
			} catch (Exception e) {
				
				return new DataResponse<String>(false, 404, "Failed to send OTP", null, null);
			}
		} else {			
			return new DataResponse<String>(false, 404, "Email ID is not registered!!", null, null);
		}
	}

	@GetMapping("/validate-otp/{otp}")
	public Boolean validate(@PathVariable String otp) {
		if (otp.equals(this.otp)) {
			return true;
		} else {
			return false;
		}
	}
}