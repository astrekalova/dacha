package de.unipotsdam.dacha.dao;

import java.util.Collection;

import de.unipotsdam.dacha.db.MatchResponsePair;

public interface MatchResponsePairDao extends BaseDao<MatchResponsePair> {
	
	Collection<MatchResponsePair> findAllPositive();
	
	Collection<MatchResponsePair> findAllNegative();
}
