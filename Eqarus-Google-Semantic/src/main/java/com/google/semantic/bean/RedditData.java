package com.google.semantic.bean;

public class RedditData {
	
	private int key;

	private String subreddit;
	private String selftext;
	private String title;
	public String getSubreddit() {
		return subreddit;
	}
	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}
	public String getSelftext() {
		return selftext;
	}
	public void setSelftext(String selftext) {
		this.selftext = selftext;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
	
	
}
