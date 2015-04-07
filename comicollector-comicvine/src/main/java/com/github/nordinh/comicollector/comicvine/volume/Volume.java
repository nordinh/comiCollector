package com.github.nordinh.comicollector.comicvine.volume;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Volume {

	private LocalDateTime dateAdded;
	private LocalDateTime dateLastUpdated;
	private String description;
	private String name;
	private int startYear;
	private VolumeImage image;
	private VolumePublisher publisher;

	public LocalDateTime getDateAdded() {
		return dateAdded;
	}

	public LocalDateTime getDateLastUpdated() {
		return dateLastUpdated;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public int getStartYear() {
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
