package com.github.nordinh.comicollector.comicvine;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

public class ComicVineConsumerApplication extends Application<ComicVineConsumerConfiguration> {
	
	public static void main(String[] args) throws Exception {
		new ComicVineConsumerApplication().run(args);
	}

	@Override
	public void run(ComicVineConsumerConfiguration configuration, Environment environment) throws Exception {
		Client client = new JerseyClientBuilder(environment)
				.using(configuration.getJerseyClient())
				.build("ComicVine");
		
		ComicVineApiConsumer comicVineApiConsumer = new ComicVineApiConsumer(
				client,
				configuration.getVolumesUrl(),
				configuration.getApiKey());
		
		ScheduledExecutorService syncVolumes = environment
				.lifecycle()
				.scheduledExecutorService("sync-comicvine-volumes", true)
				.build();
		syncVolumes.scheduleWithFixedDelay(comicVineApiConsumer.pollVolumes(), 0, 30, TimeUnit.MINUTES);
	}

}
