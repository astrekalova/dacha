package de.unipotsdam.dacha.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

import de.unipotsdam.dacha.client.chat.ChatView;
import de.unipotsdam.dacha.client.chat.ChatViewImpl;
import de.unipotsdam.dacha.shared.chat.ChatService;
import de.unipotsdam.dacha.shared.chat.ChatServiceAsync;

public class ClientFactoryImpl implements ClientFactory {

	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
	private static final ChatView chatView = new ChatViewImpl();
	private static final ChatServiceAsync chatServiceAsync = GWT.create(ChatService.class);

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public ChatView getChatView() {
		return chatView;
	}

	@Override
	public ChatServiceAsync getChatServiceAsync() {
		return chatServiceAsync;
	}
}
