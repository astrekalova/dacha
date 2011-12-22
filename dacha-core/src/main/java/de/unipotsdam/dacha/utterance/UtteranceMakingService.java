package de.unipotsdam.dacha.utterance;

import de.unipotsdam.dacha.types.Utterance;


public interface UtteranceMakingService {

	Utterance makeUtterance(String query);
}
