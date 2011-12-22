package de.unipotsdam.dacha.shared.chat;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ChatResponse implements IsSerializable {

	private String responseString;
	private List<AnswerOption> answerOptions = new ArrayList<AnswerOption>();	
	
	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	public List<AnswerOption> getAnswerOptions() {
		return answerOptions;
	}

	public void setAnswerOptions(List<AnswerOption> answerOptions) {
		this.answerOptions = answerOptions;
	}
}
