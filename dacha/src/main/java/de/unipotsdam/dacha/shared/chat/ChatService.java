package de.unipotsdam.dacha.shared.chat;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("chat")
public interface ChatService extends RemoteService {
	
	ChatResponse sendMessage(String message);
	
	void selectAnswerOptions(List<Integer> answerOptionIds);
}
