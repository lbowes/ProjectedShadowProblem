package graphics;

import java.util.ArrayList;

import internal.Envelop;

// - Responsible for observing the output of of the internal logic and presenting it to the user in some form (console output initially)
// - Should not modify any internal state
// - Purely functions as a layer on top of internal
public class Visualisation {
	
	private ConsoleRasteriser mConsoleRasterizer;
	
	Visualisation(Envelop e) { 
		assembleString(e);
	}	
	
	public String toString() 
		// Responsible for providing the entire console output as a single string
	{
		return mConsoleRasterizer.toString();
	}
	
	private void assembleString(Envelop e) 
		// Logic for building the output string from the input Envelop 
	{
		// Uses ConsoleRasteriser functions to draw the output grid
		// eg. mConsoleRasteriser.drawLine(7, 9, -6) for a line starting at (7, 9) and going 10 spaces down.
		
		
	}
	
}
