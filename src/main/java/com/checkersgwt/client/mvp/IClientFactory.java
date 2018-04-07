package com.checkersgwt.client.mvp;

import com.checkersgwt.client.view.HomeView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface IClientFactory {
	EventBus getEventBus();
	
	PlaceController getPlaceController();
	
	HomeView getHomeView();
}
