package de.unipotsdam.dacha.weights;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.unipotsdam.dacha.scoring.ScoredUtterance;
import de.unipotsdam.dacha.scoring.ScoringService;
import de.unipotsdam.dacha.types.Utterance;
import de.unipotsdam.dacha.utterance.UtteranceMakingService;

@Service("createDatapointsService")
public class CreateDatapointsServiceImpl implements CreateDatapointsService {
	
	@Autowired
	public UtteranceMakingService utteranceMakingService;
	@Autowired
	public ScoringService scoringService;
	@Autowired
	public DataPointService dataPointService;

	public List<DataPoint> createDatapoints(List<DatabaseEntry> entries) {

		List<DataPoint> points = new ArrayList<DataPoint>();
		
		for (DatabaseEntry entry : entries) {
			Utterance requestUtterance = utteranceMakingService.makeUtterance(entry.getRequest());
			Utterance matchUtterance = utteranceMakingService.makeUtterance(entry.getMatch());
			Utterance responseUtterance = utteranceMakingService.makeUtterance(entry.getResponse());
			matchUtterance.setNext(entry.getResponse());
			
			ScoredUtterance scoredUtterance = 
				scoringService.scoreUtterance(requestUtterance, new ScoredUtterance(matchUtterance), responseUtterance);
			DataPoint dataPoint = dataPointService.makeDatapoint(scoredUtterance);
			if (entry.isNegative()) {
				dataPoint.setNegative(true);
			} else if (!entry.isNegative()) {
				dataPoint.setNegative(false);
			}
			
			points.add(dataPoint);
		}
		
		return points;
	}	
}