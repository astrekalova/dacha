package de.unipotsdam.dacha.utterance;

import java.util.List;

import de.unipotsdam.dacha.types.Utterance;


public interface UtteranceDao {

	List<Utterance> findAll();
}
