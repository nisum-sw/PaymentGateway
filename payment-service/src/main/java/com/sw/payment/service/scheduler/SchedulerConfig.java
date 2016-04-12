package com.sw.payment.service.scheduler;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@Component
@ImportResource({"classpath:/rest-scheduler.xml"})
public class SchedulerConfig {

}
