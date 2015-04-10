package com.github.nordinh.comicollector.api;

import io.dropwizard.jackson.JsonSnakeCase;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown=true)
public class Issue extends ComicVineEntry {
	
	private String description;
	private String name;
	private String issueNumber;
	private ComicVineImage image;
	private Date storeDate;
	private Long Volume;

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public ComicVineImage getImage() {
		return image;
	}

	public Date getStoreDate() {
		return storeDate;
	}

	public Long getVolume() {
		return Volume;
	}

}
