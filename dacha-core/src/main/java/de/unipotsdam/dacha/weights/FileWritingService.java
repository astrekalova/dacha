package de.unipotsdam.dacha.weights;

import java.util.List;

public interface FileWritingService {

	void writeFile(List<DataPoint> points);

}