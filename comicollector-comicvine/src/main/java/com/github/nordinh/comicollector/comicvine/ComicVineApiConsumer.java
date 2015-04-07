package com.github.nordinh.comicollector.comicvine;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Link;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nordinh.comicollector.comicvine.volume.VolumesResponse;

public class ComicVineApiConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(ComicVineApiConsumer.class);

	private Client client;
	private String volumesUri;
	private String apiKey;

	public ComicVineApiConsumer(Client client, String volumesUri, String apiKey) {
		this.client = client;
		this.volumesUri = volumesUri;
		this.apiKey = apiKey;
	}
	
	public Runnable pollVolumes() {
		return new Runnable() {
			
			@Override
			public void run() {
				try {
					Link link = Link.fromUri(volumesUri).build(apiKey);
					log.info("Calling " + link.toString());
					VolumesResponse response = client
						.target(link)
						.request()
						.get()
						.readEntity(VolumesResponse.class);
					log.debug(response.toString());
				} catch (Exception e) {
					log.error("Failed to read volumes", e);
				}
			}
		};
	}

}
