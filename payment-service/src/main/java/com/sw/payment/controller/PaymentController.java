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
			@RequestBody(required = true) TransactionRequest transactionRequest) throws DataValidationException, FirstDataException {

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
            @RequestBody(required = true) TransactionRequest transactionRequest) throws DataValidationException, FirstDataException {
		
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
	
	
	/*Void : Means Authorization Reversal. Only a payment that is “Authorized” can be voided. 
	A void cancels the authorization. A payment that has been fully captured cannot be voided, 
	it must be refunded instead.*/
	@RequestMapping(value = "/capture", method = RequestMethod.POST,produces="application/json", consumes="application/json")
	public ResponseEntity<TransactionResponse> captureTransaction(
			@RequestHeader(value="apikey" , required=false) String apiKey,
			@RequestHeader(value="token" , required=false) String token,
			@RequestHeader(value="apisecret" , required=false) String apiSecret,
            @RequestHeader(value="Content-Type" , required=true) String contentType,
            @RequestBody(required = true) TransactionRequest transactionRequest) throws DataValidationException, FirstDataException {
	
	if ((transactionRequest.getToken() != null)
			&& (transactionRequest.getToken().getTokenType() != null)
			&& (transactionRequest.getToken().getTokenType().toUpperCase().equals("FDTOKEN"))
			&& transactionRequest.getTransactionTag() != null
			&& transactionRequest.getId() != null
			&& transactionRequest.getTransactionType() != null) {
	}else {
		throw new DataValidationException(" Invalid Input data !! Token Type , Transaction tag , ID  are required fields.");
	}
	TransactionResponse transactionResponse = null;
	try{
	transactionResponse = paymentGatewayService.captureTransaction(transactionRequest);
	}catch(Exception e){
		throw new FirstDataException(e);
	}
	
	return new ResponseEntity<TransactionResponse>(transactionResponse, HttpStatus.CREATED);
	}
	/*Capture : capture of a previous authorization. Only payments that are in an “Authorized” state can be captured.
	The merchant can provide the amount of capture, between 0.01 and the amount of the authorization. 
	Some processors will allow you to capture more than the authorization. 
	Once a capture is completed, the authorization is closed.*/
	@RequestMapping(value = "/void", method = RequestMethod.POST,produces="application/json", consumes="application/json")
	public ResponseEntity<TransactionResponse> voidTransaction(
			@RequestHeader(value="apikey" , required=false) String apiKey,
			@RequestHeader(value="token" , required=false) String token,
			@RequestHeader(value="apisecret" , required=false) String apiSecret,
            @RequestHeader(value="Content-Type" , required=true) String contentType,
            @RequestBody(required = true) TransactionRequest transactionRequest) throws DataValidationException, FirstDataException {
	
	if ((transactionRequest.getToken() != null)
			&& (transactionRequest.getToken().getTokenType() != null)
			&& (transactionRequest.getToken().getTokenType().toUpperCase().equals("FDTOKEN"))
			&& transactionRequest.getTransactionTag() != null
			&& transactionRequest.getId() != null
			&& transactionRequest.getTransactionType() != null) {
	}else {
		throw new DataValidationException(" Invalid Input data !! Token Type , Transaction tag , ID  are required fields.");
	}
	TransactionResponse transactionResponse = null;
	try{
	transactionResponse = paymentGatewayService.voidTransaction(transactionRequest);
	}catch(Exception e){
		throw new FirstDataException(e);
	}
	
	return new ResponseEntity<TransactionResponse>(transactionResponse, HttpStatus.CREATED);
	}
	/*Refund : refunding the entire amount of a transaction. Only payments that have been captured can be refunded. */
	@RequestMapping(value = "/refund", method = RequestMethod.POST,produces="application/json", consumes="application/json")
	public ResponseEntity<TransactionResponse> refundTransaction(
			@RequestHeader(value="apikey" , required=false) String apiKey,
			@RequestHeader(value="token" , required=false) String token,
			@RequestHeader(value="apisecret" , required=false) String apiSecret,
            @RequestHeader(value="Content-Type" , required=true) String contentType,
            @RequestBody(required = true) TransactionRequest transactionRequest) throws DataValidationException, FirstDataException {

		if ((transactionRequest.getToken() != null)
				&& (transactionRequest.getToken().getTokenType() != null)
				&& (transactionRequest.getToken().getTokenType().toUpperCase().equals("FDTOKEN"))
				&& transactionRequest.getTransactionTag() != null
				&& transactionRequest.getId() != null
				&& transactionRequest.getTransactionType() != null) {
		}else {
			throw new DataValidationException(" Invalid Input data !! Token Type , Transaction tag , ID  are required fields.");
		}
		TransactionResponse transactionResponse = null;
		try{
			transactionResponse = paymentGatewayService.refundTransaction(transactionRequest);
		}catch(Exception e){
			throw new FirstDataException(e);
		}

		return new ResponseEntity<TransactionResponse>(transactionResponse, HttpStatus.CREATED);
	}
	
	

	
}