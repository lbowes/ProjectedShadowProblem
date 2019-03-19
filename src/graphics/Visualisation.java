package graphics;

import internal.Vector;
import java.util.ArrayList;

import internal.Envelope;

// - Responsible for observing the output of of the internal logic and presenting it to the user in some form (console output)
// - Should not modify any internal state
// - Purely functions as a layer on top of internal domain state
public class Visualisation {
	
	private ConsoleRasteriser mConsoleRasteriser;
	
	// As defined by sample points provided in lastPracticalChallenge.pdf
	// Limits are inclusive
	private Vector 
		mLowerVertexLimit = new Vector(0),
		mUpperVertexLimit = new Vector(29);
	
	public Visualisation(Envelope data) 
	{ 
		// temp - the set of example output points given in first example. This data should eventually come from the Envelope 'data' passed in, with data.getVertices()
		ArrayList<Vector> tempVertices = new ArrayList<Vector>();
		tempVertices.add(new Vector(1, 11));
		tempVertices.add(new Vector(3, 13));
		tempVertices.add(new Vector(9, 0));
		tempVertices.add(new Vector(12, 7));
		tempVertices.add(new Vector(16, 3));
		tempVertices.add(new Vector(19, 18));
		tempVertices.add(new Vector(22, 3));
		tempVertices.add(new Vector(25, 0)); //25, 0
		//
				
		final Vector 
			minVertex = getMinVertexBound(tempVertices), // data.getVertices()
			maxVertex = getMaxVertexBound(tempVertices); // data.getVertices()
		
		if(vertexRangeValid(minVertex, maxVertex)) {
			// Dimensions of mConsoleRasteriser account for labels, axes, label padding and x axis scaling
			// See example output on p4 of lastPracticalChallenge.pdf
			mConsoleRasteriser = new ConsoleRasteriser(new Vector(3 + (maxVertex.x + 3) * 2, 1 + maxVertex.y + 2));
			assemblePlot(tempVertices, maxVertex);
		}
		else {
			System.out.println("Lower: " + minVertex.x + ", " + minVertex.y);
			System.out.println("Upper: " + maxVertex.x + ", " + maxVertex.y);
			throw new IllegalArgumentException("Output vertices must all be between (" + mLowerVertexLimit.x + ", " + mLowerVertexLimit.y + ") and (" + mUpperVertexLimit.x + ", " + mUpperVertexLimit.y + ") (inclusive)");
		}
	}	
	
	public String toString() 
		// Responsible for providing the entire console output as a single string
	{
		return mConsoleRasteriser.toString();
	}
	
	private void assemblePlot(ArrayList<Vector> vertices, Vector maxVertexBound) 
		// Logic for building the output string from the input Envelope 
	{
		drawAxes();
		drawLabels(maxVertexBound);
		//drawEnvelope(data);
	}
	
	private void drawAxes() {
		final Vector dims = mConsoleRasteriser.getDims();
		
		// Vertical axis
		for(int y = 1; y < dims.y; y++)
			mConsoleRasteriser.drawPoint(new Vector(3, y), '.');
			
		// Horizontal axis
		for(int x = 3; x < dims.x; x += 2)
			mConsoleRasteriser.drawPoint(new Vector(x, 1), '.');
	}
	
	private void drawLabels(Vector maxVertexBound) {
		final Vector dims = mConsoleRasteriser.getDims();
		
		// Vertical axis
		for(int y = 0; y < dims.y; y++) {
			if(y % 5 == 0 || y == maxVertexBound.y) {
				final int labelPadding = y < 10 ? 1 : 0;
				mConsoleRasteriser.addText(new Vector(labelPadding, 1 + y), Integer.toString(y));
			}
		}
			
		// Horizontal axis
		for(int x = 0; x < dims.x; x++) {
			if(x * 2 % 10 == 0 || x * 2 == maxVertexBound.x) {
				mConsoleRasteriser.addText(new Vector(3 + x * 2, 0), Integer.toString(x));
				
				if(x != 0)
					mConsoleRasteriser.drawPoint(new Vector(3 + x * 2, 1), ':');
			}
		}
	}
	
	private void drawEnvelope(ArrayList<Vector> data) {
		// TODO
	}
	
	private boolean vertexRangeValid(Vector minVertex, Vector maxVertex) {
		return 
			minVertex.x >= mLowerVertexLimit.x && minVertex.x <= mUpperVertexLimit.x &&
			minVertex.y >= mLowerVertexLimit.y && minVertex.y <= mUpperVertexLimit.y &&
			maxVertex.x >= mLowerVertexLimit.x && maxVertex.x <= mUpperVertexLimit.x &&
			maxVertex.y >= mLowerVertexLimit.y && maxVertex.y <= mUpperVertexLimit.y;
	}
	
	private Vector getMinVertexBound(ArrayList<Vector> vertices) {
		Vector min = new Vector(0, 0);
		
		for(int i = 0; i < vertices.size(); i++) {
			final Vector v = vertices.get(i);
			
			if(i == 0) {
				min = v;
				continue;
			}
				
			if(v.x < min.x)
				min.x = v.x;
			if(v.y < min.y)
				min.y = v.y;
		}
		
		return min;
	}
	
	private Vector getMaxVertexBound(ArrayList<Vector> vertices) {
		Vector max = new Vector(0, 0);
		
		for(int i = 0; i < vertices.size(); i++) {
			final Vector v = vertices.get(i);
			
			if(i == 0) {
				max = v;
				continue;
			}
				
			if(v.x > max.x)
				max.x = v.x;
			if(v.y > max.y)
				max.y = v.y;
		}
		
		return max;
	}
	
}
