package com.sw.payment.service.gateway;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.sw.payment.config.PropertyConfig;
import com.sw.payment.domain.TransactionRequest;
import com.sw.payment.domain.TransactionResponse;
import com.sw.payment.domain.TransactionType;
import com.sw.payment.exception.RestErrorHandling;
import com.sw.payment.repository.TransactionRepository;

import static com.sw.payment.util.GatewayConstants.*;

/**
 * @author Nisum-User
 *
 */
@Service
public class FirstDataPaymentGatewayService implements IPaymentGatewayService {

	private static final Logger log = LoggerFactory
			.getLogger(FirstDataPaymentGatewayService.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	PropertyConfig propertyConfig;

	@Autowired
	private TransactionRepository transactionRepository;

	@Value("${url}")
	private String url;

	@Value("${apiKey}")
	private String appId;

	@Value("${apiSecret}")
	private String securedSecret;

	@Value("${merchantToken}")
	private String token;

	private FirstDataPaymentGatewayServiceHelper serviceHelper;
	
	private void serviceHelperInitializer() {
		if(serviceHelper == null)
			serviceHelper = new FirstDataPaymentGatewayServiceHelper(restTemplate,appId, token, securedSecret);
	}

	@Override
	public TransactionResponse purchaseTransaction(TransactionRequest transactionRequest) throws Exception {
		return doPrimaryTransaction(transactionRequest);
	}

	@Override
	public TransactionResponse authorizeTransaction(TransactionRequest transactionRequest) throws Exception {
		return doPrimaryTransaction(transactionRequest);
	}

	private TransactionResponse doPrimaryTransaction(
			TransactionRequest transactionRequest) throws Exception {
		serviceHelperInitializer();
		String url = this.url + "/transactions";
		restTemplate.setErrorHandler(new RestErrorHandling());
		String payload = serviceHelper.getJSONObject(transactionRequest);
		HttpEntity<TransactionRequest> request = new HttpEntity<TransactionRequest>(
				transactionRequest, serviceHelper.getHttpHeader(this.appId,
						this.securedSecret, payload));
		ResponseEntity<TransactionResponse> response = restTemplate.exchange(
				url, HttpMethod.POST, request, TransactionResponse.class);
		TransactionResponse transResponse = response.getBody();
		transactionRepository.save(transResponse);
		return transResponse;
	}
	
	@Override
	public TransactionResponse captureTransaction(TransactionRequest trans)
			throws Exception {
		trans.setTransactionType(TransactionType.CAPTURE.name());
		return doSecondaryTransactionObject(trans);
	}

	@Override
	public TransactionResponse refundTransaction(TransactionRequest trans)
			throws Exception {
		trans.setTransactionType(TransactionType.REFUND.name());
		return doSecondaryTransactionObject(trans);
	}

	@Override
	public TransactionResponse voidTransaction(TransactionRequest trans)
			throws Exception {
		trans.setTransactionType(TransactionType.VOID.name());
		return doSecondaryTransactionObject(trans);
	}

	@Override
	public com.sw.payment.domain.TransactionResponse generateToken(
			TransactionRequest transactionRequest) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public TransactionResponse doSecondaryTransaction(
			TransactionRequest transactionRequest) throws IOException {
		return null;
	}

	public TransactionResponse doSecondaryTransactionObject(
			TransactionRequest trans) throws Exception {
		serviceHelperInitializer();
		String url = this.url + "/transactions/{id}";
		trans.setTransactionType(trans.getTransactionType().toLowerCase());
		String payload = serviceHelper.getJSONObject(trans);
		HttpEntity<TransactionRequest> request = new HttpEntity<TransactionRequest>(
				trans, serviceHelper.getHttpHeader(this.appId,
						this.securedSecret, payload));
		ResponseEntity<Object> response = restTemplate.exchange(url,
				HttpMethod.POST, request, Object.class, trans.getId());

		Object o2 = response.getBody();
		TransactionResponse resp = serviceHelper.GetTransactionResponse(o2
				.toString());
		transactionRepository.save(resp);
		return resp;

	}

	public TransactionResponse doPrimaryTokenTransaction(
			TransactionRequest trans) throws Exception {

		Assert.notNull(trans.getAmount(), "Amount is not present");
		Assert.notNull(trans.getTransactionType(),
				"Transaction type is not present");

		String url = this.url + "/transactions/tokens";
		if ((trans.getToken().getTokenData().getValue() == "")
				|| (trans.getToken().getTokenData().getValue() == TA_TOKEN_VALUE)) {
			url = this.url + "/transactions/tokens";
		}

		String payload = serviceHelper.getJSONObject(trans);
		HttpEntity<TransactionRequest> request = new HttpEntity<TransactionRequest>(
				trans, serviceHelper.getHttpHeader(this.appId,
						this.securedSecret, payload));
		ResponseEntity<TransactionResponse> response = restTemplate.exchange(
				url, HttpMethod.POST, request, TransactionResponse.class);

		return response.getBody();
	}

	public TransactionResponse doSecondaryTokenTransaction(
			TransactionRequest trans) throws Exception {
		Assert.notNull(trans.getTransactionTag(),
				"Transaction Tag is not present");
		Assert.notNull(trans.getId(), "Id is not present");
		Assert.notNull(trans.getTransactionType(),
				"Transaction type is not present");
		String url = this.url + "/transactions/{id}";
		String payload = serviceHelper.getJSONObject(trans);
		HttpEntity<TransactionRequest> request = new HttpEntity<TransactionRequest>(
				trans, serviceHelper.getHttpHeader(this.appId,
						this.securedSecret, payload));
		ResponseEntity<TransactionResponse> response = restTemplate.exchange(
				url, HttpMethod.POST, request, TransactionResponse.class,
				trans.getId());
		// return doTransaction(trans,credentials);
		return response.getBody();

		// return null;
	}

	public TransactionResponse doSecondaryTransaction1(TransactionRequest trans)
			throws Exception {
		Assert.notNull(trans.getTransactionTag(),
				"Transaction Tag is not present");
		Assert.notNull(trans.getId(), "Id is not present");
		Assert.notNull(trans.getTransactionType(),
				"Transaction type is not present");
		String url = this.url + "/transactions/{id}";
		String payload = serviceHelper.getJSONObject(trans);
		HttpEntity<TransactionRequest> request = new HttpEntity<TransactionRequest>(
				trans, serviceHelper.getHttpHeader(this.appId,
						this.securedSecret, payload));
		ResponseEntity<TransactionResponse> response = restTemplate.exchange(
				url, HttpMethod.POST, request, TransactionResponse.class,
				trans.getId());
		// return doTransaction(trans,credentials);
		return response.getBody();
		// return null;
	}

}
