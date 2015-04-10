package com.github.nordinh.comicollector.comicvine.volume;

import com.github.nordinh.comicollector.api.Volume;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVineEntryRepository;
import com.mongodb.DB;

public class VolumeRepository extends ComicVineEntryRepository<Volume> {
	
	public VolumeRepository(DB db) {
		super(db);
	}

	@Override
	public String getCollectionName() {
		return "volumes";
	}

	@Override
	public Class<Volume> getEntryClass() {
		return Volume.class;
	}
	
}
