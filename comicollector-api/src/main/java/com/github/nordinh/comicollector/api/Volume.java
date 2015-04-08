package com.github.nordinh.comicollector.api;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import io.dropwizard.jackson.JsonSnakeCase;

import java.util.Date;

import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown=true)
public class Volume {

	@Id
	private Long id;
	@JsonFormat(shape=STRING, pattern="yyyy-MM-dd hh:mm:ss")
	private Date dateAdded;
	@JsonFormat(shape=STRING, pattern="yyyy-MM-dd hh:mm:ss")
	private Date dateLastUpdated;
	private String description;
	private String name;
	private String startYear;
	private VolumeImage image;
	private VolumePublisher publisher;

	public Long getId() {
		return id;
	}
	
	public Date getDateAdded() {
		return dateAdded;
	}

	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getStartYear() {
		return startYear;
	}

	public VolumeImage getImage() {
		return image;
	}

	public VolumePublisher getPublisher() {
		return publisher;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
