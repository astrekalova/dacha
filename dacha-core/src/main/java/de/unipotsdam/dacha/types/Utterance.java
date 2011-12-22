package de.unipotsdam.dacha.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Utterance {

	private String next;

	private String value;

	private List<Sentence> sentences = new ArrayList<Sentence>();

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@XmlElementWrapper(name = "sentences")
	@XmlElement(name = "sentence")
	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	@Override
	public String toString() {
		return "value=" + value + "next: " + next;
	}
}
