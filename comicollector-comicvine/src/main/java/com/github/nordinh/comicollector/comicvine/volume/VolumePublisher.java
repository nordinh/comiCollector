package com.github.nordinh.comicollector.comicvine.volume;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class VolumePublisher {

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
