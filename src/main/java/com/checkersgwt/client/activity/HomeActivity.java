package com.checkersgwt.client.activity;

import com.checkersgwt.client.mvp.IClientFactory;
import com.checkersgwt.client.place.HomePlace;
import com.checkersgwt.client.view.HomeView;
import com.checkersgwt.client.view.IHomeView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HomeActivity extends AbstractActivity implements IHomeView.Presenter {
	
	private IClientFactory clientFactory;
	private String name;
	
	public HomeActivity(HomePlace homePlace, IClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.name = homePlace.getHomeName();
	}
	
	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		HomeView view = clientFactory.getHomeView();
		view.setName(name);
		view.setPresenter(this);
		panel.setWidget(view.asWidget());
	}
	
//	/**
//	 * Ask user before stopping this activity
//	 */
//	@Override
//	public String mayStop() {
//		return "Please hold on. This activity is stopping.";
//	}
	
	/**
	 * Navigate to a new Place in the browser
	 */
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}
}
