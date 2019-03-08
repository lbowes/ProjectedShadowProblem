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
		// Add axes
		final Vector origin = new Vector(1, 1);
		mConsoleRasteriser.drawVerticalLine(new Vector(1, 1),  100,  '.');
		mConsoleRasteriser.drawHorizontalLine(new Vector(1, 1),  100,  '.');
		
		// Add labels
		mConsoleRasteriser.addText(new Vector(0, 1),  "0");
		mConsoleRasteriser.addText(new Vector(1, 0),  "0");
		
		// TODO: Use the number of points in the envelope to find the upper bound on the labels
		// and add them at regular intervals.
		
		// Sine waves
		for(int i = origin.x + 1; i < mConsoleRasteriser.getDims().x / 2 - 1; i++) 
		{
			final Vector a = origin.add(new Vector(i, (int)((Math.sin(i * 0.25) + 1.0) / 0.1)));
			for(int j = a.y; j > origin.y; j--)
				mConsoleRasteriser.drawPoint(new Vector(i, j),  '¬');
			
			final Vector b = origin.add(new Vector(i, (int)((Math.sin(i * 0.18) + 1.0) / 0.2)));
			for(int j = b.y; j > origin.y; j--)
				mConsoleRasteriser.drawPoint(new Vector(i, j),  '2');
		}
	}
	
}
