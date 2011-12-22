package de.unipotsdam.dacha.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.unipotsdam.dacha.scoring.ScoredUtterance;
import de.unipotsdam.dacha.types.Utterance;

public class SortUtterancesServiceImplTest {

	@Autowired
	public SortUtterancesService sortUtterancesService;
	
	@Test
	public void testUtteranceComparator() {
		
		Comparator<? super ScoredUtterance> comparator = UtteranceComparator.INSTANCE;
		
		ScoredUtterance u1 = new ScoredUtterance(new Utterance());
		u1.setOverallScore(0);
		
		ScoredUtterance u2 = new ScoredUtterance(new Utterance());
		u2.setOverallScore(1);
		
		ScoredUtterance u3 = new ScoredUtterance(new Utterance());
		u3.setOverallScore(0.5);
		
		List<ScoredUtterance> comparedUtterances = Arrays.asList(u1, u2, u3);
		
		Collections.sort(comparedUtterances, comparator);
		
		assertEquals(comparedUtterances.get(0), u2);
		assertEquals(comparedUtterances.get(1), u3);
	}

}
