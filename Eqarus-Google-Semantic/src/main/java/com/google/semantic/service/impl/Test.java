package com.google.semantic.service.impl;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub]
		
		List<String> tweets = new ArrayList<String>();
		
		tweets.add("#HaryanaVoteforHonesty  Reports from Muzffarbad, Neelum Valley, Leepa Valley and few others places in Pakistan Occupied Kashmir (POK) near Line of Control (LoC) with India indicate heavy firing over last few hours after Pakistan Army’s provocation helping terrorists enter India.");
		tweets.add("strike was done by @adgpi on 7 terror camps in PoK.\n- 50+ Terrorists were killed.\n- Today morning Pak army Athmaqam District HQ targeted in revenge of 2 brave soldier\u0027s martyrdom.\n\nKEEP WATCHING US SCREWING YOU\n\nHAWAN 🔥");
		tweets.add("Indian Army has launched attacks on terrorist camps situated inside Pakistan occupied Kashmir (PoK) opposite the Tangdhar sector. This is in retaliation to the support provided by Pakistan Army to push terrorists into Indian territory.");
		tweets.add("Indian Army has launched attacks on #terrorist camps situated inside PoK.\\n\\n🇮🇳 army artillery guns hit the terror camp. \\n\\n#IndianArmy #Pok #Pakistan");
		for(String tweet : tweets) {
			System.out.println("##before cleaning : "+tweet);
			String cleanTweet = tweet.replaceAll("(@[A-Za-z0-9]+)|([^0-9A-Za-z \\t])|(\\w+:\\/\\/\\S+)", " ");
			System.out.println("##After cleaning : "+cleanTweet);
		}
		

	}

}
