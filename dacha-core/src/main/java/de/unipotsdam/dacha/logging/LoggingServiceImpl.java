package de.unipotsdam.dacha.logging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.unipotsdam.dacha.core.Response;
import de.unipotsdam.dacha.dao.ConversationDao;
import de.unipotsdam.dacha.dao.ConversationEntryDao;
import de.unipotsdam.dacha.db.Conversation;
import de.unipotsdam.dacha.db.ConversationEntry;
import de.unipotsdam.dacha.db.MatchResponsePair;
import de.unipotsdam.dacha.scoring.ScoredUtterance;

@Service("loggingService")
@Scope("session")
public class LoggingServiceImpl implements LoggingService {
	
	private static final Logger log = LoggerFactory.getLogger(LoggingServiceImpl.class);
	
	@Autowired
	private ConversationDao conversationDao;
	
	@Autowired
	private ConversationEntryDao conversationEntryDao;
	
	private Long conversationId = null;
	private Long lastEntryId = null;
	private Response response = null;
	
	@Override
	public void logMessage(String request, String response) {
		
		final Conversation conversation = getConversation();
		
		ConversationEntry entry = new ConversationEntry();
		entry.setDate(new Date());
		entry.setRequest(request);
		entry.setResponse(response);
		
		conversation.getConversationEntries().add(entry);
		
		conversationEntryDao.saveOrUpdate(entry);
		
		lastEntryId = entry.getId();
	}

	@Override
	public Conversation getConversation() {
		if (conversationId == null) {
			Conversation conversation = new Conversation();
			conversation.setDate(new Date());
			
			conversationDao.saveOrUpdate(conversation);
			conversationId = conversation.getId();
			
			return conversation;
		} else {
			return conversationDao.findById(conversationId);
		}
	}

	@Override
	@Transactional
	public void logAnswerOptionSelection(List<Integer> answerOptionIds) {
		
		if (response == null) {
			log.error("No response set");
			return;
		}
		Collections.sort(answerOptionIds);		
		Integer lastAnswerOptionId = answerOptionIds.get(answerOptionIds.size() - 1);
		
		List<ScoredUtterance> responseUtterances = new ArrayList<ScoredUtterance>();
		for (ScoredUtterance utterance : response.getResponseUtterances()) {
			if (response.getIdForUtterance(utterance) <= lastAnswerOptionId) {
				responseUtterances.add(utterance);
			}
		}		
		
		// extract data from response
		List<RankedUtterance> negativeAnswers = new ArrayList<RankedUtterance>();
		List<RankedUtterance> positiveAnswers = new ArrayList<RankedUtterance>();
		
		for (ScoredUtterance utterance : responseUtterances) {
			int utteranceId = response.getIdForUtterance(utterance);
			RankedUtterance rankedUtterance = new RankedUtterance();
			rankedUtterance.setId(utteranceId);
			
			for (Integer answerOptionId : answerOptionIds) {
				if (utteranceId == answerOptionId) {
					rankedUtterance.setScoredUtterance(utterance);
					positiveAnswers.add(rankedUtterance);
					break;
				} else if (answerOptionId >= utteranceId) {
					rankedUtterance.setScoredUtterance(utterance);
					negativeAnswers.add(rankedUtterance);
					break;
				}
			}			
		}
		
		if (positiveAnswers.isEmpty()) {
			log.error("No utterance with id {} found", answerOptionIds);
			return;
		}
		
		
		// save data with last entry
		final ConversationEntry entry = getLastEntry();

		entry.getPositivePairs().clear();
		for (RankedUtterance positiveAnswer : positiveAnswers) {
			entry.getPositivePairs().add(createMatchResponsePair(positiveAnswer));
		}
		
		entry.getNegativePairs().clear();
		for (RankedUtterance negativeAnswer : negativeAnswers) {
			entry.getNegativePairs().add(createMatchResponsePair(negativeAnswer));
		}
		
		conversationEntryDao.saveOrUpdate(entry);		
	}	

	private MatchResponsePair createMatchResponsePair(RankedUtterance rankedUtterance) {
		MatchResponsePair result = new MatchResponsePair();
		
		result.setMatch(rankedUtterance.getScoredUtterance().getUtterance().getValue());
		result.setResponse(rankedUtterance.getScoredUtterance().getUtterance().getNext());
		result.setRanking(rankedUtterance.getId());
		
		return result;
	}
	
	
	public ConversationEntry getLastEntry() {
		if (lastEntryId == null) {
			throw new IllegalStateException("No entries yet");
		} else {
			return conversationEntryDao.findById(lastEntryId);
		}
	}

	@Override
	public void setResponse(Response response) {
		this.response = response;
	}
}
