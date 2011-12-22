package de.unipotsdam.dacha.weights;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("modelTrainingService")
public class ModelTrainingServiceImpl implements ModelTrainingService {

	@Autowired
	private DatabaseEntryRetrievalService databaseEntryRetrievalService;
	@Autowired
	private CreateDatapointsService createDatapointsService;
	@Autowired
	private FileWritingService fileWritingService;

	@Override
	public List<DataPoint> trainModel() {
		
		List<DatabaseEntry> entries = databaseEntryRetrievalService.retrieveDatabaseEntries();
		List<DataPoint> points = createDatapointsService.createDatapoints(entries);
		fileWritingService.writeFile(points);
		return points;
	}	
}
