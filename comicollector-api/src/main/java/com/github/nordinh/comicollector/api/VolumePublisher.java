package com.github.nordinh.comicollector.api;

import io.dropwizard.jackson.JsonSnakeCase;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown=true)
public class VolumePublisher {

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
