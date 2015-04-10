package com.github.nordinh.comicollector.comicvine.consumption;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import io.dropwizard.jackson.JsonSnakeCase;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown=true)
public class ComicVineFeedMetadata {

	private String error;
	private int numberOfTotalResults;
	private int statusCode;
	private List<BookmarkDate> results;

	public String getError() {
		return error;
	}

	public int getNumberOfTotalResults() {
		return numberOfTotalResults;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
	public List<BookmarkDate> getResults() {
		return results;
	}
	
	public Date getDateLastUpdated() {
		return results.get(0).getDateLastUpdated();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@JsonSnakeCase
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class BookmarkDate {
		
		@JsonFormat(shape=STRING, pattern="yyyy-MM-dd hh:mm:ss")
		private Date dateLastUpdated;
		
		public Date getDateLastUpdated() {
			return dateLastUpdated;
		}
		
		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
	}
}
