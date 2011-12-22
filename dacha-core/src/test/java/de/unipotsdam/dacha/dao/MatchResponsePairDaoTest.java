package de.unipotsdam.dacha.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.unipotsdam.dacha.db.ConversationEntry;
import de.unipotsdam.dacha.db.MatchResponsePair;
import de.unipotsdam.dacha.test.ContextAwareTest;

public class MatchResponsePairDaoTest extends ContextAwareTest {
	
	@Autowired
	private ConversationEntryDao conversationEntryDao;
	
	@Autowired
	private MatchResponsePairDao matchResponsePairDao;
	
	@Test
	public void positive() {
		ConversationEntry entry = new ConversationEntry();
		MatchResponsePair positivePair = new MatchResponsePair();
		entry.getPositivePairs().add(positivePair);
		
		conversationEntryDao.saveOrUpdate(entry);
		
		Collection<MatchResponsePair> allPositive = matchResponsePairDao.findAllPositive();
		assertEquals(1, allPositive.size());
		
		Collection<MatchResponsePair> allNegative = matchResponsePairDao.findAllNegative();
		assertTrue(allNegative.isEmpty());
	}
}
