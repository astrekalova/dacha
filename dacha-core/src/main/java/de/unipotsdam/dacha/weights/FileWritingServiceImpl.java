package de.unipotsdam.dacha.weights;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("fileWritingService")
public class FileWritingServiceImpl implements FileWritingService {

	public void writeFile(List<DataPoint> points) {
		
		try {
		    BufferedWriter out = new BufferedWriter(new FileWriter("model/train.txt"));
		    
		    for (DataPoint point : points) {
		    	if (point.isNegative()) {
		    		out.write("-1" + " ");
		    	} else {
		    		out.write("+1" + " ");
		    	}
		    	
		    	List<Double> values = point.getVector();
		    	
		    	for (int i = 0; i < values.size(); i++) {
		    		if (values.get(i) > 0) {
		    			out.write((i+1) + ":" + values.get(i).toString() + " ");
		    		} 
		    	}
		    	out.write("\n");
		    }
		    
		    out.close();
		} catch (IOException e) {
		}		
	}
}