package de.unipotsdam.dacha.weights;

public class DatabaseEntry {

	private String request;	
	private String match;
	private String response;
	private boolean negative;
	
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public boolean isNegative() {
		return negative;
	}
	public void setNegative(boolean negative) {
		this.negative = negative;
	}
	
}
