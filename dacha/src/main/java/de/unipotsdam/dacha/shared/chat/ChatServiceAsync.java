package de.unipotsdam.dacha.shared.chat;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChatServiceAsync {
	void sendMessage(String message, AsyncCallback<ChatResponse> callback);

	void selectAnswerOptions(List<Integer> answerOptionIds,
			AsyncCallback<Void> asyncCallback);
}
