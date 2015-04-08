package com.github.nordinh.comicollector.comicvine;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.net.UnknownHostException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;

import com.commercehub.dropwizard.mongo.ManagedMongoClient;
import com.github.nordinh.comicollector.comicvine.volume.VolumeRepository;
import com.github.nordinh.dropwizard.mongo.MongoBundle;
import com.github.nordinh.dropwizard.mongo.MongoHealthCheck;
import com.mongodb.DB;

public class ComicVineConsumerApplication extends Application<ComicVineConsumerConfiguration> {
	
	private MongoBundle mongoBundle;

	public static void main(String[] args) throws Exception {
		new ComicVineConsumerApplication().run(args);
	}

	@Override
	public void run(ComicVineConsumerConfiguration configuration, Environment environment) throws Exception {
		VolumeRepository volumeRepository = new VolumeRepository(mongoBundle.getDb());
		
		Client client = new JerseyClientBuilder(environment)
				.using(configuration.getJerseyClient())
				.build("ComicVine");
		
		ComicVineApiConsumer comicVineApiConsumer = new ComicVineApiConsumer(
				client,
				volumeRepository,
				configuration.getVolumesBookmarkUrl(),
				configuration.getVolumesUrl(),
				configuration.getApiKey());
		
		ScheduledExecutorService syncVolumes = environment
				.lifecycle()
				.scheduledExecutorService("sync-comicvine-volumes", true)
				.build();
		syncVolumes.scheduleWithFixedDelay(comicVineApiConsumer.pollVolumes(), 1, 30, TimeUnit.MINUTES);
	}
	
	@Override
	public void initialize(Bootstrap<ComicVineConsumerConfiguration> bootstrap) {
		mongoBundle = new MongoBundle();
		bootstrap.addBundle(mongoBundle);
	}

}
