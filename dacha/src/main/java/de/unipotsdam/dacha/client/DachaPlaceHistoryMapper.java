package de.unipotsdam.dacha.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import de.unipotsdam.dacha.client.chat.ChatPlace;

@WithTokenizers({ ChatPlace.Tokenizer.class })
public interface DachaPlaceHistoryMapper extends PlaceHistoryMapper {

}
