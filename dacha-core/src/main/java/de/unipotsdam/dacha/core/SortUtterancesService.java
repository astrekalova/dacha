package de.unipotsdam.dacha.core;

import java.util.List;

import de.unipotsdam.dacha.scoring.ScoredUtterance;
import de.unipotsdam.dacha.types.Utterance;

public interface SortUtterancesService {

	List<ScoredUtterance> doSort(Utterance Query, List<Utterance> utterances, 
			Utterance lastRequestUtterance, Utterance lastResponseUtterance);
}
