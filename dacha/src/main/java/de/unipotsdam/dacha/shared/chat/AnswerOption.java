package de.unipotsdam.dacha.shared.chat;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AnswerOption implements IsSerializable {

	private int id;
	private String similarSentence;
	private String answer;
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getOverallScore() {
		return overallScore;
	}
	public void setOverallScore(double overallScore) {
		this.overallScore = overallScore;
	}
	public String getSimilarSentence() {
		return similarSentence;
	}
	public void setSimilarSentence(String similarSentence) {
		this.similarSentence = similarSentence;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
