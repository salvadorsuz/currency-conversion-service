package com.in28minutes.microservices.currencyconversionservice.controller.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="currency-exchange-service", url="localhost:8000")
public interface CurrencyExchangeServiceClient {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	ExchangeValueBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
