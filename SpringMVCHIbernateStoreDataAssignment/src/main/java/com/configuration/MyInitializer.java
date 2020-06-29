package com.configuration;

import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class MyInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MyConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		 return new String[] { "/" };
	}

	
	
	
	
	
/*	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext ctx1 = new AnnotationConfigWebApplicationContext();
		ctx1.register(MyConfiguration.class);
		ctx1.setServletContext(container);

		ServletRegistration.Dynamic servlet = container.addServlet(
				"dispatcher", new DispatcherServlet(ctx1));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
*/
}
