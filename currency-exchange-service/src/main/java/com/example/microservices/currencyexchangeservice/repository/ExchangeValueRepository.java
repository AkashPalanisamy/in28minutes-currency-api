package com.example.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservices.currencyexchangeservice.entity.ExchangeValue;

public interface ExchangeValueRepository extends 
	JpaRepository<ExchangeValue, Long>{	
	
		ExchangeValue findByFromAndTo(String from, String to);
}
