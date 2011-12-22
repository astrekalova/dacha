package de.unipotsdam.dacha.utils;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;

import org.junit.Assert;
import org.junit.Test;

import de.unipotsdam.dacha.types.Sentence;
import de.unipotsdam.dacha.types.Utterance;
import de.unipotsdam.dacha.types.Utterances;

public class XmlBindingTest {

	@Test
	public void unmarshalling() throws Exception {
		
		InputStream is = getClass().getClassLoader().getResourceAsStream("data/season01.xml");		
		
		JAXBContext context = JAXBContext.newInstance(Utterances.class);
		Utterances utterances = (Utterances) context.createUnmarshaller().unmarshal(is);
		
		Assert.assertFalse(utterances.getUtterances().isEmpty());
		
		Utterance utterance = utterances.getUtterances().get(1);
		
		Assert.assertFalse(utterance.getSentences().isEmpty());
		
		Sentence sentence = utterance.getSentences().get(0);
		
		Assert.assertFalse(sentence.getTokens().isEmpty());
		
	}
}
