package com.github.nordinh.comicollector.api;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import io.dropwizard.jackson.JsonSnakeCase;

import java.util.Date;

import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonSnakeCase
public class ComicVineEntry {

	@Id
	private Long id;
	@JsonFormat(shape = STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date dateAdded;
	@JsonFormat(shape = STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date dateLastUpdated;

	public ComicVineEntry() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}