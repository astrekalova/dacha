package de.unipotsdam.dacha.dao;

import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import de.unipotsdam.dacha.db.MatchResponsePair;

@Repository("matchResponsePairDao")
public class MatchResponsePairDaoImpl extends HibernateDao<MatchResponsePair> implements MatchResponsePairDao {

	@Override
	public Collection<MatchResponsePair> findAllPositive() {
		DetachedCriteria criteria = DetachedCriteria.forClass(MatchResponsePair.class);
		criteria.add(Property.forName("positiveId").isNotNull());
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public Collection<MatchResponsePair> findAllNegative() {
		DetachedCriteria criteria = DetachedCriteria.forClass(MatchResponsePair.class);
		criteria.add(Property.forName("negativeId").isNotNull());
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
