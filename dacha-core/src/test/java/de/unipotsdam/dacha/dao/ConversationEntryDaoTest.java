//package de.unipotsdam.dacha.dao;
//
//import static org.junit.Assert.assertTrue;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import de.unipotsdam.dacha.db.ConversationEntry;
//import de.unipotsdam.dacha.test.TransactionAwareTest;
//
//
//public class ConversationEntryDaoTest  extends TransactionAwareTest {
//
//	@Autowired
//	private ConversationEntryDao conversationEntryDao;
//
//	@Test
//	public void simple() {
//		
//		List<ConversationEntry> entries = conversationEntryDao.findAll();
//		assertTrue(!entries.isEmpty());
//	}
//}
