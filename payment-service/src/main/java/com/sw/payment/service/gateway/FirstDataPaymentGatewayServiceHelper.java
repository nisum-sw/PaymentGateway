package com.sw.payment.service.gateway;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sw.payment.config.PropertyConfig;
import com.sw.payment.domain.Token;
import com.sw.payment.domain.TransactionRequest;
import com.sw.payment.domain.TransactionResponse;
import com.sw.payment.domain.TransactionType;
import com.sw.payment.domain.Transarmor;
import com.sw.payment.repository.TokenRepository;
import com.sw.payment.repository.TransactionRepository;

import static com.sw.payment.util.GatewayConstants.*;
/**
 * @author Nisum-User
 *
 */

public class FirstDataPaymentGatewayServiceHelper {

	private static final Logger log = LoggerFactory
			.getLogger(FirstDataPaymentGatewayServiceHelper.class);

	RestTemplate restTemplate;
	String appId;
	String token;
	String securedSecret;
	
	public FirstDataPaymentGatewayServiceHelper(RestTemplate restTemplate,
			String appId, String token, String securedSecret) {
		this.restTemplate = restTemplate;
		this.appId= appId;
		this.token = token;
		this.securedSecret = securedSecret;
	}
	
	boolean initiated = false;
	
	private Map<String, String> getSecurityKeys(String appId, String secureId,
			String payLoad) throws Exception {

		Map<String, String> returnMap = new HashMap<String, String>();
		long nonce;
		try {
			nonce = Math.abs(SecureRandom.getInstance("SHA1PRNG").nextLong());
			log.debug("SecureRandom nonce:{}", nonce);
			returnMap.put(NONCE, Long.toString(nonce));
			returnMap.put(APIKEY, this.appId);
			returnMap.put(TIMESTAMP, Long.toString(System.currentTimeMillis()));
			returnMap.put(TOKEN, this.token);
			returnMap.put(APISECRET, this.securedSecret);

			returnMap.put(PAYLOAD, payLoad);
			returnMap.put(AUTHORIZE, getMacValue(returnMap));
			return returnMap;
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public String getMacValue(Map<String, String> data) throws Exception {
		Mac mac = Mac.getInstance("HmacSHA256");
		String apiSecret = data.get(APISECRET);
		log.debug("API_SECRET:{}", apiSecret);
		SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(),
				"HmacSHA256");
		mac.init(secret_key);
		StringBuilder buff = new StringBuilder();
		buff.append(data.get(APIKEY)).append(data.get(NONCE))
				.append(data.get(TIMESTAMP));
		if (data.get(TOKEN) != null)
			buff.append(data.get(TOKEN));
		if (data.get(PAYLOAD) != null)
			buff.append(data.get(PAYLOAD));

		log.info(buff.toString());
		byte[] macHash = mac.doFinal(buff.toString().getBytes("UTF-8"));
		log.info("MacHAsh:{}", Arrays.toString(macHash));
		String authorizeString = Base64.encodeBase64String(toHex(macHash));
		log.info("Authorize: {}", authorizeString);
		return authorizeString;

	}

	public void Initialize() {
		if (!initiated) {

			org.springframework.http.converter.StringHttpMessageConverter sconverter = new StringHttpMessageConverter();
			restTemplate.getMessageConverters().add(sconverter);

			org.springframework.http.converter.json.MappingJacksonHttpMessageConverter converter = new org.springframework.http.converter.json.MappingJacksonHttpMessageConverter();
			converter.getObjectMapper().configure(
					Feature.WRITE_NULL_MAP_VALUES, false);
			converter.getObjectMapper().configure(
					Feature.WRITE_NULL_PROPERTIES, false);
			converter.getObjectMapper().configure(
					Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);

			converter
					.getObjectMapper()
					.getSerializationConfig()
					.setSerializationInclusion(
							org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL);

			converter.getObjectMapper().getSerializationConfig()
					.set(SerializationConfig.Feature.USE_ANNOTATIONS, true);

			converter
					.getObjectMapper()
					.getDeserializationConfig()
					.set(org.codehaus.jackson.map.DeserializationConfig.Feature.USE_ANNOTATIONS,
							true);
			converter
					.getObjectMapper()
					.getDeserializationConfig()
					.set(DeserializationConfig.Feature.AUTO_DETECT_FIELDS, true);

			List<MediaType> mediatypes = new ArrayList<MediaType>();
			mediatypes.add(MediaType.APPLICATION_XML);
			mediatypes.add(MediaType.APPLICATION_JSON);
			mediatypes.add(MediaType.ALL);
			converter.setSupportedMediaTypes(mediatypes);
			restTemplate.getMessageConverters().add(converter);

			initiated = true;
		}
	}

	public byte[] toHex(byte[] arr) {
		String hex = Hex.encodeHexString(arr);
		log.info("Apache common value:{}", hex);
		return hex.getBytes();
	}

	public HttpHeaders getHttpHeader(String appId, String securityKey,
			String payload) throws Exception {
		Initialize();
		Map<String, String> encriptedKey = getSecurityKeys(appId, securityKey,
				payload);
		HttpHeaders header = new HttpHeaders();
		Iterator<String> iter = encriptedKey.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			if (PAYLOAD.equals(key))
				continue;
			header.add(key, encriptedKey.get(key));
		}
		header.add("Accept", "application/json");
		header.setContentType(MediaType.APPLICATION_JSON);
		List<MediaType> mediatypes = new ArrayList<MediaType>();
		mediatypes.add(MediaType.APPLICATION_JSON);
		mediatypes.add(new MediaType("application", "json", Charset
				.forName("UTF-8")));
		header.add("User-Agent", "Java/1.6.0_26");

		return header;
	}

