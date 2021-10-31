package com.pavan.mbs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.mbs.entity.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, Integer> {

//	List<Subscription> findByMid(int mid);
}
