package de.unipotsdam.dacha.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

import de.unipotsdam.dacha.client.chat.ChatView;
import de.unipotsdam.dacha.shared.chat.ChatServiceAsync;

public interface ClientFactory {
	EventBus getEventBus();
	PlaceController getPlaceController();
	ChatView getChatView();
	ChatServiceAsync getChatServiceAsync();
}
