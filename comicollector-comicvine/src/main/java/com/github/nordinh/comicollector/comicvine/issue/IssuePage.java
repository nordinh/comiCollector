package com.github.nordinh.comicollector.comicvine.issue;

import io.dropwizard.jackson.JsonSnakeCase;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.nordinh.comicollector.api.Issue;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVinePage;

@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown=true)
public class IssuePage extends ComicVinePage<Issue> {

	private List<Issue> results;

	@Override
	public List<Issue> getResults() {
		return results;
	}

}
