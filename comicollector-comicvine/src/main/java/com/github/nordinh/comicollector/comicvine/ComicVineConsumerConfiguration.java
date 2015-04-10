package com.github.nordinh.comicollector.comicvine;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.commercehub.dropwizard.mongo.MongoClientFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVineConsumptionEntryConfiguration;
import com.github.nordinh.dropwizard.mongo.MongoConfiguration;

public class ComicVineConsumerConfiguration extends Configuration
		implements MongoConfiguration {
	
	@Valid
    @NotNull
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();
	
	@NotNull
	private ComicVineConsumptionEntryConfiguration volumes;

	@NotNull
	private ComicVineConsumptionEntryConfiguration issues;

	@NotNull
	private String apiKey;
	
	@Valid
	@NotNull
	private MongoClientFactory mongo;

	@JsonProperty
	public MongoClientFactory getMongo() {
		return mongo;
	}

	@JsonProperty
	public void setMongo(MongoClientFactory mongo) {
		this.mongo = mongo;
	}
	
	public JerseyClientConfiguration getJerseyClient() {
        return jerseyClient;
    }
	
	@JsonProperty
	public ComicVineConsumptionEntryConfiguration getVolumes() {
		return volumes;
	}
	
	@JsonProperty
	public ComicVineConsumptionEntryConfiguration getIssues() {
		return issues;
	}
	
	@JsonProperty
	public String getApiKey() {
		return apiKey;
	}

}
