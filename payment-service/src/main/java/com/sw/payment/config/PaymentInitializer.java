package com.sw.payment.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.sw.payment.security.SecurityConfig;

public class PaymentInitializer/* implements WebApplicationInitializer*/{

	//@Override
	public void onStartup(ServletContext paramServletContext)
			throws ServletException {

		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(PropertyConfig.class);
		rootContext.register(SecurityConfig.class);
		//rootContext.setConfigLocation("com.sw.payment.security");
		
		
     // Manage the lifecycle of the root application context
		paramServletContext.addListener(new ContextLoaderListener(rootContext));
		
		paramServletContext.setInitParameter("contextInitializerClasses", "com.sw.payment.config.PaymentApplicationContextInitializer");
		//AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		//webContext.register(MvcConfiguration.class);
		
		//Set Spring Security Filter
/*		paramServletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
        .addMappingForUrlPatterns(null, false, "/*");*/
        
		  DispatcherServlet dispatcherServlet = new DispatcherServlet();
		  ServletRegistration.Dynamic dispatcher = paramServletContext.addServlet("payment", dispatcherServlet);
		  dispatcher.setLoadOnStartup(1);
		  dispatcher.addMapping("/*");
		 }
		
		
		
		
        //ctx.setServletContext(paramServletContext);
		
		
		/*ServletRegistration.Dynamic registration = paramServletContext
				.addServlet("payment", new DispatcherServlet());
*/
/*		registration.setLoadOnStartup(1);
		registration.addMapping("/*");
*/		
//	}
	

}

