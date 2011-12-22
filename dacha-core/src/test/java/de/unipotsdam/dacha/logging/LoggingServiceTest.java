package de.unipotsdam.dacha.logging;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.unipotsdam.dacha.core.Response;
import de.unipotsdam.dacha.db.Conversation;
import de.unipotsdam.dacha.scoring.ScoredUtterance;
import de.unipotsdam.dacha.test.TransactionAwareTest;
import de.unipotsdam.dacha.types.Utterance;

public class LoggingServiceTest extends TransactionAwareTest {

	@Autowired
	private LoggingService loggingService;	
	
	@Test
	public void logMessage() {

		// flush eventual log entries from other tests
		loggingService.getConversation().getConversationEntries().clear();

		loggingService.logMessage("request1", "response1");
		loggingService.logMessage("request2", "response2");
		
		Conversation conversationAfter = loggingService.getConversation();
		
		assertEquals(2, conversationAfter.getConversationEntries().size());
	}
}
