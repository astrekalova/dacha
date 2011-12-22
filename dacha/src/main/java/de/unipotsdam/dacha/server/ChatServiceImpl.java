package de.unipotsdam.dacha.server;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.unipotsdam.dacha.core.ChatEngine;
import de.unipotsdam.dacha.core.Response;
import de.unipotsdam.dacha.logging.LoggingService;
import de.unipotsdam.dacha.scoring.ScoredUtterance;
import de.unipotsdam.dacha.shared.chat.AnswerOption;
import de.unipotsdam.dacha.shared.chat.ChatResponse;
import de.unipotsdam.dacha.shared.chat.ChatService;

@Service("chatService")
public class ChatServiceImpl extends RemoteServiceServlet implements ChatService {

	private static final long serialVersionUID = 70806854827487741L;

	@Resource(name = "chatEngine")
	private ChatEngine chatEngine;
	
	@Autowired
	private LoggingService loggingService;
	
	@Override
	public ChatResponse sendMessage(String message) {

		final ChatResponse chatResponse = new ChatResponse();
		
		Response response = chatEngine.getResponse(message);
		
		chatResponse.setResponseString(response.getResponseString());
		
		for (ScoredUtterance responseUtterance : response.getResponseUtterances()) {
			AnswerOption answerOption = new AnswerOption();
			answerOption.setId(response.getIdForUtterance(responseUtterance));
			answerOption.setSimilarSentence(responseUtterance.getUtterance().getValue());
			answerOption.setAnswer(responseUtterance.getUtterance().getNext());
			answerOption.setOverallScore(Math.round(responseUtterance.getOverallScore()*100.0) / 100.0);
			
			answerOption.setDepScore(Math.round(responseUtterance.getDepScore()*100.0) / 100.0);
			answerOption.setEdgeScore(Math.round(responseUtterance.getEdgeScore()*100.0) / 100.0);
			answerOption.setHistoryScore(Math.round(responseUtterance.getHistoryScore()*100.0) / 100.0);
			answerOption.setLemmaScore(Math.round(responseUtterance.getLemmaScore()*100.0) / 100.0);
			answerOption.setLengthScore(responseUtterance.getLengthScore());
			answerOption.setNerScore(responseUtterance.getNerScore());
			answerOption.setPosScore(Math.round(responseUtterance.getPosScore()*100.0) / 100.0);
			answerOption.setWordPosScore(Math.round(responseUtterance.getWordPosScore()*100.0) / 100.0);
			answerOption.setWordScore(Math.round(responseUtterance.getWordScore()*100.0) / 100.0);

			chatResponse.getAnswerOptions().add(answerOption);
		}
		
		return chatResponse;
	}

	@Override
	public void selectAnswerOptions(List<Integer> answerOptionIds) {
		loggingService.logAnswerOptionSelection(answerOptionIds);
	}
}
