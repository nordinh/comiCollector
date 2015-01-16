package com.github.nordinh.comicollector;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.github.nordinh.comicollector.health.TemplateHealthCheck;
import com.github.nordinh.comicollector.resource.HelloWorldResource;

public class ComiCollectorApplication extends
		Application<ComiCollectorConfiguration> {

	public static void main(String[] args) throws Exception {
		new ComiCollectorApplication().run(args);
	}

	@Override
	public String getName() {
		return "comiCollector";
	}

	@Override
	public void initialize(Bootstrap<ComiCollectorConfiguration> bootstrap) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run(ComiCollectorConfiguration configuration,
			Environment environment) throws Exception {
		final HelloWorldResource resource = new HelloWorldResource(
				configuration.getTemplate(), configuration.getDefaultName());
		final TemplateHealthCheck healthCheck =
		        new TemplateHealthCheck(configuration.getTemplate());
		    environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
	}

}
