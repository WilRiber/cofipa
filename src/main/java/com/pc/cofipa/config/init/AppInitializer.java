package com.pc.cofipa.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.pc.cofipa.config.JPAConfig;
import com.pc.cofipa.config.SecurityConfig;
import com.pc.cofipa.config.ServiceConfig;
import com.pc.cofipa.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
    protected Filter[] getServletFilters(){
    	return new Filter[] { };
    	
		
	}
	
   @Override
   protected void customizeRegistration(Dynamic registration) {
      registration.setMultipartConfig(new MultipartConfigElement(""));

}
		
	

}
