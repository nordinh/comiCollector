package com.github.nordinh.comicollector.comicvine;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Link;

public class ComicVineApiConsumer {

	private static final long FREQUENCY = Duration.ofSeconds(15).toMillis();
	private static final long DELAY = Duration.ofSeconds(0).toMillis();
	private Timer timer;
	private Client client;

	public ComicVineApiConsumer() {
		client = ClientBuilder.newClient();
		timer = new Timer(true);
	}

	public void start() {
		timer.schedule(pollVolumes(), DELAY, FREQUENCY);
	}

	private TimerTask pollVolumes() {
		return new TimerTask() {

			@Override
			public void run() {
				try {
					Volumes volumes = client
						.target(Link.fromUri("").build())
						.request()
						.get()
						.readEntity(Volumes.class);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Something went wrong. Will retry in " + FREQUENCY);
				}
			}
		};
	}

}
