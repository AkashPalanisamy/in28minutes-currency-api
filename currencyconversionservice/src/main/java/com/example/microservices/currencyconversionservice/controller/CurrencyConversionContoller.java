package com.example.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.currencyconversionservice.entity.CurrencyConversionBean;
import com.example.microservices.currencyconversionservice.proxy.CurrencyConversionProxy;

@RestController
public class CurrencyConversionContoller {
	
	@Autowired
	private CurrencyConversionProxy proxy;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/welcome")
	public String welcome() {
		return "WELCOME";
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CurrencyConversionBean conversion = proxy.retrieveExchangeValue(from, to);
		
		conversion.setQuantity(quantity);
		conversion.setTotalCalculatedAmount(quantity.multiply(conversion.getConversionMultiple()));
		
		return conversion;			
	}
	
	
	/*
	 * By using "REST TEMPLATE"
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from",from);
		uriVariables.put("to",to);		
		
		ResponseEntity<CurrencyConversionBean> response =  new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}",      // URI
				CurrencyConversionBean.class,										// ResponseType
				uriVariables);														// Variables inside URI
		
		CurrencyConversionBean conversion = response.getBody();			
		
		conversion.setQuantity(quantity);
		conversion.setTotalCalculatedAmount(quantity.multiply(conversion.getConversionMultiple()));
		
		return conversion;
	}
	 */
	

}
