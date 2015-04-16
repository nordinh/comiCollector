package com.github.nordinh.comicollector.comicvine;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVineConsumptionBookmarkRepository;
import com.github.nordinh.comicollector.comicvine.issue.IssueRepository;
import com.github.nordinh.comicollector.comicvine.issue.IssuesConsumer;
import com.github.nordinh.comicollector.comicvine.volume.VolumeRepository;
import com.github.nordinh.comicollector.comicvine.volume.VolumesConsumer;
import com.github.nordinh.dropwizard.mongo.MongoBundle;

public class ComicVineConsumerApplication extends Application<ComicVineConsumerConfiguration> {
	
	private MongoBundle mongoBundle;

	public static void main(String[] args) throws Exception {
		new ComicVineConsumerApplication().run(args);
	}

	@Override
	public void run(ComicVineConsumerConfiguration configuration, Environment environment) throws Exception {
		ComicVineConsumptionBookmarkRepository comicVineConsumptionBookmarkRepository = new ComicVineConsumptionBookmarkRepository(mongoBundle.getDb());
		
		Client client = new JerseyClientBuilder(environment)
				.using(configuration.getJerseyClient())
				.build("ComicVine");
		
		VolumeRepository volumeRepository = new VolumeRepository(mongoBundle.getDb());
		VolumesConsumer comicVineApiConsumer = new VolumesConsumer(
				client,
				volumeRepository,
				comicVineConsumptionBookmarkRepository,
				configuration.getVolumes(),
				configuration.getApiKey());
		
		ScheduledExecutorService syncVolumes = environment
				.lifecycle()
				.scheduledExecutorService("sync-comicvine-volumes", true)
				.build();
		syncVolumes.scheduleWithFixedDelay(comicVineApiConsumer.pollEntries(), 0, 30, TimeUnit.MINUTES);
		
		IssueRepository issueRepository = new IssueRepository(mongoBundle.getDb());
		IssuesConsumer issueConsumer = new IssuesConsumer(
				client,
				issueRepository,
				comicVineConsumptionBookmarkRepository,
				configuration.getIssues(),
				configuration.getApiKey());
		
		ScheduledExecutorService syncIssues = environment
				.lifecycle()
				.scheduledExecutorService("sync-comicvine-issues", true)
				.build();
		syncIssues.scheduleWithFixedDelay(issueConsumer.pollEntries(), 0, 30, TimeUnit.MINUTES);
	}
	
	@Override
	public void initialize(Bootstrap<ComicVineConsumerConfiguration> bootstrap) {
		bootstrap.getObjectMapper().registerModule(getIgnoreWrongDateFormatModule());
		mongoBundle = new MongoBundle();
		bootstrap.addBundle(mongoBundle);
	}

	@SuppressWarnings("serial")
	private SimpleModule getIgnoreWrongDateFormatModule() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Date.class, new DateDeserializers.DateDeserializer() {
			@Override
			public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
				try {
					return super.deserialize(jp, ctxt);
				} catch (Exception e) {
					return null;
				}
			}
		});
		return module;
	}

}
