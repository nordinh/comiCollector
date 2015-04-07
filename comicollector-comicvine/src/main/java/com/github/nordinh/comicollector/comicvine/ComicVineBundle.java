package com.github.nordinh.comicollector.comicvine;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

public class ComicVineBundle implements ConfiguredBundle<ComicVineBundleConfiguration> {

	@Override
	public void run(ComicVineBundleConfiguration configuration, Environment environment) throws Exception {
		ComicVineConfiguration comicVineConfiguration = configuration.getComicVineConfiguration();
		
		Client client = new JerseyClientBuilder(environment)
				.using(comicVineConfiguration.getJerseyClientConfiguration())
				.build("ComicVine");
		
		ComicVineApiConsumer comicVineApiConsumer = new ComicVineApiConsumer(
				client,
				comicVineConfiguration.getVolumesURL(),
				comicVineConfiguration.getApiKey());
		environment.jersey().register(comicVineApiConsumer);
		
		ScheduledExecutorService syncVolumes = environment
				.lifecycle()
				.scheduledExecutorService("sync-comicvine-volumes", true)
				.build();
		syncVolumes.scheduleWithFixedDelay(comicVineApiConsumer.pollVolumes(), 1, 30, TimeUnit.MINUTES);
	}

	@Override
	public void initialize(Bootstrap<?> bootstrap) {
	}

}
