package com.sw.payment.service.gateway;

import java.io.IOException;

import com.sw.payment.domain.TransactionRequest;
import com.sw.payment.domain.TransactionResponse;


public interface IPaymentGatewayService {
	
	//generate token
	public TransactionResponse generateToken(TransactionRequest transactionRequest) throws IOException;
	
	//for purchase or Authorization
	public TransactionResponse doPrimaryTransaction(TransactionRequest transactionRequest) throws IOException;
	
	//for Capture , refund , void, and cancel
	public TransactionResponse doSecondaryTransaction(TransactionRequest transactionRequest) throws IOException;

}
