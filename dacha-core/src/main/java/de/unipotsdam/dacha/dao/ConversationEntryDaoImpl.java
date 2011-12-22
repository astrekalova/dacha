package de.unipotsdam.dacha.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import de.unipotsdam.dacha.db.ConversationEntry;

@Repository("conversationEntryDao")
public class ConversationEntryDaoImpl extends HibernateDao<ConversationEntry> implements ConversationEntryDao {

	@Override
	public List<ConversationEntry> findAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(ConversationEntry.class);
		return getHibernateTemplate().findByCriteria(criteria);
	}
}
