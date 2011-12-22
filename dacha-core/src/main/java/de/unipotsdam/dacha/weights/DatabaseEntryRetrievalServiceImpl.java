package de.unipotsdam.dacha.weights;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.unipotsdam.dacha.dao.ConversationEntryDao;
import de.unipotsdam.dacha.db.ConversationEntry;
import de.unipotsdam.dacha.db.MatchResponsePair;

@Service("databaseEntryRetrievalService")
public class DatabaseEntryRetrievalServiceImpl implements DatabaseEntryRetrievalService {

	@Autowired
	ConversationEntryDao conversationEntryDao;
	
	public List<DatabaseEntry> retrieveDatabaseEntries() {
		
		List<ConversationEntry> entries = conversationEntryDao.findAll();
		List<DatabaseEntry> dataPoints = new ArrayList<DatabaseEntry>();
		
		for (ConversationEntry entry : entries) {
			if (!entry.getNegativePairs().isEmpty()) {
				for (MatchResponsePair pair : entry.getNegativePairs()) {
					DatabaseEntry point = new DatabaseEntry();
					point.setRequest(entry.getRequest());
					point.setMatch(pair.getMatch());
					point.setResponse(pair.getResponse());
					point.setNegative(true);
					dataPoints.add(point);
				}				
			} else if (!entry.getPositivePairs().isEmpty()) {
				for (MatchResponsePair pair : entry.getPositivePairs()) {
					DatabaseEntry point = new DatabaseEntry();
					point.setRequest(entry.getRequest());
					point.setMatch(pair.getMatch());
					point.setResponse(pair.getResponse());
					point.setNegative(false);
					dataPoints.add(point);
				}
			}
		}
		
		return dataPoints;
	}
}
