package com.pavan.mbs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.mbs.entity.Numbers;
import com.pavan.mbs.service.NumberService;

@RestController
public class NumbersController {

	@Autowired
	private NumberService numberService;
	
	@PostMapping("/numbers")
	public ResponseEntity<Map<String, String>> addNumber(@RequestBody Numbers number) {
		return numberService.addNumber(number);
	}
	
	@GetMapping("/numbers")
	public ResponseEntity<Map<String, String>> getNumbers() {
		return numberService.getNumbers();
	}
	
	@PutMapping("/numbers")
	public ResponseEntity<Map<String, String>> updateNumber(@RequestBody Numbers number) {
		return numberService.updateNumber(number);
	}
	
	@DeleteMapping("/numbers/{id}")
	public ResponseEntity<Map<String, String>> deleteNumber(@PathVariable int id) {
		return numberService.deleteNumber(id);
	}
}
