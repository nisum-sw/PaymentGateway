package com.sw.payment.security;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@Component
@ImportResource({"classpath:/rest-security.xml"})
public class SecurityConfig {

}
