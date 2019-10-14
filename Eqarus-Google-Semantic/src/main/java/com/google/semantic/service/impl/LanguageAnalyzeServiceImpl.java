package com.google.semantic.service.impl;


import com.google.semantic.constants.GoogleSemanticConstants;
import com.google.semantic.response.SentimentResponseBean;
import com.google.semantic.service.LanguageAnalyzeService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;


@Service
public class LanguageAnalyzeServiceImpl implements LanguageAnalyzeService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Map<String, SentimentResponseBean> languageAnalysis(List<String> twitter) throws IOException  {

		char [] needToreplace = {'"','.','"'};
		String input=twitter.toString().replace(",", new String(needToreplace));
		logger.info(input);
		Map<String,SentimentResponseBean> sentimentsMap= new HashMap<>();
		Document doc = Document.newBuilder()
				.setContent(input).setType(Type.PLAIN_TEXT).build();
		logger.info(GoogleSemanticConstants.GOOGLE_DOCUMENT_PROCESS_SUCCESSFULLY);
		try (LanguageServiceClient language = LanguageServiceClient.create()) {     
			try {
				AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
				logger.info(GoogleSemanticConstants.GOOGLE_ANALYZESENTIMENT_RESPONSE_SUCCESSFULLY);
				List<com.google.cloud.language.v1.Sentence> sentimentList= response.getSentencesList();
				for(com.google.cloud.language.v1.Sentence sent : sentimentList) {
					SentimentResponseBean sentimentResponseBean= new SentimentResponseBean();
					Sentiment sentiment = sent.getSentiment();
					sentimentResponseBean.setMagnitude(sentiment.getMagnitude());
					sentimentResponseBean.setScore(sentiment.getScore());
					String key=sent.getText().getContent().replaceAll("[^a-zA-Z0-9]", " ").trim();
					sentimentsMap.put(key, sentimentResponseBean);
				}
			}
			catch(Exception e) {
				logger.error( e.getLocalizedMessage());
			}	
		}


		return sentimentsMap;

	}
}
