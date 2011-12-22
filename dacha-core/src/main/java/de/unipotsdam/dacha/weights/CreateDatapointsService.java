package de.unipotsdam.dacha.weights;

import java.util.List;

public interface CreateDatapointsService {

	List<DataPoint> createDatapoints(List<DatabaseEntry> entries);

}