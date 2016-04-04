package com.sw.payment.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

public class PaymentApplicationContextInitializer implements
		ApplicationContextInitializer<ConfigurableApplicationContext> {

	/*
	 * @Value( "${spring.active.profile}" ) private String activeProfile;
	 */
	@Override
	public void initialize(ConfigurableApplicationContext ac) {
		ConfigurableEnvironment appEnvironment = ac.getEnvironment();
		try {
			appEnvironment.getPropertySources().addFirst(
					new ResourcePropertySource(
							"classpath:configuration//application.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			appEnvironment.addActiveProfile("dev");
			e.printStackTrace();
		}
		// LOG.info("env.properties loaded");
		// appEnvironment.addActiveProfile(activeProfile);

	}
}