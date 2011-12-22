package de.unipotsdam.dacha.scoring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import de.unipotsdam.dacha.types.Utterance;

@Service("scoringService")
public class ScoringServiceImpl implements ScoringService {	
	
	@Value("${weight.word}")
	private double wordScoreWeight;

	@Value("${weight.lemma}")
	private double lemmaScoreWeight;
	
	@Value("${weight.pos}")
	private double posScoreWeight;
	
	@Value("${weight.wordPos}")
	private double wordPosWeight;
	
	@Value("${weight.dep}")
	private double depScoreWeight;
	
	@Value("${weight.edge}")
	private double edgeScoreWeight;
	
	@Value("${weight.ner}")
	private double nerScoreWeight;
	
	@Value("${weight.length}")
	private double lengthScoreWeight;
	
	@Value("${weight.history}")
	private double historyScoreWeight;
	
	public ScoredUtterance scoreUtterance(Utterance request, ScoredUtterance scoredUtterance, Utterance nextUtterance) {
		
		ScoredUtterance u1 = ScoringUtils.computeWordScore(request, scoredUtterance);
		ScoredUtterance u2 = ScoringUtils.computeLemmaScore(request, u1);
		ScoredUtterance u3 = ScoringUtils.computePosScore(request, u2);
		ScoredUtterance u4 = ScoringUtils.computeWordPosScore(request, u3);
		ScoredUtterance u5 = ScoringUtils.computeDependencyScore(request, u4);
		ScoredUtterance u6 = ScoringUtils.computeEdgeScore(request, u5);
		ScoredUtterance u7 = ScoringUtils.computeNerScore(nextUtterance, u6);
		ScoredUtterance u8 = ScoringUtils.computeLengthScore(request, nextUtterance, u7);
		
		double overallScore = wordScoreWeight * u6.getWordScore() + 
			lemmaScoreWeight * u6.getLemmaScore() +
			posScoreWeight * u6.getPosScore() + 
			wordPosWeight * u6.getWordPosScore() +
			depScoreWeight * u6.getDepScore() +
			edgeScoreWeight * u6.getEdgeScore() + 
			nerScoreWeight * u8.getNerScore() +
			lengthScoreWeight * u8.getLengthScore();
		
		u8.setOverallScore(overallScore);
		
		return u8;
	}
	
	public double scoreHistory(Utterance preLastUtterance, Utterance lastUtterance,
			Utterance lastRequestUtterance, Utterance lastResponseUtterance) {
		
		double historyScore = 0;
		
		if (lastRequestUtterance != null && preLastUtterance != null) {
			ScoredUtterance scoredUtterance = new ScoredUtterance(lastRequestUtterance);
			ScoredUtterance updatedUtterance = scoreUtterance(preLastUtterance, scoredUtterance, null);
			historyScore += updatedUtterance.getOverallScore();
		}
		
		if (lastResponseUtterance != null && lastUtterance != null) {
			ScoredUtterance scoredUtterance = new ScoredUtterance(lastRequestUtterance);
			ScoredUtterance updatedUtterance = scoreUtterance(lastUtterance, scoredUtterance, null);
			historyScore += updatedUtterance.getOverallScore();
		}
		
		return historyScoreWeight * historyScore;
	}
}
