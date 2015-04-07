package com.github.nordinh.comicollector.comicvine.volume;

import io.dropwizard.jackson.JsonSnakeCase;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown=true)
public class VolumeImage {
	
	private String thumbUrl;

	public String getThumbUrl() {
		return thumbUrl;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
