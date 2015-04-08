package com.github.nordinh.comicollector.comicvine.volume;

import io.dropwizard.jackson.JsonSnakeCase;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.nordinh.comicollector.api.Volume;

@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown=true)
public class VolumesResponse {
	
	private String error;
	private int offset;
	private int numberOfPageResults;
	private int numberOfTotalResults;
	private int statusCode;
	private List<Volume> results;

	public String getError() {
		return error;
	}

	public int getOffset() {
		return offset;
	}

	public int getNumberOfPageResults() {
		return numberOfPageResults;
	}

	public int getNumberOfTotalResults() {
		return numberOfTotalResults;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public List<Volume> getResults() {
		return results;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
