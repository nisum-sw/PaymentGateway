package com.sw.payment.service.scheduler;

import javax.annotation.PostConstruct;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;


@Component
public class SwScheduler extends AbstractCronScheduler implements ISchedulerService {


	@Autowired
    @Qualifier("schedulerPoller")
    @Setter
    @Getter(value=AccessLevel.PROTECTED)
    private Runnable runnable;

    @Value("${scheduler.polling.autoStartup}")
    @Getter(value = AccessLevel.PROTECTED)
    private boolean autoStartup;

    @Autowired
	@Qualifier ("SwTaskScheduler")
	@Setter
    @Getter(value=AccessLevel.PROTECTED)
	private TaskScheduler scheduler;

	@Autowired
	public SwScheduler(@Value("${scheduler.polling.interval}") String cronExpression) {
		super(cronExpression);
	}

    @PostConstruct
    public void init() {
        if (autoStartup){
            this.startScheduler();
        }
    }

    @Override
    public String getServiceId() {
        return "Invoice Status daemon";
    }

	@Override
	public void changeRunningIntervalOfScheduler(String cronExpression) throws Exception {
		// TODO Auto-generated method stub
		
	}

}