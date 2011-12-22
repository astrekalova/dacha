package de.unipotsdam.dacha.scoring;

import de.unipotsdam.dacha.types.Utterance;

public class ScoredUtterance {

	private Utterance utterance;
	private double overallScore;
	
	private double wordScore;
	private double lemmaScore;
	private double posScore;
	private double wordPosScore;
	private double depScore;
	private double edgeScore;
	private double nerScore;
	private double lengthScore;
	private double historyScore;
	
	public ScoredUtterance(Utterance utterance) {
		this.utterance = utterance;
	}
	
	public Utterance getUtterance() {
		return utterance;
	}
	public void setUtterance(Utterance utterance) {
		this.utterance = utterance;
	}

	public double getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(double score) {
		this.overallScore = score;
	}

	@Override
	public String toString() {
		return "[utterance=" + utterance + ", overallScore="
				+ overallScore + "]";
	}

	public double getWordScore() {
		return wordScore;
	}

	public void setWordScore(double wordScore) {
		this.wordScore = wordScore;
	}

	public double getLemmaScore() {
		return lemmaScore;
	}

	public void setLemmaScore(double lemmaScore) {
		this.lemmaScore = lemmaScore;
	}

	public double getPosScore() {
		return posScore;
	}

	public void setPosScore(double posScore) {
		this.posScore = posScore;
	}

	public double getWordPosScore() {
		return wordPosScore;
	}

	public void setWordPosScore(double wordPosScore) {
		this.wordPosScore = wordPosScore;
	}

	public double getDepScore() {
		return depScore;
	}

	public void setDepScore(double depScore) {
		this.depScore = depScore;
	}

	public double getEdgeScore() {
		return edgeScore;
	}

	public void setEdgeScore(double edgeScore) {
		this.edgeScore = edgeScore;
	}

	public double getNerScore() {
		return nerScore;
	}

	public void setNerScore(double nerScore) {
		this.nerScore = nerScore;
	}

	public double getLengthScore() {
		return lengthScore;
	}

	public void setLengthScore(double lengthScore) {
		this.lengthScore = lengthScore;
	}

	public double getHistoryScore() {
		return historyScore;
	}

	public void setHistoryScore(double historyScore) {
		this.historyScore = historyScore;
	}	
}
