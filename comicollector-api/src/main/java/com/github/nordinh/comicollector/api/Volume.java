package com.github.nordinh.comicollector.api;

import io.dropwizard.jackson.JsonSnakeCase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown=true)
public class Volume extends ComicVineEntry {

	private String description;
	private String name;
	private String startYear;
	private ComicVineImage image;
	private VolumePublisher publisher;

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getStartYear() {
		return startYear;
	}

	public ComicVineImage getImage() {
		return image;
	}

	public VolumePublisher getPublisher() {
		return publisher;
	}
	
}
