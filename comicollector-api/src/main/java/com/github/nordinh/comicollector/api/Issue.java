package com.github.nordinh.comicollector.api;

import java.util.Collection;
import java.util.Collections;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Issue {
	
	private String _id;
	
	@NotBlank
	private String name;
	@Min(0)
	private int issueNo;
	@NotNull
	private String volumeId;
	@NotNull
	private Collection<Author> authors;
	
	public String getId() {
		return _id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getIssueNo() {
		return issueNo;
	}
	
	public String getVolumeId() {
		return volumeId;
	}
	
	public Collection<Author> getAuthors() {
		return Collections.unmodifiableCollection(authors);
	}

}
