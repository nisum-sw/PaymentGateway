package com.sw.payment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
//@PropertySource("classpath:/configuration/application.properties")
public class PropertyConfig {
	
	
	/*@Configuration
    @PropertySource("classpath:/configuration/application.properties")
    static class Application
    { }
*/
	
	@Configuration
    @Profile("dev")
    @PropertySource("classpath:/configuration/dev.properties")
    static class Dev
    { }

    @Configuration
    @Profile("prod")
    @PropertySource("classpath:/configuration/prod.properties")
    static class Prod
    {
        // nothing needed here if you are only overriding property values
    }

	
	
	@Autowired
	public Environment env;

}
