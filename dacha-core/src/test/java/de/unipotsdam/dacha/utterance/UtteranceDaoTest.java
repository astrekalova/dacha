package de.unipotsdam.dacha.utterance;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.unipotsdam.dacha.test.ContextAwareTest;
import de.unipotsdam.dacha.types.Utterance;

public class UtteranceDaoTest extends ContextAwareTest {

	@Autowired
	private UtteranceDao utteranceDao;

	@Test
	public void testSimple() {
		assertNotNull(utteranceDao);
		List<Utterance> result = utteranceDao.findAll();
		assertFalse(result.size() == 0);
	}
}
