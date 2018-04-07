package com.checkersgwt.client.mvp;

import com.checkersgwt.client.activity.HomeActivity;
import com.checkersgwt.client.place.HomePlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class CheckersActivityMapper implements ActivityMapper{
	
	private IClientFactory clientFactory;
	
	public CheckersActivityMapper(IClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace) {
			return new HomeActivity((HomePlace) place, clientFactory);
		}
		return null;
	}
}
