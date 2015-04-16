package com.github.nordinh.comicollector.comicvine.consumption;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Link;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nordinh.comicollector.api.ComicVineEntry;

public abstract class ComicVineApiConsumer<E extends ComicVineEntry, P extends ComicVinePage<E>, R extends ComicVineEntryRepository<E>> {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private Client client;
	private ComicVineConsumptionEntryConfiguration uris;
	private String apiKey;
	private R entryRepository;
	private ComicVineConsumptionBookmarkRepository comicVineConsumptionBookmarkRepository;

	public ComicVineApiConsumer(
			Client client,
			R entryRepository,
			ComicVineConsumptionBookmarkRepository comicVineConsumptionBookmarkRepository,
			ComicVineConsumptionEntryConfiguration uris,
			String apiKey) {
		
		this.client = client;
		this.entryRepository = entryRepository;
		this.comicVineConsumptionBookmarkRepository = comicVineConsumptionBookmarkRepository;
		this.uris = uris;
		this.apiKey = apiKey;
	}
	
	public abstract Class<P> getPageClass(); 
	
	public Runnable pollEntries() {
		return new Runnable() {
			
			@Override
			public void run() {
				try {
					log.info("Polling for new entries");
					ComicVineConsumptionBookmark bookmarkLatestConsumption = comicVineConsumptionBookmarkRepository.findById(entryRepository.getCollectionName());
					
					if (!bookmarkLatestConsumption.hasTaskToBeCompleted()) {
						bookmarkLatestConsumption.startNewTask(fetchMetadata(uris.getMetadata()));
					}
					
					while (bookmarkLatestConsumption.hasTaskToBeCompleted()) {
						P page = fetchPage(bookmarkLatestConsumption);
						entryRepository.store(page.getResults());
						bookmarkLatestConsumption.markPageAsProcessed(page);
						comicVineConsumptionBookmarkRepository.store(bookmarkLatestConsumption);
					}
				} catch (Exception e) {
					log.error("Failed to read " + entryRepository.getCollectionName(), e);
				}
			}
		};
	}
	
	private P fetchPage(ComicVineConsumptionBookmark bookmarkLatestConsumption) {
		int offset = bookmarkLatestConsumption.getNextPageToConsume() * bookmarkLatestConsumption.getPageSize();
		Link pageLink = Link.fromUri(uris.getPages()).build(apiKey, offset);
		
		log.info("Fetching " + pageLink.toString());
		return client
				.target(pageLink)
				.request()
				.get()
				.readEntity(getPageClass());
	}
	
	private ComicVineFeedMetadata fetchMetadata(String bookmarkUri) {
		Link bookmarkLink = Link.fromUri(bookmarkUri).build(apiKey);
		log.info("Fetching " + bookmarkLink.toString());
		
		return client
				.target(bookmarkLink)
				.request()
				.get()
				.readEntity(ComicVineFeedMetadata.class);
	}

}