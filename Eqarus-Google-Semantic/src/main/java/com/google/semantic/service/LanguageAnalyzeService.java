package com.google.semantic.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.semantic.bean.RedditTokenBean;
import com.google.semantic.bean.TwitterRequestBean;
import com.google.semantic.response.SentimentResponseBean;
import com.google.semantic.response.SentimentWrapperResponse;

public interface LanguageAnalyzeService {
	
	public SentimentWrapperResponse languageAnalysis(TwitterRequestBean twitterRequestBean) throws IOException;
	

}
