package com.checkersgwt.client.mvp;

import com.checkersgwt.client.place.HomePlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({HomePlace.Tokinizer.class})
public interface CheckersPlaceHistoryMapper extends PlaceHistoryMapper {
}
