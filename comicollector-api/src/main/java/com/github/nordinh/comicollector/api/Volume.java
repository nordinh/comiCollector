package com.github.nordinh.comicollector.api;

import java.util.Collection;
import java.util.Collections;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Volume {

	private String _id;
	@NotBlank
	private String name;
	@NotNull
	private Collection<Publisher> publishers;
	@NotNull
	private Collection<Author> authors;

	public String getId() {
		return _id;
	}

	public String getName() {
		return name;
	}

	public Collection<Publisher> getPublishers() {
		return Collections.unmodifiableCollection(publishers);
	}

	public Collection<Author> getAuthors() {
		return Collections.unmodifiableCollection(authors);
	}

}
