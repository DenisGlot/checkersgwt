package com.checkersgwt.client.mvp;

import com.checkersgwt.client.view.HomeView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactory implements IClientFactory {
	
	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
	
	private static final HomeView homeView = new HomeView();
	
	
	@Override
	public EventBus getEventBus() {
		return eventBus;
	}
	
	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}
	
	@Override
	public  HomeView getHomeView() {
		return homeView;
	}
}
