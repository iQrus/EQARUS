package com.google.semantic.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.semantic.bean.TwitterRequestBean;
import com.google.semantic.response.SentimentResponseBean;
import com.google.semantic.response.SentimentWrapperResponse;
import com.google.semantic.service.LanguageAnalyzeService;

@RestController
public class HomeController {
	
	@Autowired
	private LanguageAnalyzeService languageAnalyzeService;
	
	@PostMapping("/home")
	public SentimentWrapperResponse getLanguageAnalysis(@RequestBody TwitterRequestBean twitterRequestBean)  {
		
		Map<String, SentimentResponseBean> semanticResponseMap=new HashMap<>();
		SentimentWrapperResponse sentimentWrapperResponse=new SentimentWrapperResponse();
		
		if(null!=twitterRequestBean)
		semanticResponseMap=languageAnalyzeService.languageAnalysis(twitterRequestBean.getTwitterDataList());
		if(null!=semanticResponseMap)
			sentimentWrapperResponse.setSentimentResponseMap(semanticResponseMap);
		return sentimentWrapperResponse;
		
		
	}

}
