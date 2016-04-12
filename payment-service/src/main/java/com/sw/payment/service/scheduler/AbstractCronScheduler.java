package com.sw.payment.service.scheduler;

import java.util.concurrent.ScheduledFuture;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

@Slf4j
public abstract class AbstractCronScheduler{

	@Getter
	private String currentCronExpression;

	public AbstractCronScheduler(String cronExpression) {
		validateCronExpression(cronExpression);
		this.currentCronExpression = cronExpression;
	}

	private void validateCronExpression(String cronExpression) {
		new CronTrigger(cronExpression);
	}

	@SuppressWarnings("rawtypes")
    private ScheduledFuture currentSchedulerThread;

	public void startScheduler() {
		stopScheduler();
		this.currentSchedulerThread = getScheduler().schedule(getRunnable(), new CronTrigger(currentCronExpression));
		log.info(String.format("%s has started with cron schedule: %s",
				getServiceId(), getCurrentCronExpression()));
	}

	public void stopScheduler() {
		if (isEnabled()) {
			this.currentSchedulerThread.cancel(true);
			this.currentSchedulerThread = null;
			log.info(String.format("%s has stopped", getServiceId()));
		}
	}

	public void reScheduleTimer(String cronExpression) {
		String oldCronExpression = getCurrentCronExpression();
		validateCronExpression(cronExpression);
		this.currentCronExpression = cronExpression;
		if (isEnabled()){
			startScheduler();
		}
		log.info(String.format("%s [%s] has been rescheduled from '%s' to '%s'",
				getServiceId(), (isEnabled() ? "running" : "stopped"),
				oldCronExpression, getCurrentCronExpression()));
	}

	public boolean isEnabled() {
		if (this.currentSchedulerThread == null
				|| this.currentSchedulerThread.isCancelled()) {
			return false;
		}
		return true;
	}

	protected abstract TaskScheduler getScheduler();
	protected abstract Runnable getRunnable();
	protected abstract String getServiceId();

}
