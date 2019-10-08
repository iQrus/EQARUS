package com.google.semantic.service.impl;

import com.google.semantic.response.SentimentResponseBean;
import com.google.semantic.service.LanguageAnalyzeService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;

@Service
public class LanguageAnalyzeServiceImpl implements LanguageAnalyzeService {
	
	
	@Override
	public Map<String, SentimentResponseBean> languageAnalysis(List<String> twitter)  {
		
		Map<String,SentimentResponseBean> sentimentsMap= new HashMap<>();
		
		
		try (LanguageServiceClient language = LanguageServiceClient.create()) {

		      // The text to analyze
		     for(String textLanguage : twitter) {
		    	 System.out.printf("Text: %s%n", textLanguage);
		    	 SentimentResponseBean sentimentBean=new SentimentResponseBean();
		     Document doc = Document.newBuilder()
		          .setContent(textLanguage).setType(Type.PLAIN_TEXT).build();
		     
		      // Detects the sentiment of the text
		     try {
		    	 Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();
		    	 sentimentBean.setMagnitude(sentiment.getMagnitude());
		    	 sentimentBean.setScore(sentiment.getScore());
		    	 sentimentsMap.put(textLanguage, sentimentBean);
		    	 // System.out.printf("Text: %s%n", textLanguage);
		    	 System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());

		     }

		     catch(Exception e) {
		    	 System.out.println(e.getLocalizedMessage());
		     }	
		     }

		}
		catch(IOException e) {
			System.out.println(e.getLocalizedMessage());
		}	
   System.out.println(sentimentsMap.size());
		return sentimentsMap;

	}
}
