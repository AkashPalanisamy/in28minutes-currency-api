package com.example.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.microservices.currencyconversionservice.entity.CurrencyConversionBean;

@FeignClient(name="currency-exchange-service", 
			 url="localhost:8000")
@Component
public interface CurrencyConversionProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);

}
