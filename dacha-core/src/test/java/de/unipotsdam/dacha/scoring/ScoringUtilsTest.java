package de.unipotsdam.dacha.scoring;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.unipotsdam.dacha.types.Dependency;
import de.unipotsdam.dacha.types.Edge;
import de.unipotsdam.dacha.types.Sentence;
import de.unipotsdam.dacha.types.Token;
import de.unipotsdam.dacha.types.Utterance;

public class ScoringUtilsTest {

	Utterance request = new Utterance();
	Utterance u2 = new Utterance();
	ScoredUtterance scoredUtterance = null;
	Utterance nextUtterance = new Utterance();
	
	Utterance nullUtterance = null;
	
	@Before
	public void setUp() {
		
		Sentence s11 = new Sentence();
		
		Token t11 = new Token();
		t11.setLemma("hello");
		t11.setWord("hello");
		t11.setPos("JJ");
		t11.setWordPos("hello_JJ");
		t11.setNer("O");
		Token t12 = new Token();
		t12.setLemma("world");
		t12.setWord("world");
		t12.setPos("NN");
		t12.setWordPos("world_NN");
		t12.setNer("O");
		Token t13 = new Token();
		t13.setLemma("!");
		t13.setWord("!");
		t13.setPos(".");
		t13.setWordPos("!_.");
		t13.setNer("O");
		List<Token> ts11 = Arrays.asList(t11, t12, t13);
		s11.setTokens(ts11);
		
		Edge e1 = new Edge();
		e1.setStartNode("NP");
		e1.setEndNode("JJ");
		Edge e2 = new Edge();
		e2.setStartNode("NP");
		e2.setEndNode("NN");
		Edge e3 = new Edge();
		e3.setStartNode("NP");
		e3.setEndNode(".");		
		List<Edge> edges = Arrays.asList(e1, e2, e3);
		s11.setEdges(edges);
		
		Dependency dependency11 = new Dependency();
		dependency11.setType("amod");
		List<Dependency> d11 = Arrays.asList(dependency11);
		s11.setDependencies(d11);
		
		List<Sentence> sents1 = Arrays.asList(s11);
		request.setSentences(sents1);

		
		Sentence s21 = new Sentence();
		Token t21 = new Token();
		t21.setLemma("hello");
		t21.setWord("hello");
		t21.setPos("JJ");
		t21.setWordPos("hello_JJ");
		t21.setNer("O");
		Token t22 = new Token();
		t22.setLemma("you");
		t22.setWord("you");
		t22.setPos("P");
		t22.setWordPos("you_P");
		t22.setNer("O");
		Token t23 = new Token();
		t23.setLemma("!");
		t23.setWord("!");
		t23.setPos(".");
		t23.setWordPos("!_.");
		t23.setNer("O");
		List<Token> ts21 = Arrays.asList(t21, t22, t23);
		s21.setTokens(ts21);
		
		List<Sentence> sents2 = Arrays.asList(s21);

		Edge e21 = new Edge();
		e21.setStartNode("S");
		e21.setEndNode("VP");
		Edge e22 = new Edge();
		e22.setStartNode("S");
		e22.setEndNode(".");
		Edge e23 = new Edge();
		e23.setStartNode("VP");
		e23.setEndNode("VB");
		Edge e24 = new Edge();
		e24.setStartNode("VP");
		e24.setEndNode("NP");
		Edge e25 = new Edge();
		e25.setStartNode("NP");
		e25.setEndNode("PRP");
		List<Edge> edges2 = Arrays.asList(e21, e22, e23, e24, e25);
		s21.setEdges(edges2);
		
		List<Dependency> d21 = new ArrayList<Dependency>();
		s21.setDependencies(d21);
				
		u2.setSentences(sents2);		

		scoredUtterance = new ScoredUtterance(u2);
		
		Sentence s31 = new Sentence();
		Token t31 = new Token();
		t31.setWord("all");
		Token t32 = new Token();
		t32.setWord("your");
		Token t33 = new Token();
		t33.setWord("bases");
		Token t34 = new Token();
		t34.setWord("belong");
		Token t35 = new Token();
		t35.setWord("to");
		Token t36 = new Token();
		t36.setWord("us");
		List<Token> ts31 = Arrays.asList(t31, t32, t33, t34, t35, t36);
		s31.setTokens(ts31);
		
		List<Sentence> sents3 = Arrays.asList(s31);
		nextUtterance.setSentences(sents3);
	}
	
	@Test
	public void testComputeLemmaSimilarity() {
				
		scoredUtterance = ScoringUtils.computeLemmaScore(request, scoredUtterance);
		assertTrue(scoredUtterance.getLemmaScore() != 0);
	}
	
	@Test
	public void testComputeWordSimilarity() {
		
		scoredUtterance = ScoringUtils.computeWordScore(request, scoredUtterance);
		assertTrue(scoredUtterance.getWordScore() != 0);
	}
	
	@Test
	 public void testComputePosSimilariy() {
		
		scoredUtterance = ScoringUtils.computePosScore(request, scoredUtterance);
		assertTrue(scoredUtterance.getPosScore() != 0);
	}
	
	@Test
	public void testComputeWordPosSimilarity() {
		
		scoredUtterance = ScoringUtils.computeWordPosScore(request, scoredUtterance);
		assertTrue(scoredUtterance.getWordPosScore() != 0);
	}
	
	@Test
	public void testComputeEdgeSimilarity() {
		scoredUtterance = ScoringUtils.computeEdgeScore(request, scoredUtterance);
		assertTrue(scoredUtterance.getEdgeScore() == 0);		
	}
	
	@Test
	public void testComputeDependencySimilarity() {
		scoredUtterance = ScoringUtils.computeDependencyScore(request, scoredUtterance);
		assertTrue(scoredUtterance.getDepScore() == 0);
	}
	
	@Test
	public void testComputeNerScore() {
		scoredUtterance = ScoringUtils.computeNerScore(request, scoredUtterance);
		assertTrue(scoredUtterance.getNerScore() == 0);
		
		scoredUtterance = ScoringUtils.computeNerScore(nullUtterance, scoredUtterance);
		assertTrue(scoredUtterance.getNerScore() == 0);
	}
	
	@Test
	public void testLengthScore() {
		scoredUtterance = ScoringUtils.computeLengthScore(request, nextUtterance, scoredUtterance);
		assertTrue(scoredUtterance.getLengthScore() == 1);
	}
}