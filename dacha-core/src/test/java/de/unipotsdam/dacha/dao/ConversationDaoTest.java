package de.unipotsdam.dacha.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.unipotsdam.dacha.db.Conversation;
import de.unipotsdam.dacha.db.ConversationEntry;
import de.unipotsdam.dacha.test.TransactionAwareTest;

public class ConversationDaoTest extends TransactionAwareTest {

	@Autowired
	private ConversationDao conversationDao;
	
	@Test
	public void simpleConversation() {
		
		Conversation conversation = new Conversation();
		conversation.setDate(new Date());
		
		ConversationEntry entry = new ConversationEntry();
		entry.setDate(new Date());
		entry.setRequest("entry1");

		conversation.getConversationEntries().add(entry);
		
		conversationDao.saveOrUpdate(conversation);
		final Long conversationId = conversation.getId();
		
		conversation = conversationDao.findById(conversationId);
		assertNotNull(conversation);
		assertFalse(conversation.getConversationEntries().isEmpty());
	}
}
