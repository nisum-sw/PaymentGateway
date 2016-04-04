package com.sw.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sw.payment.domain.TransactionRequest;
import com.sw.payment.service.gateway.IPaymentGatewayService;

@RestController
@RequestMapping("/service/payment")
public class PaymentController {

	@Autowired
	private IPaymentGatewayService paymentGatewayService; 
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getGreeting(@PathVariable String name) {
		String result="Hello "+name;		
		return result;
	}
	
	
	@RequestMapping(value = "/transaction/primary", method = RequestMethod.PUT)
	public String doPrimaryTransaction(
			@RequestHeader(value="apikey" , required=false) String apiKey,
			@RequestHeader(value="token" , required=false) String token,
			@RequestHeader(value="apisecret" , required=false) String apiSecret,
            @RequestHeader(value="Content-Type" , required=true) String contentType,
            @RequestBody(required = true) TransactionRequest transactionRequest) throws Exception {
		
			String result=transactionRequest.getAmount() + contentType + apiKey + apiSecret+ token;		
		//
			paymentGatewayService.doPrimaryTransaction(transactionRequest);
			
			return result;
	}
	
	

       
	
	
	
	
	
}