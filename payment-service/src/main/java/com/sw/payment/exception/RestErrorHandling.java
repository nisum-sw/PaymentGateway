package com.sw.payment.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestErrorHandling implements ResponseErrorHandler {

    public boolean hasError(ClientHttpResponse clienthttpresponse) throws IOException {

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