package graphics;

import internal.Vector;
import java.util.ArrayList;

import internal.Envelope;

// - Responsible for observing the output of of the internal logic and presenting it to the user in some form (console output initially)
// - Should not modify any internal state
// - Purely functions as a layer on top of internal
public class Visualisation {
	
	private ConsoleRasteriser mConsoleRasteriser;
	
	public Visualisation(Envelope data, Vector gridSize) 
	{ 
		mConsoleRasteriser = new ConsoleRasteriser(gridSize);

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
		data.add(new Vector(25, 0));
		//
		
		drawAxes();
		drawLabels(data);
		drawEnvelope(data);
	}
	
	private void drawAxes() {
		// Add axes
		final Vector 
			origin = new Vector(1, 1),
			dims_grid = mConsoleRasteriser.getDims_grid();
		
		mConsoleRasteriser.drawVerticalLine(origin,  dims_grid.y,  '.');
		mConsoleRasteriser.drawHorizontalLine(origin,  dims_grid.x,  '.');
	}
	
	private void drawLabels(ArrayList<Vector> data) {
		// 1. Compute data bounds
		final Vector upperBound = calcUpperBound(data);
		
		// 2. Add labels
		final Vector dims = mConsoleRasteriser.getDims_grid();

		// TODO: Sometimes the axes do not display a range far enough to include the upper bound points.
		// Make sure that the upper bound point is always displayed.
		final int 
			numLabelsPerAxis = 11,
			largestDim = Math.max(dims.x, dims.y),
			labelStepSize = (int)(largestDim / (float)numLabelsPerAxis);
		
		for(int x = 0; x <= dims.x; x += labelStepSize)
			mConsoleRasteriser.addText(new Vector(x, 0), Integer.toString((int)((float)x / dims.x * upperBound.x)));
		
		for(int y = 0; y <= dims.y; y += labelStepSize)
			mConsoleRasteriser.addText(new Vector(0, y), Integer.toString((int)((float)y / dims.y * upperBound.y)));
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
