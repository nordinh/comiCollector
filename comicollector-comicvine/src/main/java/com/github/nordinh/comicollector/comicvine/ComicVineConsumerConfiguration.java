package com.github.nordinh.comicollector.comicvine;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ComicVineConsumerConfiguration extends Configuration {
	
	@Valid
    @NotNull
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();
	
	@NotNull
	private String volumesUrl;

	@NotNull
	private String apiKey;
	
	public JerseyClientConfiguration getJerseyClient() {
        return jerseyClient;
    }
	
	@JsonProperty
	public String getVolumesUrl() {
		return volumesUrl;
	}
	
	@JsonProperty
	public String getApiKey() {
		return apiKey;
	}

}
