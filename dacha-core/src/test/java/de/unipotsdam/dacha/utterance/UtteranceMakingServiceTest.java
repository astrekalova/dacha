package de.unipotsdam.dacha.utterance;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.unipotsdam.dacha.test.ContextAwareTest;
import de.unipotsdam.dacha.types.Utterance;


public class UtteranceMakingServiceTest extends ContextAwareTest {

	@Autowired
	UtteranceMakingService utteranceMakingService;
	
	@Test
	public void testProcessQuery() {
		
		String query = "I'll put a pillowcase over my head.";
		Utterance utterance = utteranceMakingService.makeUtterance(query);
		
		assertNotNull(utterance);
	}
}
