package de.unipotsdam.dacha.logging;

import java.util.List;

import de.unipotsdam.dacha.core.Response;
import de.unipotsdam.dacha.db.Conversation;

public interface LoggingService {

	void logMessage(String request, String response);
	
	void setResponse(Response response);
	
	Conversation getConversation();

	void logAnswerOptionSelection(List<Integer> answerOptionIds);
}
