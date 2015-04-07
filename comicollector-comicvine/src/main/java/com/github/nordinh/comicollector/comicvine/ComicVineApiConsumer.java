package com.github.nordinh.comicollector.comicvine;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Link;

import com.github.nordinh.comicollector.comicvine.volume.VolumesResponse;

public class ComicVineApiConsumer {

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
					VolumesResponse response = client
						.target(Link.fromUri(volumesUri).build(apiKey))
						.request()
						.get()
						.readEntity(VolumesResponse.class);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Something went wrong. Will retry in later.");
				}
			}
		};
	}

}
