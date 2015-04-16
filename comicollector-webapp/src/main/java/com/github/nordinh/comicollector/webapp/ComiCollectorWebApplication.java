package com.github.nordinh.comicollector.webapp;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ComiCollectorWebApplication extends Application<ComiCollectorWebConfiguration> {
	
	public static void main(String[] args) throws Exception {
		new ComiCollectorWebApplication().run(args);
	}

	@Override
	public void run(ComiCollectorWebConfiguration configuration, Environment environment) throws Exception {
		final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

		// Configure CORS parameters
		cors.setInitParameter("allowedOrigins", "*");
		cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
		cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

		// Add URL mapping
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	}
	
	@Override
	public void initialize(Bootstrap<ComiCollectorWebConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/app", "/", "index.html"));
	}

}
