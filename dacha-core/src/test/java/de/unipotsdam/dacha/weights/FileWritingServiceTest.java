package de.unipotsdam.dacha.weights;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.unipotsdam.dacha.test.ContextAwareTest;

public class FileWritingServiceTest extends ContextAwareTest {

	@Autowired
	FileWritingService fileWritingService;
	
	@Test
	public void testWriteFile() {
		
		List<DataPoint> points = new ArrayList<DataPoint>();
		DataPoint point1 = new DataPoint();
		point1.setNegative(true);
		point1.setVector(Arrays.asList(1.0, 1.0, 0.0, 1.0));
		DataPoint point2 = new DataPoint();
		point2.setNegative(false);
		point2.setVector(Arrays.asList(0.0, 1.0, 1.0, 1.0));
		
		points.add(point1);
		points.add(point2);
		
		fileWritingService.writeFile(points);
	}

}
