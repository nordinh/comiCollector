package com.github.nordinh.comicollector.comicvine.consumption;

import java.util.List;

import org.mongojack.JacksonDBCollection;

import com.github.nordinh.comicollector.api.ComicVineEntry;
import com.mongodb.DB;

public abstract class ComicVineEntryRepository<T extends ComicVineEntry> {
	
	private JacksonDBCollection<T, Integer> entries;

	public ComicVineEntryRepository(DB db) {
		this.entries = JacksonDBCollection.wrap(
				db.getCollection(getCollectionName()), 
				getEntryClass(),
				Integer.class);
	}
	
	public abstract String getCollectionName();
	
	public abstract Class<T> getEntryClass();
	
	public void store(T entry) {
		this.entries.save(entry);
	}
	
	public void store(List<T> entries) {
		for (T entry : entries) {
			store(entry);
		}
	}

}
