package com.sw.payment.controller;

import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sw.payment.domain.TransactionRequest;
import com.sw.payment.domain.TransactionResponse;
import com.sw.payment.service.gateway.IPaymentGatewayService;
import com.sw.payment.service.scheduler.ISchedulerService;

@RestController
@RequestMapping("/scheduler/")
public class SchedulerController {

	@Autowired
	private ISchedulerService  schedulerService; 
	
	
	@RequestMapping(value = "/foo/{name}", method = RequestMethod.GET)
	public String getGreeting(@PathVariable String name) {
		String result="Hello "+name;		
		return result;
	}
	
	@RequestMapping(value = "/state", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> changeRunningStateOfScheduler( 
			@RequestParam(value = "action", required=true) String action) throws Exception {
		if (action.equalsIgnoreCase("start")) {
			schedulerService.startScheduler();
		}else if(action.equalsIgnoreCase("stop")) {
			schedulerService.stopScheduler();
 	 	}else {
 	 		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
 	 	 }
 	 	 	return new ResponseEntity<String>(HttpStatus.OK);
 	 }
	
	@RequestMapping(value = "/interval", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> changeRunningIntervalOfScheduler(
			@RequestParam(value = "cronExpression", required=true) String cronExpression) {
		
		System.out.println(" cronExpression =  " + cronExpression);
		if(!CronExpression.isValidExpression(cronExpression)){
			return new ResponseEntity<String>("Invalid Cron Expression", HttpStatus.BAD_REQUEST);
		}               
		schedulerService.reScheduleTimer(cronExpression);
		return new ResponseEntity<String>(HttpStatus.OK);
	}


	
}