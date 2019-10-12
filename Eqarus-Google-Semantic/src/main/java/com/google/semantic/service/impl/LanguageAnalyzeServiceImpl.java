package com.google.semantic.service.impl;

import com.google.semantic.bean.TwitterRequestBean;
import com.google.semantic.response.SentimentResponseBean;
import com.google.semantic.service.LanguageAnalyzeService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.language.v1beta2.AnalyzeEntitySentimentResponse;
import com.google.cloud.language.v1beta2.Sentence;
import com.google.protobuf.Descriptors.FieldDescriptor;

@Service
public class LanguageAnalyzeServiceImpl implements LanguageAnalyzeService {
	
	
	@Override
	public Map<String, SentimentResponseBean> languageAnalysis(List<String> twitter) throws IOException  {
		
		final Logger logger = LoggerFactory.getLogger(this.getClass());
		char [] needToreplace = {'"','.','"'};
		
		String input=twitter.toString().replace(",", new String(needToreplace));
		input.replace('[','"').replace(']','"');
		System.out.println(input);
		 
		Map<String,SentimentResponseBean> sentimentsMap= new HashMap<>();
		
	
		 Document doc = Document.newBuilder()
		          .setContent(input).setType(Type.PLAIN_TEXT).build();
		 try (LanguageServiceClient language = LanguageServiceClient.create()) {     
		try {
            AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
            List<com.google.cloud.language.v1.Sentence> sentimentList= response.getSentencesList();
            for(com.google.cloud.language.v1.Sentence sent : sentimentList) {
            	SentimentResponseBean sentimentResponseBean= new SentimentResponseBean();
                Sentiment sentiment = sent.getSentiment();
                sentimentResponseBean.setMagnitude(sentiment.getMagnitude());
                sentimentResponseBean.setScore(sentiment.getScore());
                sentimentsMap.put(sent.getText().getContent().trim(), sentimentResponseBean);
                // System.out.printf("Text: %s%n", textLanguage);
                //System.out.printf("Sentiment:  Score : %s \n Magnitude:  %s \n Text : %s \n", sentimentsMap);
            }
        }
		 catch(Exception e) {
	    	 logger.error( e.getLocalizedMessage());
	      }	
		 }
		
	/*	try (LanguageServiceClient language = LanguageServiceClient.create()) {

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
		    	 logger.error(textLanguage+ ": " +e.getLocalizedMessage());
		      }	
		     }

		}
		
         System.out.println(sentimentsMap.size());*/
		return sentimentsMap;

	}
}
