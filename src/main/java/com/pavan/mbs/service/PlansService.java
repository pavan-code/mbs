package com.pavan.mbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pavan.mbs.entity.DataResponse;
import com.pavan.mbs.entity.Plans;
import com.pavan.mbs.repo.PlansRepo;

@Service
public class PlansService {

	@Autowired
	private PlansRepo plansRepo;
	
	static final String message = "Message";
	static final String status = "Status";
	static final String statusCode = "StatusCode";
	
	Map<String, String> body = new HashMap<>();
	
	public DataResponse<Plans> addPlan(Plans plan) {
		Plans planSaved = plansRepo.save(plan);		
		return new DataResponse<Plans>(true, 201, "Plan added Successfully", null, planSaved);
	}
	public DataResponse<Plans> deletePlan(int id) {
		plansRepo.deleteById(id);
		return new DataResponse<Plans>(true, 201, "Plan deleted Successfully", null, null);
	}
	public ResponseEntity<Map<String, String>> addPlans(List<Plans> plans) {
		List<Plans> planSaved = plansRepo.saveAll(plans);
		body.put(message, "Plans Added Successfully");
		body.put(status, "true");
		body.put(statusCode, "201");
		body.put("data", planSaved.toString());
		return new ResponseEntity<>(body, HttpStatus.CREATED);
	}
	public Plans getPlanById(int id) {
		Optional<Plans> plan = plansRepo.findById(id);
		if(plan.isPresent()) {
			return plan.get();			
		} else {
			return new Plans();
		}
	} 
	public DataResponse<Plans> getAllPlans() {
		List<Plans> plans = plansRepo.findAll();		
		return new DataResponse<Plans>(true, 200, "all mobile plans", plans, null);
		
	}
	public List<Plans> getPlanByOperator(String operator) {
		List<Plans> plans = plansRepo.findByOperator(operator);
		return plans;
	}
}
