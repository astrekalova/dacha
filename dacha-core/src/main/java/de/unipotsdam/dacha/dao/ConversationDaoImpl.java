package de.unipotsdam.dacha.dao;

import org.springframework.stereotype.Repository;

import de.unipotsdam.dacha.db.Conversation;

@Repository("conversationDao")
public class ConversationDaoImpl extends HibernateDao<Conversation> implements ConversationDao {

}
