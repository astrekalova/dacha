package de.unipotsdam.dacha.scoring;

import de.unipotsdam.dacha.types.Utterance;

public interface ScoringService {

	ScoredUtterance scoreUtterance(Utterance query, ScoredUtterance scoredUtterance, Utterance nextUtterance);

	double scoreHistory(Utterance utterance, Utterance utterance2,
			Utterance lastRequestUtterance, Utterance lastResponseUtterance);
}
