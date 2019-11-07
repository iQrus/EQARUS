package com.google.semantic.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.semantic.response.SentimentResponseBean;
import com.google.semantic.response.SentimentWrapperResponse;

public interface LanguageAnalyzeService {
	
	public SentimentWrapperResponse languageAnalysis(List<String>twitter) throws IOException;

	

}
