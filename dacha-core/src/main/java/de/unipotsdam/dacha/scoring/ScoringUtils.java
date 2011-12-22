package de.unipotsdam.dacha.scoring;

import java.util.ArrayList;
import java.util.List;

import de.unipotsdam.dacha.types.Dependency;
import de.unipotsdam.dacha.types.Edge;
import de.unipotsdam.dacha.types.Sentence;
import de.unipotsdam.dacha.types.Token;
import de.unipotsdam.dacha.types.Utterance;

public class ScoringUtils {

	public static ScoredUtterance computeLemmaScore(Utterance request, ScoredUtterance dbUtterance) {
		
		List<Token> requestTokens = getTokens(request);
		List<Token> utTokens = getTokens(dbUtterance.getUtterance());
		
		List<String> utLemmata = new ArrayList<String>();
		for (Token tok : utTokens) {
			utLemmata.add(tok.getLemma());
		}
		
		int overlap = 0;
		
		for (Token requestTok : requestTokens) {
			if (utLemmata.contains(requestTok.getLemma())) {
				overlap++;
			}
		}
		
		double score = normalize(overlap, requestTokens, utTokens); 
		
		dbUtterance.setLemmaScore(score);
		return dbUtterance;
	}

	public static ScoredUtterance computeWordScore(Utterance request, ScoredUtterance dbUtterance) {

		List<Token> requestTokens = getTokens(request);
		List<Token> utTokens = getTokens(dbUtterance.getUtterance());
		
		List<String> utWords = new ArrayList<String>();
		for (Token tok : utTokens) {
			utWords.add(tok.getWord().toLowerCase());
		}
		
		int overlap = 0;
		
		for (Token requestTok : requestTokens) {
			if (utWords.contains(requestTok.getWord().toLowerCase())) {
				overlap++;
			}
		}
		
		double score = normalize(overlap, requestTokens, utTokens); 
		
		dbUtterance.setWordScore(score);		
		return dbUtterance;
	}

	public static ScoredUtterance computePosScore(Utterance request, ScoredUtterance dbUtterance) {

		List<Token> requestTokens = getTokens(request);
		List<Token> utTokens = getTokens(dbUtterance.getUtterance());
		
		List<String> utPosTags = new ArrayList<String>();
		for (Token tok : utTokens) {
			utPosTags.add(tok.getPos());
		}
		
		int overlap = 0;
		
		for (Token requestTok : requestTokens) {
			if (utPosTags.contains(requestTok.getPos())) {
				overlap++;
			}
		}
		
		double score = normalize(overlap, requestTokens, utTokens); 
		dbUtterance.setPosScore(score);
		
		return dbUtterance;
	}

	public static ScoredUtterance computeNerScore(Utterance nextUtterance, ScoredUtterance scoredUtterance) {

		double score = 0; 
		if (nextUtterance != null) {
			List<Token> utTokens = getTokens(nextUtterance);
			for (Token token : utTokens) {
				String ner = token.getNer();
				if ((ner != null) && (!ner.equals("O"))) {
					score++;
				}
			}
		}
		
		scoredUtterance.setNerScore(score);
		
		return scoredUtterance;
	}

	public static ScoredUtterance computeDependencyScore(Utterance request, ScoredUtterance dbUtterance) {

		List<Dependency> requestDeps = getDeps(request);
		List<Dependency> utDeps = getDeps(dbUtterance.getUtterance());
		
		List<String> deps = new ArrayList<String>();
		for (Dependency dep : utDeps) {
			deps.add(dep.getType());
		}
		
		double score = 0;
		
		int overlap = 0;
		if (!requestDeps.isEmpty() && !utDeps.isEmpty()) {
			for (Dependency requestTok : requestDeps) {
				if (deps.contains(requestTok.getType())) {
					overlap++;
				}
			}
			score = normalizeD(overlap, requestDeps, deps); 
		} else {
			score = 0;
		}
		
		dbUtterance.setDepScore(score);
		
		return dbUtterance;
	}

