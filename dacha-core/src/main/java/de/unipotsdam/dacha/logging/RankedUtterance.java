package de.unipotsdam.dacha.logging;

import de.unipotsdam.dacha.scoring.ScoredUtterance;

public class RankedUtterance {

	private ScoredUtterance scoredUtterance;
	
	private int ranking;

	public ScoredUtterance getScoredUtterance() {
		return scoredUtterance;
	}

	public void setScoredUtterance(ScoredUtterance utterance) {
		this.scoredUtterance = utterance;
	}

	public int getId() {
		return ranking;
	}

	public void setId(int id) {
		this.ranking = id;
	}
}
