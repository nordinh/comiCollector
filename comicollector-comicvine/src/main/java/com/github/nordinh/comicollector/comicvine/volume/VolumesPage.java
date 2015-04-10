package com.github.nordinh.comicollector.comicvine.volume;

import io.dropwizard.jackson.JsonSnakeCase;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.nordinh.comicollector.api.Volume;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVinePage;

@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown=true)
public class VolumesPage extends ComicVinePage<Volume> {
	
	private List<Volume> results;

	@Override
	public List<Volume> getResults() {
		return results;
	}

}
