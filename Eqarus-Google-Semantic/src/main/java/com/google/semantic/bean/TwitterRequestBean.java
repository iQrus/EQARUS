package com.google.semantic.bean;

import java.util.LinkedHashMap;
import java.util.List;




public class TwitterRequestBean {
	
	private List<String> twitterDataList;
	private LinkedHashMap<String, String> tweetLocationList;
	private LinkedHashMap<String, RedditData> redditDataList;
	private LinkedHashMap<String, String> sentitmentList;

	public List<String> getTwitterDataList() {
		return twitterDataList;
	}

	public void setTwitterDataList(List<String> twitterDataList) {
		this.twitterDataList = twitterDataList;
	}

	public LinkedHashMap<String, String> getTweetLocationList() {
		return tweetLocationList;
	}

	public void setTweetLocationList(LinkedHashMap<String, String> tweetLocationList) {
		this.tweetLocationList = tweetLocationList;
	}

	public LinkedHashMap<String, RedditData> getRedditDataList() {
		return redditDataList;
	}

	public void setRedditDataList(LinkedHashMap<String, RedditData> redditDataList) {
		this.redditDataList = redditDataList;
	}

	public LinkedHashMap<String, String> getSentitmentList() {
		return sentitmentList;
	}

	public void setSentitmentList(LinkedHashMap<String, String> sentitmentList) {
		this.sentitmentList = sentitmentList;
	}

}
