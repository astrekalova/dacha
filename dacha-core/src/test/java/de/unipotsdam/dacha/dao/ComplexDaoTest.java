package de.unipotsdam.dacha.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.unipotsdam.dacha.db.Conversation;
import de.unipotsdam.dacha.db.ConversationEntry;
import de.unipotsdam.dacha.db.MatchResponsePair;
import de.unipotsdam.dacha.test.TransactionAwareTest;

public class ComplexDaoTest extends TransactionAwareTest {

	@Autowired
	private ConversationDao conversationDao;
	
	@Autowired
	private ConversationEntryDao conversationEntryDao;
	
	@Test
	public void positiveNegativePairs() {
		final Conversation conversation = new Conversation();
		conversation.setDate(new Date());
		
		final ConversationEntry entry = new ConversationEntry();
		entry.setRequest("TEST REQUEST");
		entry.setResponse("TEST RESPONSE");
		entry.setDate(new Date());
		conversation.getConversationEntries().add(entry);
		
		conversationDao.saveOrUpdate(conversation);
		
		entry.getPositivePairs().add(pair("P1"));
		entry.getPositivePairs().add(pair("P2"));
		entry.getPositivePairs().add(pair("P3"));
		
		entry.getNegativePairs().add(pair("N1"));
		entry.getNegativePairs().add(pair("N2"));
		
		conversationEntryDao.saveOrUpdate(entry);
		
		ConversationEntry findEntry = conversationEntryDao.findById(entry.getId());
		assertEquals(3, findEntry.getPositivePairs().size());
		assertEquals(2, findEntry.getNegativePairs().size());
	}
	
	private MatchResponsePair pair(String value) {
		MatchResponsePair pair = new MatchResponsePair();
		pair.setMatch("TESTMATCH" + value);
		pair.setResponse("TESTRESPONSE" + value);
		
		return pair;
	}
}
