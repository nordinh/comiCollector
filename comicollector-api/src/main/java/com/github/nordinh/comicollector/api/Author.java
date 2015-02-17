package com.github.nordinh.comicollector.api;

public class Author {
	
	public enum AuthorType { WRITER, PENCILER}
	
	private String name;
	private AuthorType type;
	
	public String getName() {
		return name;
	}
	
	public AuthorType getType() {
		return type;
	}

}
