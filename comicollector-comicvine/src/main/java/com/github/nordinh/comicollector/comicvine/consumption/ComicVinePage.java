package com.github.nordinh.comicollector.comicvine.consumption;

import io.dropwizard.jackson.JsonSnakeCase;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.github.nordinh.comicollector.api.ComicVineEntry;
import com.google.common.collect.Iterables;

@JsonSnakeCase
public abstract class ComicVinePage<T extends ComicVineEntry> {

	private String error;
	private int offset;
	private int numberOfPageResults;
	private int numberOfTotalResults;
	private int statusCode;

	public ComicVinePage() {
		super();
	}

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

	public abstract List<T> getResults();

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Date getDateLastModified() {
		return Iterables.getLast(getResults()).getDateLastUpdated();
	}

}