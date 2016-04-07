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
	
	//for Capture , refund , void, and cancel
	public TransactionResponse doSecondaryTransaction(TransactionRequest transactionRequest) throws Exception;

}
