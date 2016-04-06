package com.sw.payment.service.gateway;

import java.io.IOException;

import com.sw.payment.domain.TransactionRequest;
import com.sw.payment.domain.TransactionResponse;


public interface IPaymentGatewayService {
	
	//generate token
	public TransactionResponse generateToken(TransactionRequest transactionRequest) throws IOException;
	
	//for purchase
	public TransactionResponse purchaseTransaction(TransactionRequest transactionRequest) throws IOException;
	
	//for Authorization
	public TransactionResponse authorizeTransaction(TransactionRequest transactionRequest) throws IOException;
	
	//for Capture , refund , void, and cancel
	public TransactionResponse doSecondaryTransaction(TransactionRequest transactionRequest) throws IOException;

}
