package de.unipotsdam.dacha.client.chat;

import java.util.Collection;
import java.util.List;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.IsWidget;

import de.unipotsdam.dacha.shared.chat.AnswerOption;

public interface ChatView extends IsWidget {

	void setPresenter(Presenter presenter);
	
	void clear();
	
	void addChatEntry(String chatEntry);
	
	void setLastMessage(String message);
	
	void clearAnswerOptions();
	
	void setAnswerOptions(Collection<AnswerOption> answerOptions);
	
	void setInfoBoxVisibility(boolean visible);
	
	public interface Presenter
	{
		void onNewMessage(String message);
		
		void onAnswerOptionsSelection(List<Integer> answerOptionIds);
	}

	void removeDebugInfo();
}
