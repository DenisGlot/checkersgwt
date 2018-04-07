package com.checkersgwt.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class HomePlace extends Place {

	private String homeName;
	
	public HomePlace(String token) {
		this.homeName = token;
	}
	
	public String getHomeName() {
		return homeName;
	}
	
	public static class Tokinizer implements PlaceTokenizer<HomePlace> {
		
		@Override
		public HomePlace getPlace(String token) {
			return new HomePlace(token);
		}
		
		@Override
		public String getToken(HomePlace place) {
			return place.getHomeName();
		}
	}
}
