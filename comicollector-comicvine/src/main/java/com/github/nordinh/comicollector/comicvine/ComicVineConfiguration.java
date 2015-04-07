package com.github.nordinh.comicollector.comicvine;

import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ComicVineConfiguration {
	
	@Valid
    @NotNull
    private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();
	
	@NotNull
	private String volumesUrl;

	@NotNull
	private String apiKey;
	
	public JerseyClientConfiguration getJerseyClientConfiguration() {
        return httpClient;
    }
	
	public String getVolumesURL() {
		return volumesUrl;
	}
	
	public String getApiKey() {
		return apiKey;
	}

}
