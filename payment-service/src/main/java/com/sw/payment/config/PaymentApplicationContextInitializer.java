package com.sw.payment.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

public class PaymentApplicationContextInitializer implements
		ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext ac) {
		ConfigurableEnvironment appEnvironment = ac.getEnvironment();
		try {
			appEnvironment.getPropertySources().addFirst(
					new ResourcePropertySource(
							"classpath:configuration//application.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			appEnvironment.addActiveProfile("dev"); //The world is illusion my friend 
			e.printStackTrace();
		}
	}
}