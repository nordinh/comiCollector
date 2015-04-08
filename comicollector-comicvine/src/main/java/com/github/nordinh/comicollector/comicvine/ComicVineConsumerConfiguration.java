package com.github.nordinh.comicollector.comicvine;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.commercehub.dropwizard.mongo.MongoClientFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.nordinh.dropwizard.mongo.MongoConfiguration;

public class ComicVineConsumerConfiguration extends Configuration
		implements MongoConfiguration {
	
	@Valid
    @NotNull
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();
	
	@NotNull
	private String volumesUrl;
	
	@NotNull
	private String volumesBookmarkUrl;

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
	public String getVolumesUrl() {
		return volumesUrl;
	}
	
	@JsonProperty
	public String getVolumesBookmarkUrl() {
		return volumesBookmarkUrl;
	}
	
	@JsonProperty
	public String getApiKey() {
		return apiKey;
	}

}
