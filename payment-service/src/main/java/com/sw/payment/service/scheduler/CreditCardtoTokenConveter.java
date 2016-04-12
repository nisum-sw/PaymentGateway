package com.sw.payment.service.scheduler;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;


@Component("creditCardtoTokenConveter")
public class CreditCardtoTokenConveter {

	public String initiateCreditCardtoTokenConveter() {
		
		System.out.println("CCtoTokenJob::convertNextSetOfCCTokensHello started at date/time" + new DateTime() );
		
		return "foo";
	}
}
