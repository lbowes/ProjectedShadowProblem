package graphics;

import internal.Vector;
import java.util.Arrays;

// - Contains drawing logic used to display the output envelop
// - Bottom left grid position represents (0, 0)
public class ConsoleRasteriser {

	// Internal state
	private char[] mGrid;  
	
	// Grid dimensions
	private Vector mGridDims;
	
	public ConsoleRasteriser(Vector dims) {
		mGridDims = dims;
				
		// Grid data will be stored internally in 1D, but should be accessed intuitively with x and y 
		mGrid = new char[mGridDims.x * mGridDims.y];
		clear(' ');
	}
	
	public String toString() {
		String output = "";
		
		for (int y = 0; y < mGridDims.y; y++) 
		{
			for (int x = 0; x < mGridDims.x; x++) 
			{
				// Grid is reflected vertically for drawing so that (0, 0) is at the bottom left
				output += getPixel(new Vector(x, mGridDims.y - y - 1));
			}
			output += "\n";
		}
		
		return output;
	}
	
	public Vector getDims()
	{
		return mGridDims;
	}
	
	// ------------------------ DRAWING FUNCTIONS ------------------------
	public void drawVerticalLine(Vector startPos, int expanse, char newValue) 
		// expanse_grid is either positive/negative and indicates how far the line goes up or down
	{		 
		if (!withinGrid(startPos)) 
			throw new IllegalArgumentException("ConsoleRasteriser: drawVerticalLine() tried to draw from point outside grid: (" + startPos.x + ", " + startPos.y + ")");
		else if(expanse != 0)
		{
			// line should be drawn down
			if(expanse < 0) 
			{
				// Line might collide with edge of grid before covering full length
				final int maxExpanse = Math.max(0, startPos.y - expanse);
				for(int i = startPos.y; i > maxExpanse; i--) 
					setPixel(new Vector(startPos.x, i), newValue);
			}
			// line should be drawn up
			else
			{
				final int maxExpanse = Math.min(mGridDims.y, startPos.y + expanse);
				for(int i = startPos.y; i < maxExpanse; i++) 
					setPixel(new Vector(startPos.x, i), newValue);
			}
		}
	}

	public void drawHorizontalLine(Vector startPos, int expanse, String pattern) 
		// see drawVerticalLine
	{
		if (!withinGrid(startPos)) 
			throw new IllegalArgumentException("ConsoleRasteriser: drawHorizontalLine() tried to draw from point outside grid: (" + startPos.x + ", " + startPos.y + ")");
		else if(expanse != 0)
		{
			// line should be drawn left
			if(expanse < 0) 
			{
				// Line might collide with edge of grid before covering full length
				final int maxExpanse = Math.max(0, startPos.x - expanse);
				for(int i = startPos.x; i > maxExpanse; i--) 
					setPixel(new Vector(i, startPos.y), pattern.charAt(i % pattern.length()));
			}
			// line should be drawn right
			else
			{
				final int maxExpanse = Math.min(mGridDims.x, startPos.x + expanse * 2);
				for(int i = startPos.x; i < maxExpanse; i++) 
					setPixel(new Vector(i, startPos.y), pattern.charAt(i % pattern.length()));
			}
		}
	}

	public void drawPoint(Vector pos, char newValue) {
		setPixel(pos, newValue);
	}

	public void addText(Vector pos, String text)
	{
		if (withinGrid(pos) && text.length() > 0) {
			final int maxExpanse = Math.min(mGridDims.x, pos.x + text.length());
			for(int i = pos.x; i < maxExpanse; i++) 
				setPixel(new Vector(i, pos.y), text.charAt(i - pos.x));
		}
	}
	// -------------------------------------------------------------------
	
	private void clear(char background) 
	{
		Arrays.fill(mGrid, background);
	}
		
	private void setPixel(Vector pos, char newValue) 
	{
		if (withinGrid(pos))
			mGrid[pos.y * mGridDims.x + pos.x] = newValue;
	}
	
	private char getPixel(Vector pos) 
	{
		if (!withinGrid(pos))
			throw new IllegalArgumentException("ConsoleRasteriser: getPixel() tried to access point outside grid: (" + pos.x + ", " + pos.y + ")");
		else
			return mGrid[pos.y * mGridDims.x + pos.x];
	}
	
	private boolean withinGrid(Vector query) 
	{
		return query.x >= 0 && query.x < mGridDims.x && query.y >= 0 && query.y < mGridDims.y;
	}
	
}
