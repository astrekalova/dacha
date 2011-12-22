package de.unipotsdam.dacha.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "utterances")
public class Utterances {

	private List<Utterance> utterances = new ArrayList<Utterance>();

	@XmlElement(name = "utterance")
	public List<Utterance> getUtterances() {
		return utterances;
	}

	public void setUtterances(List<Utterance> utterances) {
		this.utterances = utterances;
	}
}
