package de.unipotsdam.dacha.core;

import java.util.ArrayList;
import java.util.List;

import de.unipotsdam.dacha.scoring.ScoredUtterance;

public class Response {

	private String responseString;
	private List<ScoredUtterance> responseUtterances = new ArrayList<ScoredUtterance>();
	
	public String getResponseString() {
		return responseString;
	}
	
	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	
	public List<ScoredUtterance> getResponseUtterances() {
		return responseUtterances;
	}
	
	public void setResponseUtterances(List<ScoredUtterance> similarStrings) {
		this.responseUtterances = similarStrings;
	}
	
	public int getIdForUtterance(ScoredUtterance scoredUtterance) {
		if (responseUtterances.contains(scoredUtterance)) {
			return responseUtterances.indexOf(scoredUtterance);
		} else {
			return -1;
		}
	}
	
	@Override
	public String toString() {
		return "Response [responseString=" + responseString
		+ ", similarStrings=" + responseUtterances + "]";
	}
}
