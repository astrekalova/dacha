package de.unipotsdam.dacha.types;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Token {

	private String word;
	private String lemma;
	private String pos;
	private String ner;
	private String wordPos;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getLemma() {
		return lemma;
	}
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getNer() {
		return ner;
	}
	public void setNer(String ner) {
		this.ner = ner;
	}
	public String getWordPos() {
		return wordPos;
	}
	public void setWordPos(String wordPos) {
		this.wordPos = wordPos;
	}
	@Override
	public String toString() {
		return "Token [word=" + word + ", lemma=" + lemma + ", pos=" + pos
				+ ", ner=" + ner + ", wordPos=" + wordPos + "]";
	}
}
