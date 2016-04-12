package com.sw.payment.service.scheduler;

import java.io.IOException;

import com.sw.payment.domain.TransactionRequest;
import com.sw.payment.domain.TransactionResponse;


public interface ISchedulerService {
	
	public void startScheduler() throws Exception;
	
	public void stopScheduler() throws Exception;
	
	void changeRunningIntervalOfScheduler(String cronExpression) throws Exception;

	public void reScheduleTimer(String cronExpression);

}
