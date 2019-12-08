package com.google.semantic.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.google.semantic.bean.TwitterRequestBean;
import com.google.semantic.response.SentimentResponseBean;
import com.google.semantic.response.SentimentWrapperResponse;
import com.google.semantic.service.LanguageAnalyzeService;

@Service
public class LanguageAnalyzeServiceImpl implements LanguageAnalyzeService {
	
	private static final String VPOS = "Very positive";
	private static final String POS = "Positive";
	private static final String NEUT = "Neutral";
	private static final String NEG = "Negative";
	private static final String VNEG = "Very negative";
	
	private static LinkedHashMap<String,Integer> scoreCountMap = null;
	private static Integer[] countArr = null;
	
	@Override
	public SentimentWrapperResponse languageAnalysis(TwitterRequestBean twitterRequestBean) throws IOException {

		final Logger logger = LoggerFactory.getLogger(this.getClass());
		StringBuilder input = new StringBuilder();
		SentimentWrapperResponse response = new SentimentWrapperResponse();
		List<String> twitter = twitterRequestBean.getTwitterDataList();
		LinkedHashMap<String, String> tweetLocationList = twitterRequestBean.getTweetLocationList();
		logger.info("tweetLocationList"+tweetLocationList.toString());
		LinkedHashMap<String, String> newTweetLocationList = new LinkedHashMap<String, String>();
		String temp = "";
		if(twitter != null) {
			for(String tweet : twitter) {
				temp = tweet.replaceAll("(@[A-Za-z0-9]+)|([^0-9A-Za-z \\t])|(\\w+:\\/\\/\\S+)", " ").trim();	
				input.append(temp);
				input.append(". ");
				if(tweetLocationList != null && tweetLocationList.containsKey(tweet)) {
					newTweetLocationList.put(temp, tweetLocationList.get(tweet));
				}
			}
		}else {
			logger.info("List of tweets is null");
		}
		List<SentimentResponseBean> responseList = new ArrayList<>();
logger.info("newTweetLocationList"+newTweetLocationList);
		Document doc = Document.newBuilder().setContent(input.toString()).setType(Type.PLAIN_TEXT).build();
		scoreCountMap = new LinkedHashMap<String,Integer>();
		scoreCountMap.put(VPOS, 0);
		scoreCountMap.put(POS, 0);
		scoreCountMap.put(NEUT, 0);
		scoreCountMap.put(NEG, 0);
		scoreCountMap.put(VNEG, 0);
		countArr = new Integer[]{0,0,0,0,0};
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			try {
				AnalyzeSentimentResponse resp = language.analyzeSentiment(doc);
				List<com.google.cloud.language.v1.Sentence> sentimentList = resp.getSentencesList();
				for (com.google.cloud.language.v1.Sentence sent : sentimentList) {
					SentimentResponseBean sentimentResponseBean = new SentimentResponseBean();
					Sentiment sentiment = sent.getSentiment();
					sentimentResponseBean.setMagnitude(sentiment.getMagnitude());
					sentimentResponseBean.setScore(sentiment.getScore());
					sentimentResponseBean.setText(sent.getText().getContent());
					sentimentResponseBean.setCategory(this.getCategory(sentiment.getScore()));
					sentimentResponseBean.setSentimentScore(fetchCat(sentimentResponseBean.getCategory()));
					 if(newTweetLocationList.get(sent.getText().getContent().substring(0,sent.getText().getContent().length()-1)) != null) {
					  logger.info(this.getCategory(sentiment.getScore())+"--"+fetchCat(sentimentResponseBean.getCategory())); 
						 
						  //logger.info("Latitude**"+ Double.parseDouble(newTweetLocationList.get(sent.getText().getContent().substring(0, sent.getText().getContent().length()-1)).split("@")[0]));
						//  logger.info("Longitude**"+ Double.parseDouble(newTweetLocationList.get(sent.getText().getContent().substring(0, sent.getText().getContent().length()-1)).split("@")[1])); 
					  }
					 
					
					sentimentResponseBean.setLatitude(newTweetLocationList == null || newTweetLocationList.get(sent.getText().getContent().substring(0, sent.getText().getContent().length()-1)) == null ? 0.0 : Double.parseDouble(newTweetLocationList.get(sent.getText().getContent().substring(0, sent.getText().getContent().length()-1)).split("@")[0]));
					sentimentResponseBean.setLongitude(newTweetLocationList == null || newTweetLocationList.get(sent.getText().getContent().substring(0, sent.getText().getContent().length()-1)) == null ? 0.0 : Double.parseDouble(newTweetLocationList.get(sent.getText().getContent().substring(0, sent.getText().getContent().length()-1)).split("@")[1]));
					responseList.add(sentimentResponseBean);
				}
			} catch (Exception e) {
				logger.error("Error parsing api response to bean : "+e.getMessage());
			}
		}
		response.setResponseList(responseList);
		response.setCountMap(scoreCountMap);
		
		return response;

	}
	private int fetchCat(String cat) {
		if(cat.equalsIgnoreCase(VPOS))
			return 1;
		else if(cat.equalsIgnoreCase(POS))
			return 2;
		else if(cat.equalsIgnoreCase(VNEG))
			return 5;
		else if(cat.equalsIgnoreCase(NEG))
				return 4;
		else
			return 3;
		
	}
	
	/**
	 * @param score
	 * @return
	 * method to classify the scores in 5 general categories
	 */
	private static String getCategory(float score) {
		String sentimentClass = "";
		
		if(score <= 1 && score > 0.6) {
			sentimentClass = VPOS;
			scoreCountMap.put(VPOS, ++countArr[0]);
		}else if(score <= 0.6 && score > 0.1) {
			sentimentClass = POS;
			scoreCountMap.put(POS, ++countArr[1]);
		}else if(score >= -0.1 && score <= 0.1) {
			sentimentClass = NEUT;
			scoreCountMap.put(NEUT, ++countArr[2]);
		}else if(score >= -0.6 && score < -0.1) {
			sentimentClass = NEG;
			scoreCountMap.put(NEG, ++countArr[3]);
		}else if(score >= -1 && score < -0.6) {
			sentimentClass = VNEG;
			scoreCountMap.put(VNEG, ++countArr[4]);
		}
		
		return sentimentClass;
	}
	
}
