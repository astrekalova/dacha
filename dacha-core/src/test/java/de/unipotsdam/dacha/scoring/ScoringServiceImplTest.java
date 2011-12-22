package de.unipotsdam.dacha.scoring;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import de.unipotsdam.dacha.test.ContextAwareTest;
import de.unipotsdam.dacha.types.Utterance;
import de.unipotsdam.dacha.utterance.UtteranceMakingService;

public class ScoringServiceImplTest extends ContextAwareTest {

	@Autowired
	ScoringService scoringService;
	
	@Autowired
	UtteranceMakingService utteranceMakingService;
	
	private Utterance requestUtterance; 
	private Utterance dbUtterance;
	private Utterance nextUtterance;
	
	@Before
	public void setUp() {
		
		requestUtterance = utteranceMakingService.makeUtterance("Hello world!");
		dbUtterance = utteranceMakingService.makeUtterance("Hello you!");
		dbUtterance.setNext("All your bases are belong to us!");
		nextUtterance = utteranceMakingService.makeUtterance("All your bases are belong to us!");
	}
	
	@Test
	public void testScoreUtterance() {		
		
		ScoredUtterance scoredUtterance = 
			scoringService.scoreUtterance(requestUtterance, new ScoredUtterance(dbUtterance), nextUtterance);
		
		assertTrue(scoredUtterance.getOverallScore() != 0);		
		assertTrue(scoredUtterance.getWordScore() != 0);
		assertTrue(scoredUtterance.getLemmaScore() != 0);
		assertTrue(scoredUtterance.getPosScore() != 0);
		assertTrue(scoredUtterance.getWordPosScore() != 0);
		assertTrue(scoredUtterance.getEdgeScore() != 0);
		assertTrue(scoredUtterance.getDepScore() != 0);
		assertTrue(scoredUtterance.getNerScore() == 0);
		assertTrue(scoredUtterance.getLengthScore() == 1);		
	}

	@Test
	public void testScoreHistory() {
		
		Utterance lastRequestUtterance = utteranceMakingService.makeUtterance("Hey!");
		Utterance lastResponseUtterance = utteranceMakingService.makeUtterance("Hey");
		
		Utterance preLastDbUtterance = utteranceMakingService.makeUtterance("Wow");
		Utterance lastDbUtterance = utteranceMakingService.makeUtterance("Hey!");
		
		double historyScore = scoringService.scoreHistory(
				preLastDbUtterance, lastDbUtterance, lastRequestUtterance, lastResponseUtterance);
		
		assertTrue(historyScore > 0);
	}
}
