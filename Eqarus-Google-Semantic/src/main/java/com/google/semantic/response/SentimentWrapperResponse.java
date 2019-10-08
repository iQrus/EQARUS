package com.google.semantic.response;

import java.util.Map;

public class SentimentWrapperResponse {
	
	private Map<String, SentimentResponseBean> sentimentResponseMap;
    public Map<String, SentimentResponseBean> getSentimentResponseMap() {
        return sentimentResponseMap;
    }
    public void setSentimentResponseMap(Map<String, SentimentResponseBean> sentimentResponseMap) {
        this.sentimentResponseMap = sentimentResponseMap;
    }
    

}
