package com.sw.payment.service.scheduler;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component("SchedulerJob")
@Slf4j
public class SchedulerJob {
	
	@Value("${invoice.status.reversal.time.hours}")
    @Getter(value = AccessLevel.PROTECTED)
	@Setter
    private int reverseHour;
	

	public void schedulerJobApi() throws Exception  {
		
		log.info("Invoice status scheduler is started");
		
		System.out.println( " hello world ");
	}


}

