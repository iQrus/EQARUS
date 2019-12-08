package com.google.semantic.response;

import java.util.Map;

public class SentimentResponseBean {

	private float magnitude;
	private float score;
	private String text;
	private String category;
	private int sentimentScore;
	private double latitude;
	private double longitude;

	public float getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(float magnitude) {
		this.magnitude = magnitude;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getSentimentScore() {
		return sentimentScore;
	}

	public void setSentimentScore(int sentimentScore) {
		this.sentimentScore = sentimentScore;
	}
	

}
