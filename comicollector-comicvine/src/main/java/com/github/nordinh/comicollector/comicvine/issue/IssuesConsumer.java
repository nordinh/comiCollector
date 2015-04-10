package com.github.nordinh.comicollector.comicvine.issue;

import javax.ws.rs.client.Client;

import com.github.nordinh.comicollector.api.Issue;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVineApiConsumer;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVineConsumptionBookmarkRepository;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVineConsumptionEntryConfiguration;

public class IssuesConsumer extends ComicVineApiConsumer<Issue, IssuePage, IssueRepository> {

	public IssuesConsumer(
			Client client,
			IssueRepository entryRepository,
			ComicVineConsumptionBookmarkRepository comicVineConsumptionBookmarkRepository,
			ComicVineConsumptionEntryConfiguration uris,
			String apiKey) {
		super(client, entryRepository, comicVineConsumptionBookmarkRepository, uris, apiKey);
	}

	@Override
	public Class<IssuePage> getPageClass() {
		return IssuePage.class;
	}

}