	public static ScoredUtterance computeEdgeScore(Utterance request, ScoredUtterance dbUtterance) {
		
		List<Edge> requestEdges = getEdges(request);
		List<Edge> utEdges = getEdges(dbUtterance.getUtterance());
		
		List<String> edges = new ArrayList<String>();
		for (Edge edge : utEdges) {
			edges.add(edge.getStartNode() + "_" + edge.getEndNode());
		}
		
		int overlap = 0;
		
		for (Edge requestEdge : requestEdges) {
			if (edges.contains(requestEdge.getStartNode() + "_" + requestEdge.getEndNode())) {
				overlap++;
			}
		}
		
		double score = normalizeEdges(overlap, requestEdges, edges); 
		
		dbUtterance.setEdgeScore(score);
		
		return dbUtterance;
	}

	public static ScoredUtterance computeWordPosScore(Utterance request, ScoredUtterance dbUtterance) {
		
		List<Token> requestTokens = prepareTokensWithWordPos(request);
		List<Token> utTokens = prepareTokensWithWordPos(dbUtterance.getUtterance());
		
		List<String> utWordPosTags = new ArrayList<String>();
		for (Token tok : utTokens) {
			utWordPosTags.add(tok.getWordPos());
		}
		
		int overlap = 0;
		
		for (Token requestTok : requestTokens) {
			if (utWordPosTags.contains(requestTok.getWordPos())) {
				overlap++;
			}
		}
		double score = normalize(overlap, requestTokens, utTokens);
		dbUtterance.setWordPosScore(score);
		
		return dbUtterance;
	}	

	private static List<Token> prepareTokensWithWordPos(Utterance request) {

		List<Token> tokens = getTokens(request);
		List<Token> tokensWithWordPos = new ArrayList<Token>();
		
		for (Token token : tokens) {
			if (token.getWordPos() == null) {
				token.setWordPos(token.getWord() + "_" + token.getPos());
				tokensWithWordPos.add(token);
			} else {
				tokensWithWordPos.add(token);
			}			
		}
		
		return tokensWithWordPos;
	}

	public static ScoredUtterance computeLengthScore(Utterance request, Utterance nextUtterance, ScoredUtterance scoredUtterance) {

		double score = 0;

		if (nextUtterance != null) {
			int countRequest = countTokens(request);
			int countNextUtterance = countTokens(nextUtterance);
			
			if (countRequest < countNextUtterance) {
				score = 1;
			} else {
				score = 0;
			}
		}
		
		scoredUtterance.setLengthScore(score);
		
		return scoredUtterance;
	}

	private static int countTokens(Utterance request) {
		
		int count = 0;
		List<Sentence> sents = request.getSentences();
		for (Sentence sentence : sents) {
			List<Token> tokens = sentence.getTokens();
			count += tokens.size();
		}
		
		return count;
	}

	private static List<Token> getTokens(Utterance utterance) {
		
		List<Token> allTokens = new ArrayList<Token>();
		
		List<Sentence> sents = utterance.getSentences();	
		for (Sentence sent : sents) {
			List<Token> tokens = sent.getTokens();
			allTokens.addAll(tokens);
		}
		
		return allTokens;
	}
	
	private static List<Dependency> getDeps(Utterance utterance) {

		List<Dependency> allDeps = new ArrayList<Dependency>();
		
		List<Sentence> sents = utterance.getSentences();	
		for (Sentence sent : sents) {
			List<Dependency> deps = sent.getDependencies();
			allDeps.addAll(deps);
		}
		
		return allDeps;
	}
	
	private static List<Edge> getEdges(Utterance utterance) {

		List<Edge> allEdges = new ArrayList<Edge>();
		
		List<Sentence> sents = utterance.getSentences();	
		for (Sentence sent : sents) {
			List<Edge> edges = sent.getEdges();
			allEdges.addAll(edges);
		}
		
		return allEdges;
	}
	
	private static double normalize(int overlap, List<Token> requestTokens,
			List<Token> utTokens) {
		
		double denominator = Math.sqrt(requestTokens.size()) * Math.sqrt(utTokens.size());
		
		return (double)overlap / denominator;
	}

	
	private static double normalizeD(int overlap, List<Dependency> requestDeps,
			List<String> utDeps) {

		double denominator = Math.sqrt(requestDeps.size()) * Math.sqrt(utDeps.size());
		
		return (double)overlap / denominator;
	}
	
	
	private static double normalizeEdges(int overlap, List<Edge> requestEdges,
			List<String> edges) {

		double denominator = Math.sqrt(requestEdges.size()) * Math.sqrt(edges.size());
		
		return (double)overlap / denominator;

	}
}
