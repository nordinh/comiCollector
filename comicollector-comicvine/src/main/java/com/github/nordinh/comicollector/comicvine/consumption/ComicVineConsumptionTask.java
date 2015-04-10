package com.github.nordinh.comicollector.comicvine.consumption;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"finished"})
public class ComicVineConsumptionTask {
	
	public static final int DEFAULT_PAGE_SIZE = 100;

	private enum Status { BUSY, FINISHED}
	
	private Status status = Status.FINISHED;
	private int page;
	private int pageSize = DEFAULT_PAGE_SIZE;
	private Date fromBookmark;
	private Date toBookmark;
	
	@SuppressWarnings("unused")
	private ComicVineConsumptionTask() {}
	
	public ComicVineConsumptionTask(int page, Date fromBookmark, Date toBookmark) {
		this.page = page;
		this.fromBookmark = fromBookmark;
		this.toBookmark = toBookmark;
		this.status = Status.BUSY;
	}
	
	public int getPage() {
		return page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public Date getFromBookmark() {
		return fromBookmark;
	}
	
	public Date getToBookmark() {
		return toBookmark;
	}
	
	public void updateOffset(int offset) {
		this.page = offset;
	}
	
	public boolean isFinished() {
		return status == Status.FINISHED;
	}
	
	public void markAsFinished() {
		status = Status.FINISHED;
	}
	
	public void markPageAsProcessed() {
		if (page > 0)
			page--;
	}
	
	public boolean isToBeProcessed(Date dateTime) {
		return !fromBookmark.after(dateTime);
	}
	
	public Status getStatus() {
		return status;
	}

}
