package com.google.semantic.bean;

import java.util.List;



public class TwitterRequestBean {
	
	private List <String> twitterDataList;
    
	//@JsonProperty("twitterDataList")
	public List<String> getTwitterDataList() {
		return twitterDataList;
	}

	public void setTwitterDataList(List<String> twitterDataList) {
		this.twitterDataList = twitterDataList;
	}

}
