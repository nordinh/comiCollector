package com.github.nordinh.comicollector.comicvine.issue;

import com.github.nordinh.comicollector.api.Issue;
import com.github.nordinh.comicollector.comicvine.consumption.ComicVineEntryRepository;
import com.mongodb.DB;

public class IssueRepository extends ComicVineEntryRepository<Issue> {

	public IssueRepository(DB db) {
		super(db);
	}

	@Override
	public String getCollectionName() {
		return "issues";
	}

	@Override
	public Class<Issue> getEntryClass() {
		return Issue.class;
	}

}
