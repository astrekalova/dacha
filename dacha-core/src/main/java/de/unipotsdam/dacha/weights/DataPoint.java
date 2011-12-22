package de.unipotsdam.dacha.weights;

import java.util.List;

public class DataPoint {

	private List<Double> vector;
	private boolean negative;
	
	public List<Double> getVector() {
		return vector;
	}
	public void setVector(List<Double> vector) {
		this.vector = vector;
	}
	public boolean isNegative() {
		return negative;
	}
	public void setNegative(boolean negative) {
		this.negative = negative;
	}
}
