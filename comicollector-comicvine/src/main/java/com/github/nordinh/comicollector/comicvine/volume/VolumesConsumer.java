package com.github.nordinh.comicollector.comicvine.volume;

import javax.ws.rs.client.Client;

import com.github.nordinh.comicollector.api.Volume;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVineApiConsumer;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVineConsumptionBookmarkRepository;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVineConsumptionEntryConfiguration;

public class VolumesConsumer extends ComicVineApiConsumer<Volume, VolumesPage, VolumeRepository> {

	public VolumesConsumer(
			Client client,
			VolumeRepository entryRepository,
			ComicVineConsumptionBookmarkRepository comicVineConsumptionBookmarkRepository,
			ComicVineConsumptionEntryConfiguration uris,
			String apiKey) {
		super(client, entryRepository, comicVineConsumptionBookmarkRepository, uris, apiKey);
	}

	@Override
	public Class<VolumesPage> getPageClass() {
		return VolumesPage.class;
	}
	
}