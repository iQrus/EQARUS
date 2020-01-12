package com.google.semantic.bean;

public class RedditTokenBean {
	
	private String username="BanditQ21";
	private String clientId="";
	private String clientSecret="";
	private String stringUrl = "https://reddit.com/api/v/access_token";
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getStringUrl() {
		return stringUrl;
	}
	public void setStringUrl(String stringUrl) {
		this.stringUrl = stringUrl;
	}
	
	

}
