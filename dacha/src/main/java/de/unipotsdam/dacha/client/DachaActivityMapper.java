package de.unipotsdam.dacha.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import de.unipotsdam.dacha.client.chat.ChatActivity;
import de.unipotsdam.dacha.client.chat.ChatPlace;


public class DachaActivityMapper implements ActivityMapper {
	
	private final ClientFactory clientFactory;

	public DachaActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		
		if (place instanceof ChatPlace) {
			return new ChatActivity(clientFactory, (ChatPlace)place);
		}
		
		throw new RuntimeException("Unknow place: " + place.toString());
	}
}
