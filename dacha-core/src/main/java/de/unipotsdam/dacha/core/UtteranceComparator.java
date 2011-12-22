package de.unipotsdam.dacha.core;

import java.util.Comparator;

import de.unipotsdam.dacha.scoring.ScoredUtterance;

public class UtteranceComparator implements Comparator<ScoredUtterance> {

	public static Comparator<? super ScoredUtterance> INSTANCE = new UtteranceComparator();
	
	@Override
	public int compare(ScoredUtterance utterance1, ScoredUtterance utterance2) {
		
		double overlap1 = utterance1.getOverallScore();
		double overlap2 = utterance2.getOverallScore();
		
		if (overlap1 < overlap2) {
			return 1;
		} else if (overlap1 == overlap2) {
			return 0;
		} else {
			return -1;
		}
	}

}
