package com.google.semantic.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.semantic.response.SentimentResponseBean;

public interface LanguageAnalyzeService {
	
	public Map<String,SentimentResponseBean> languageAnalysis(List<String>twitter) throws IOException;

	

}
