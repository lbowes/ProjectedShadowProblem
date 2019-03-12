package graphics;

import internal.Vector;
import java.util.ArrayList;

import internal.Envelope;

// - Responsible for observing the output of of the internal logic and presenting it to the user in some form (console output initially)
// - Should not modify any internal state
// - Purely functions as a layer on top of internal
public class Visualisation {
	
	private ConsoleRasteriser mConsoleRasteriser; 
	private Vector mOrigin = new Vector(1, 1);
	
	public Visualisation(Envelope data) 
	{ 
		mConsoleRasteriser = new ConsoleRasteriser(new Vector(100, 50));

		assemblePlot(data);
	}	
	
	public String toString() 
		// Responsible for providing the entire console output as a single string
	{
		return mConsoleRasteriser.toString();
	}
	
	private void assemblePlot(Envelope e) 
		// Logic for building the output string from the input Envelope 
	{
		// Add lines and points
		// temp - the set of example output points given in first example. This data should eventually come from the Envelope e passed in.
		ArrayList<Vector> data = new ArrayList<Vector>();
		data.add(new Vector(1, 11));
		data.add(new Vector(3, 13));
		data.add(new Vector(9, 0));
		data.add(new Vector(12, 7));
		data.add(new Vector(16, 3));
		data.add(new Vector(19, 18));
		data.add(new Vector(22, 3));
		data.add(new Vector(25, 0)); //25, 0
		//
		
		// TODO:
		//drawAxes();
		//drawLabels(data);
		//drawEnvelope(data);
		
		final Vector dims = mConsoleRasteriser.getDims();
		for(int x = 0; x < dims.x; x++) {
			for(int y = 0; y < dims.y; y++) {
				mConsoleRasteriser.drawPoint(new Vector(x, y), '.');
			}
		}
	}
	
	private void drawAxes() {
		// TODO:
	}
	
	private void drawLabels(ArrayList<Vector> data) {
		
	}
	
	private void drawEnvelope(ArrayList<Vector> data) {
		// TODO
	}
	
	private Vector calcUpperBound(ArrayList<Vector> data) {
		Vector upperBound = new Vector(0, 0);
		
		for(Vector v : data) {
			if(v.x >= upperBound.x)
				upperBound.x = v.x;
			if(v.y >= upperBound.y)
				upperBound.y = v.y;
		}
		
		return upperBound;
	}
	
}
