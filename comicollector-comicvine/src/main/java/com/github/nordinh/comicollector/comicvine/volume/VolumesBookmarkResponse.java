package com.github.nordinh.comicollector.comicvine.volume;

import io.dropwizard.jackson.JsonSnakeCase;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown=true)
public class VolumesBookmarkResponse {
	
	private String error;
	private int numberOfTotalResults;
	private int statusCode;

	public String getError() {
		return error;
	}

	public int getNumberOfTotalResults() {
		return numberOfTotalResults;
	}

	public int getStatusCode() {
		return statusCode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
