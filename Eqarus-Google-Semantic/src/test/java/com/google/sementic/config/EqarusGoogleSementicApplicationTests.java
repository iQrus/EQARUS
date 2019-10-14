package com.google.sementic.config;

import static org.mockito.Mockito.when;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

 
import com.google.semantic.bean.TwitterRequestBean;
import com.google.semantic.config.EqarusGoogleSemanticApplication;
import com.google.semantic.response.SentimentResponseBean;
import com.google.semantic.response.SentimentWrapperResponse;
import com.google.semantic.service.LanguageAnalyzeService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes=EqarusGoogleSemanticApplication.class)
public class EqarusGoogleSementicApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private com.fasterxml.jackson.databind.ObjectMapper ObjectMapper;
	
	@Mock
	private LanguageAnalyzeService languageAnalyzeService;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Before
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void contextLoads() {
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void getLanguageAnalysisTest() throws Exception {
		TwitterRequestBean twitterRequestBean = new TwitterRequestBean();
		List <String> twitterDataList = new ArrayList<>();
		Map<String, SentimentResponseBean> semanticResponseMap=new HashMap<>();
		SentimentResponseBean sentimentResponseBean = new SentimentResponseBean();
		String input="Bihar Floods";
		sentimentResponseBean.setMagnitude(0.6f);
		sentimentResponseBean.setScore(-0.6f);
		semanticResponseMap.put(input, sentimentResponseBean);
		twitterDataList.add(input);
		twitterRequestBean.setTwitterDataList(twitterDataList);
		when(languageAnalyzeService.languageAnalysis(twitterDataList)).thenReturn(semanticResponseMap);

		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/home").contentType(APPLICATION_JSON_UTF8).content(new ObjectMapper().writeValueAsString(twitterRequestBean));
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String responseResult=result.getResponse().getContentAsString();
		
		SentimentWrapperResponse sentimentWrapperActualResponse= ObjectMapper.readValue(responseResult, SentimentWrapperResponse.class);
		
		SentimentWrapperResponse sentimentWrapperExpectedResponse= new SentimentWrapperResponse();
		sentimentWrapperExpectedResponse.setSentimentResponseMap(semanticResponseMap);
		

		
		//Assert.assertSame(sentimentWrapperExpectedResponse.getSentimentResponseMap().containsKey(input), sentimentWrapperActualResponse.getSentimentResponseMap().containsKey(input));
		Assert.assertSame(sentimentWrapperExpectedResponse.getSentimentResponseMap().containsValue(sentimentWrapperExpectedResponse), sentimentWrapperActualResponse.getSentimentResponseMap().containsValue(sentimentWrapperActualResponse));
	}

}
