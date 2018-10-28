package com.in28minutes.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currencyconversionservice.beans.CurrencyConversionBean;
import com.in28minutes.microservices.currencyconversionservice.controller.client.CurrencyExchangeServiceClient;
import com.in28minutes.microservices.currencyconversionservice.controller.client.ExchangeValueBean;

@RestController
@RequestMapping("/currency-converter")
public class CurrencyConverterController {

	private CurrencyExchangeServiceClient currencyExchangeServiceClient;
	
	@Autowired
	public CurrencyConverterController(final CurrencyExchangeServiceClient currencyExchangeServiceClient) {
		this.currencyExchangeServiceClient = currencyExchangeServiceClient;
	}


	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
//		Map<String,String> uriVariables = new HashMap<String,String>();
//        uriVariables.put("from", from);
//        uriVariables.put("to", to);
//		
//		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
//				CurrencyConversionBean.class, uriVariables);
//		
//		CurrencyConversionBean response = responseEntity.getBody();
		
		
		ExchangeValueBean response = currencyExchangeServiceClient.retrieveExchangeValue(from, to);
		
		BigDecimal total = quantity.multiply(BigDecimal.valueOf(response.getConversionMultiple()));
		
		return new CurrencyConversionBean(from, to, BigDecimal.valueOf(response.getConversionMultiple()), quantity, total, response.getPort());
	}
	
}
