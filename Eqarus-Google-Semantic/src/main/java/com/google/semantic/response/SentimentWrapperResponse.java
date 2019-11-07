package com.google.semantic.response;

import java.util.List;
import java.util.Map;

public class SentimentWrapperResponse {
	
	private List<SentimentResponseBean> responseList;
	
	private Map<String,Integer> countMap;

	public List<SentimentResponseBean> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<SentimentResponseBean> responseList) {
		this.responseList = responseList;
	}

	public Map<String, Integer> getCountMap() {
		return countMap;
	}

	public void setCountMap(Map<String, Integer> countMap) {
		this.countMap = countMap;
	}
	
	

}
