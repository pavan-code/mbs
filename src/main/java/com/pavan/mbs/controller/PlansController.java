package com.pavan.mbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.mbs.entity.DataResponse;
import com.pavan.mbs.entity.Plans;
import com.pavan.mbs.service.PlansService;

/**
 * 
 * @author surya 
 * @version 1.0.0
 */
@RestController
@CrossOrigin("*")
public class PlansController {
	
	@Autowired
	private PlansService plansService;
	
	@PostMapping("/plan")
	public DataResponse<Plans> addPlan(@RequestBody Plans plan) {
		return plansService.addPlan(plan);
	}
	@DeleteMapping("/plan/{id}")
	public DataResponse<Plans> deletePlan(@PathVariable int id) {
		return plansService.deletePlan(id);
	}
	@PostMapping("/plans")
	public ResponseEntity<Map<String, String>> addPlans(@RequestBody List<Plans> plans) {
		return plansService.addPlans(plans);
	}
	@GetMapping("/plans")
	public DataResponse<Plans> getAllPlans() {
		return plansService.getAllPlans();
	}
	@GetMapping("/plans/operator={operator}")
	public List<Plans> getPlansByOperator(@PathVariable String operator) {
		return plansService.getPlanByOperator(operator);
	}
	@GetMapping("/plan/{id}")
	public Plans getPlansById(@PathVariable int id) {
		return plansService.getPlanById(id);
	}
}
