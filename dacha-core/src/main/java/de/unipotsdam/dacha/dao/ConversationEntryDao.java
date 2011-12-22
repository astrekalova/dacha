package de.unipotsdam.dacha.dao;

import java.util.List;

import de.unipotsdam.dacha.db.ConversationEntry;

public interface ConversationEntryDao extends BaseDao<ConversationEntry> {

	List<ConversationEntry> findAll();
}
