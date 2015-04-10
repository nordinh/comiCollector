package com.github.nordinh.comicollector.comicvine.consumption;

import org.mongojack.JacksonDBCollection;

import com.mongodb.DB;

public class ComicVineConsumptionBookmarkRepository {
	
	private JacksonDBCollection<ComicVineConsumptionBookmark, String> bookmarks;
	
	public ComicVineConsumptionBookmarkRepository(DB db) {
		bookmarks = JacksonDBCollection.wrap(
				db.getCollection("comicVineBookmarks"),
				ComicVineConsumptionBookmark.class,
				String.class);
	}
	
	public ComicVineConsumptionBookmark findById(String id) {
		ComicVineConsumptionBookmark result = bookmarks.findOneById(id);
		return result != null ? result : new ComicVineConsumptionBookmark(id);
	}
	
	public void store(ComicVineConsumptionBookmark bookmark) {
		bookmarks.save(bookmark);
	}

}
