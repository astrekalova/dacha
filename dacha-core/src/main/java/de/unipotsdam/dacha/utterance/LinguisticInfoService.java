package de.unipotsdam.dacha.utterance;

import java.util.List;

import de.unipotsdam.dacha.types.Sentence;

import edu.stanford.nlp.pipeline.Annotation;

public interface LinguisticInfoService {

	List<Sentence> addLinguisticInfo(Annotation annotation);

}