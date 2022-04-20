package com.pavan.mbs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.mbs.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	Customer findByEmail(String email);
	List<Customer> findByType(String type);
	Customer findByAadharNumber(String aadhar);
	List<Customer> findByStaffId(int id);
}
