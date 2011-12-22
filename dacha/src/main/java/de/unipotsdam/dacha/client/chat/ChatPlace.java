package de.unipotsdam.dacha.client.chat;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;


public class ChatPlace extends Place {
	
	private final ChatPlaceType type;
	
	public ChatPlace() {
		this.type = ChatPlaceType.Survey;
	}
	
	public ChatPlace(ChatPlaceType type) {
		this.type = type;
	}
	
	public ChatPlaceType getType() {
		return type;
	}

	@Prefix("Chat")
	public static class Tokenizer implements PlaceTokenizer<ChatPlace> {
		@Override
		public String getToken(ChatPlace place) {
			return place.getType().name();
		}

		@Override
		public ChatPlace getPlace(String token) {
			ChatPlaceType type = Enum.valueOf(ChatPlaceType.class, token);
			return new ChatPlace(type);
		}
	}
}
