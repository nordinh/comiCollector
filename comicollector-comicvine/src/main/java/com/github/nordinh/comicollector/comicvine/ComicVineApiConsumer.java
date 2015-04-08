package com.github.nordinh.comicollector.comicvine;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Link;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nordinh.comicollector.comicvine.volume.Volume;
import com.github.nordinh.comicollector.comicvine.volume.VolumeRepository;
import com.github.nordinh.comicollector.comicvine.volume.VolumesBookmarkResponse;
import com.github.nordinh.comicollector.comicvine.volume.VolumesResponse;

public class ComicVineApiConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(ComicVineApiConsumer.class);

	private Client client;
	private String volumesUri;
	private String volumesBookmarkUri;
	private String apiKey;
	private LocalDateTime currentDateBookmark = LocalDateTime.of(1900, 1, 1, 0, 0);
	private VolumeRepository volumeRepository;

	public ComicVineApiConsumer(Client client, VolumeRepository volumeRepository, String volumesBookmarkUri, String volumesUri, String apiKey) {
		this.client = client;
		this.volumeRepository = volumeRepository;
		this.volumesUri = volumesUri;
		this.volumesBookmarkUri = volumesBookmarkUri;
		this.apiKey = apiKey;
	}
	
	public Runnable pollVolumes() {
		return new Runnable() {
			
			@Override
			public void run() {
				try {
					Link bookmarkLink = Link.fromUri(volumesBookmarkUri).build(apiKey);
					log.info("Fetching " + bookmarkLink.toString());
					VolumesBookmarkResponse bookmarkResponse = client
						.target(bookmarkLink)
						.request()
						.get()
						.readEntity(VolumesBookmarkResponse.class);
					
					for (int i = bookmarkResponse.getNumberOfTotalResults(); i >= 0; i = i - 100 ) {
						Link volumesLink = Link.fromUri(volumesUri).build(apiKey, Math.max(0, i - 100));
						log.info("Fetching " + volumesLink.toString());
						VolumesResponse volumesResponse = client
								.target(volumesLink)
								.request()
								.get()
								.readEntity(VolumesResponse.class);
						volumeRepository.store(volumesResponse.getResults());
					}
				} catch (Exception e) {
					log.error("Failed to read volumes", e);
				}
			}
		};
	}

}