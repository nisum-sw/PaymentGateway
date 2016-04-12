package com.sw.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sw.payment.domain.TransactionRequest;
import com.sw.payment.domain.TransactionResponse;
import com.sw.payment.domain.TransactionType;
import com.sw.payment.exception.DataIntegrityViolationException;
import com.sw.payment.exception.DataValidationException;
import com.sw.payment.exception.FirstDataException;
import com.sw.payment.service.gateway.IPaymentGatewayService;

@RestController
@RequestMapping(value="/transaction", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

	@Autowired
	private IPaymentGatewayService paymentGatewayService; 
	
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getGreeting(@PathVariable String name) {
		String result="Hello "+name;		
		return result;
	}

	
	
	@RequestMapping(value = "/purchase", method = RequestMethod.POST,produces="application/json", consumes="application/json")
	public ResponseEntity<TransactionResponse> purchaseTransaction(
			@RequestHeader(value="apikey" , required=false) String apiKey,
			@RequestHeader(value="token" , required=false) String token,
			@RequestHeader(value="apisecret" , required=false) String apiSecret,
			@RequestHeader(value="Content-Type" , required=true) String contentType,
            @RequestBody(required = true) TransactionRequest transactionRequest) throws Exception {
		
		if (transactionRequest.getAmount() == null
				|| !TransactionType.PURCHASE.name().equalsIgnoreCase(
						transactionRequest.getTransactionType())
				|| transactionRequest.getPaymentMethod() == null
				|| transactionRequest.getCurrency() == null) {
			throw new DataValidationException(" Invalid Input data !!");
		}
		
		TransactionResponse transactionResponse = null;
		try{
		transactionResponse = paymentGatewayService.purchaseTransaction(transactionRequest);
		}catch(Exception e){
			throw new FirstDataException(e);
		}
		
		return new ResponseEntity<TransactionResponse>(transactionResponse, HttpStatus.CREATED);
	 
	}
	
	
	@RequestMapping(value = "/authorize", method = RequestMethod.POST,produces="application/json", consumes="application/json")
	public ResponseEntity<TransactionResponse> authorizeTransaction(
			@RequestHeader(value="apikey" , required=false) String apiKey,
			@RequestHeader(value="token" , required=false) String token,
			@RequestHeader(value="apisecret" , required=false) String apiSecret,
            @RequestHeader(value="Content-Type" , required=true) String contentType,
            @RequestBody(required = true) TransactionRequest transactionRequest) throws DataValidationException,Exception {
		
		if (transactionRequest.getAmount() == null
				|| !TransactionType.AUTHORIZE.name().equalsIgnoreCase(
						transactionRequest.getTransactionType())
				|| transactionRequest.getPaymentMethod() == null
				|| transactionRequest.getCurrency() == null) {
			throw new DataValidationException(" Invalid Input data !!");
		}
		TransactionResponse transactionResponse = null;
			try{
			transactionResponse = paymentGatewayService.authorizeTransaction(transactionRequest);
			}catch(Exception e){
				throw new FirstDataException(e);
			}
			
			return new ResponseEntity<TransactionResponse>(transactionResponse, HttpStatus.CREATED);
		
	}
	

	
}