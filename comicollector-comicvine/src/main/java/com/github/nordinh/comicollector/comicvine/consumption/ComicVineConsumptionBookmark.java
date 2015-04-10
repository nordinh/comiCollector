package com.github.nordinh.comicollector.comicvine.consumption;

import static com.github.nordinh.comicollector.comicvine.consumption.ComicVineConsumptionTask.DEFAULT_PAGE_SIZE;

import java.util.Date;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"nextPageToConsume","toBeProcessed","pageSize"})
public class ComicVineConsumptionBookmark {
	
	@Id
	@JsonProperty
	private String collection;
	@JsonProperty
	private ComicVineConsumptionTask task;
	@JsonProperty
	private Date latestSuccessfulConsumptionBookmark = new Date(0);
	
	@SuppressWarnings("unused")
	private ComicVineConsumptionBookmark() {}
	
	public ComicVineConsumptionBookmark(String collection) {
		this.collection = collection;
	}
	
	public boolean hasTaskToBeCompleted() {
		return task != null && !task.isFinished();
	}
	
	public void markPageAsProcessed(ComicVinePage<?> page) {
		task.markPageAsProcessed();
		if (task.getPage() == 0 || !isToBeProcessed(page.getDateLastModified())) {
			markTaskAsFinished();
		}
	}
	
	public void markTaskAsFinished() {
		task.markAsFinished();
		latestSuccessfulConsumptionBookmark = task.getToBookmark();
	}
	
	public int getNextPageToConsume() {
		return task.getPage();
	}
	
	public void startNewTask(ComicVineFeedMetadata metadata) {
		if (metadata.getNumberOfTotalResults() == 0) return;
		
		this.task = new ComicVineConsumptionTask(
				(metadata.getNumberOfTotalResults() - 1) / DEFAULT_PAGE_SIZE,
				latestSuccessfulConsumptionBookmark,
				metadata.getDateLastUpdated());
	}
	
	public boolean isToBeProcessed(Date dateTime) {
		return task.isToBeProcessed(dateTime);
	}

	public int getPageSize() {
		return task.getPageSize();
	}
	
}
