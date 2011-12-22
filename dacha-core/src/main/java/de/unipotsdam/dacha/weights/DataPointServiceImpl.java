package de.unipotsdam.dacha.weights;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import de.unipotsdam.dacha.scoring.ScoredUtterance;

@Service("dataPointService")
public class DataPointServiceImpl implements DataPointService{

	public DataPoint makeDatapoint(ScoredUtterance utterance){
		
		DataPoint point = new DataPoint();
		List<Double> vector = new ArrayList<Double>();
		
		vector.add(utterance.getDepScore());
		vector.add(utterance.getEdgeScore());
		vector.add(utterance.getLemmaScore());
		vector.add(utterance.getLengthScore());
		vector.add(utterance.getNerScore());
		vector.add(utterance.getPosScore());
		vector.add(utterance.getWordPosScore());
		vector.add(utterance.getWordScore());
		
		point.setVector(vector);
		
		return point;
	}
}
