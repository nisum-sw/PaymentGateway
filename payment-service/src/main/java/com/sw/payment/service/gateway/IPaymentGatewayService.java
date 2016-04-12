package com.sw.payment.service.gateway;

import java.io.IOException;

import com.sw.payment.domain.TransactionRequest;
import com.sw.payment.domain.TransactionResponse;


public interface IPaymentGatewayService {
	
	//generate token
	public TransactionResponse generateToken(TransactionRequest transactionRequest) throws Exception;
	
	//for purchase
	public TransactionResponse purchaseTransaction(TransactionRequest transactionRequest) throws Exception;
	
	//for Authorization
	public TransactionResponse authorizeTransaction(TransactionRequest transactionRequest) throws Exception;
	
	//for Capture 
	public TransactionResponse captureTransaction(TransactionRequest transactionRequest) throws Exception;
	 
	//for void
	public TransactionResponse voidTransaction(TransactionRequest transactionRequest) throws Exception;
		
	//for refund
	public TransactionResponse refundTransaction(TransactionRequest transactionRequest) throws Exception;

}
