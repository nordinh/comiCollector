package com.github.nordinh.comicollector.comicvine.volume;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class VolumeImage {
	
	private String thumbUrl;

	public String getThumbUrl() {
		return thumbUrl;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
