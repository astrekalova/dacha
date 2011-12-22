package de.unipotsdam.dacha.client.chat;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.unipotsdam.dacha.client.ClientFactory;
import de.unipotsdam.dacha.shared.chat.ChatResponse;
import de.unipotsdam.dacha.shared.chat.ChatServiceAsync;

public class ChatActivity extends AbstractActivity implements ChatView.Presenter {

	private final ClientFactory clientFactory;
	private final ChatPlace place;
	private ChatView view;

	public ChatActivity(ClientFactory clientFactory, ChatPlace place) {
		this.clientFactory = clientFactory;
		this.place = place;
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getChatView();
		view.setPresenter(this);
		panel.setWidget(view.asWidget());
		
		if (place.getType() == ChatPlaceType.Standard) {
			view.setInfoBoxVisibility(false);
		} else if (place.getType() == ChatPlaceType.Survey) {
			view.setInfoBoxVisibility(true);
			view.removeDebugInfo();
		}		
	}

	@Override
	public void onNewMessage(final String message) {

		view.addChatEntry(message);
		view.setLastMessage(message);
		view.clearAnswerOptions();

		ChatServiceAsync chatServiceAsync = clientFactory.getChatServiceAsync();
		chatServiceAsync.sendMessage(message, new AsyncCallback<ChatResponse>() {

			@Override
			public void onSuccess(ChatResponse result) {
				view.addChatEntry(result.getResponseString());
				view.setAnswerOptions(result.getAnswerOptions());
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	@Override
	public void onAnswerOptionsSelection(List<Integer> answerOptionIds) {
		ChatServiceAsync chatServiceAsync = clientFactory.getChatServiceAsync();
		chatServiceAsync.selectAnswerOptions(answerOptionIds, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

}
