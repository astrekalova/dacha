package de.unipotsdam.dacha.weights;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.unipotsdam.dacha.test.TransactionAwareTest;

public class ModelTrainingServiceTest extends TransactionAwareTest {

	@Autowired
	ModelTrainingService modelTrainingService;
	
	@Test
	public void testTrainModel() {

		List<DataPoint> points = modelTrainingService.trainModel();
		assertTrue(!points.isEmpty());
	}

}
