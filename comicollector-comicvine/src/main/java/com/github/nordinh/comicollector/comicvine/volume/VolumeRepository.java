package com.github.nordinh.comicollector.comicvine.volume;

import java.util.List;

import org.mongojack.JacksonDBCollection;

import com.github.nordinh.comicollector.api.Volume;
import com.mongodb.DB;

public class VolumeRepository {
	
	private JacksonDBCollection<Volume, Integer> volumes;

	public VolumeRepository(DB db) {
		this.volumes = JacksonDBCollection.wrap(db.getCollection("volumes"), Volume.class, Integer.class);
	}
	
	public void store(Volume volume) {
		this.volumes.save(volume);
	}
	
	public void store(List<Volume> volumes) {
		for (Volume volume : volumes) {
			store(volume);
		}
	}

}