	public String getJSONObject(Object data) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.WRITE_NULL_PROPERTIES, false);
		objectMapper.configure(Feature.WRITE_NULL_MAP_VALUES, false);
		objectMapper
				.configure(
						org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
						false);
		objectMapper
				.getSerializationConfig()
				.setSerializationInclusion(
						org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL);

		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		OutputStream stream = new BufferedOutputStream(byteStream);
		JsonGenerator jsonGenerator = objectMapper.getJsonFactory()
				.createJsonGenerator(stream, JsonEncoding.UTF8);

		objectMapper.writeValue(jsonGenerator, data);
		stream.flush();
		return new String(byteStream.toByteArray());
	}

		public class MyErrorHandling implements ResponseErrorHandler {

		public boolean hasError(ClientHttpResponse clienthttpresponse)
				throws IOException {

			if (clienthttpresponse.getStatusCode() != HttpStatus.OK) {

				if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {

					return true;
				}
			}
			return false;
		}

		public void handleError(ClientHttpResponse response) throws IOException {
			System.out.println(response.getStatusText());
			if (response.getStatusCode() == HttpStatus.FORBIDDEN) {

			}

		}
	}

	public TransactionResponse GetTransactionResponse(Object obj) {
		TransactionResponse response = new TransactionResponse();
		Token token = new Token();
		Transarmor tokenData = new Transarmor();
		token.setTokenData(tokenData);
		response.setToken(token);
		int beginIndex = 0;
		int endIndex = 0;
		String objstr = obj.toString();
		boolean tokenResponse = false;
		objstr = objstr.trim();
		if (objstr.startsWith("Payeezy.callback")) {
			objstr = objstr.substring(19, objstr.length()); // ("Payeezy.callback",
															// "");
			objstr = objstr.trim();
			tokenResponse = true;
		}

		String[] responseData = objstr.split(",");

		for (int i = 0; i < responseData.length; i++) {
			String str = responseData[i];

			String[] dataVals = str.split("=");
			if (tokenResponse) {
				str = str.trim();
				dataVals = str.split(":");
			}
			if (dataVals.length >= 2) {
				dataVals[1] = dataVals[1].replace("{", "");
				dataVals[1] = dataVals[1].replace("}", "");
				dataVals[1] = dataVals[1].replace(":", "");
				dataVals[1] = dataVals[1].replace("\"", "");
				dataVals[1] = dataVals[1].replace("[", "");
			}
			if (dataVals.length >= 3) {
				dataVals[2] = dataVals[2].replace("{", "");
				dataVals[2] = dataVals[2].replace("}", "");
				dataVals[2] = dataVals[2].replace(":", "");
				dataVals[2] = dataVals[2].replace("\"", "");
				dataVals[2] = dataVals[2].replace("[", "");
			}

			if (dataVals[0].contains("results")) {
				String correlationID = dataVals[2];
				response.setCorrelationID(correlationID);
			}

			// if(str.contains("correlation_id"))
			if (dataVals[0].contains("correlation_id")) {
				String correlationID = dataVals[1];
				response.setCorrelationID(correlationID);
			}
			if (str.contains("status")) {
				if (tokenResponse) {
					String status = dataVals[1];
					try {
						int stat = Integer.parseInt(status);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					if (status.length() < 6) // if(stat>0)
					{
						response.setStatus(status);
					}
				} else {
					String status = dataVals[1];
					response.setStatus(status);
				}
			}
			if (str.contains("type")) {
				String type = dataVals[1];
				response.getToken().setTokenType(type);
			}
			if (str.contains("token")) {
				String cardtype = dataVals[1];
				if (dataVals.length > 2) {
					cardtype = dataVals[2];
				}
				response.getToken().getTokenData().setType(cardtype);
			}
			if (str.contains("cardholder_name")) {
				String name = dataVals[1];
				response.getToken().getTokenData().setName(name);
			}

			if (str.contains("exp_date")) {
				String expDate = dataVals[1];
				response.getToken().getTokenData().setExpiryDt(expDate);
			}
			if (str.contains("value")) {
				String value = dataVals[1];
				response.getToken().getTokenData().setValue(value);
			}

			if (str.contains("transaction_id")) {
				String transactionId = dataVals[1];
				response.setTransactionId(transactionId);
			}
			if (str.contains("transaction_tag")) {
				String transactionTag = dataVals[1];
				response.setTransactionTag(transactionTag);
			}
			if (str.contains("amount")) {
				String amount = dataVals[1];
				response.setAmount(amount);
			}
			if (str.contains("transaction_status")) {
				String transactionStatus = dataVals[1];
				response.setTransactionStatus(transactionStatus);
			}
			if (str.contains("validation_status")) {
				String validation_status = dataVals[1];
				response.setValidationStatus(validation_status);
			}
			if (str.contains("transaction_type")) {
				String transaction_type = dataVals[1];
				response.setTransactionType(transaction_type);
			}
			if (str.contains("method")) {
				String method = dataVals[1];
				response.setMethod(method);
			}

		}
		return response;

	}

}
