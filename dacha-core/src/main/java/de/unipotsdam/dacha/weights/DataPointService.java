package de.unipotsdam.dacha.weights;

import de.unipotsdam.dacha.scoring.ScoredUtterance;

public interface DataPointService {

	public DataPoint makeDatapoint(ScoredUtterance utterance);
}
