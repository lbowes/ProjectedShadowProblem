package graphics;

import internal.Vector;
import java.util.Arrays;

// - Contains drawing logic used to display the output envelop
// - Bottom left grid position represents (0, 0)
public class ConsoleRasteriser {

	// Internal state of the grid
	private char[] mGrid;  
	
	// Grid dimensions
	private Vector mGridDims;
	
	public ConsoleRasteriser(Vector dims_grid) 
		// dims should be logical dimensions of grid (ignoring 2x scale factor on x)
	{
		mGridDims = toInternalSpace(dims_grid); 
		
		// Grid data will be stored internally in 1D, but should be accessed intuitively with x and y 
		mGrid = new char[mGridDims.x * mGridDims.y];
		clear('.');
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
	
	// ------------------------ DRAWING FUNCTIONS ------------------------
	public void drawVerticalLine(Vector startPos_grid, int expanse_grid, char newValue) 
		// expanse_grid is either positive/negative and indicates how far the line goes up or down
	{
		// This class will be responsible for storing the graphical representation, modified from above by Visualisation
		// Once drawing is completed, the result can be implicitly extracted with toString()
		
		final Vector startPos_internal = toInternalSpace(startPos_grid); 
		if (!withinGrid(startPos_internal)) 
			throw new IllegalArgumentException("ConsoleRasteriser: drawVerticalLine() tried to draw from point outside grid: (" + startPos_grid.x + ", " + startPos_grid.y + ")");
		else if(expanse_grid != 0)
		{
			// line should be drawn down
			if(expanse_grid < 0) 
			{
				// Line might collide with edge of grid before covering full length
				final int maxExpanse = Math.max(0, startPos_internal.y - expanse_grid);
				for(int i = startPos_internal.y; i > maxExpanse; i--) 
					setPixel(new Vector(startPos_internal.x, i), newValue);
			}
			// line should be drawn up
			else
			{
				final int maxExpanse = Math.min(mGridDims.y, startPos_internal.y + expanse_grid);
				for(int i = startPos_internal.y; i < maxExpanse; i++) 
					setPixel(new Vector(startPos_internal.x, i), newValue);
			}
		}
	}

	public void drawHorizontalLine(Vector startPos_grid, int expanse_grid, char newValue) 
		// see drawVerticalLine
	{
		final Vector startPos_internal = toInternalSpace(startPos_grid);
		if (!withinGrid(startPos_internal)) 
			throw new IllegalArgumentException("ConsoleRasteriser: drawHorizontalLine() tried to draw from point outside grid: (" + startPos_grid.x + ", " + startPos_grid.y + ")");
		else if(expanse_grid != 0)
		{
			// line should be drawn left
			if(expanse_grid < 0) 
			{
				// Line might collide with edge of grid before covering full length
				final int maxExpanse = Math.max(0, startPos_internal.x - expanse_grid * 2);
				for(int i = startPos_internal.x; i > maxExpanse; i -= 2) 
					setPixel(new Vector(i, startPos_internal.y), newValue);
			}
			// line should be drawn right
			else
			{
				final int maxExpanse = Math.min(mGridDims.x, startPos_internal.x + expanse_grid * 2);
				for(int i = startPos_internal.x; i < maxExpanse; i += 2) 
					setPixel(new Vector(i, startPos_internal.y), newValue);
			}
		}
	}

	public void drawPoint(Vector pos_grid, char newValue) {
		setPixel(toInternalSpace(pos_grid), newValue);
	}

	public void addText(Vector pos_grid, String text)
	{
		final Vector startPos_internal = toInternalSpace(pos_grid);
		if (!withinGrid(startPos_internal)) 
			throw new IllegalArgumentException("ConsoleRasteriser: addText() tried to draw from point outside grid: (" + pos_grid.x + ", " + pos_grid.y + ")");
		else if(text.length() > 0)
		{
			final int maxExpanse = Math.min(mGridDims.x, startPos_internal.x + text.length());
			for(int i = startPos_internal.x; i < maxExpanse; i++) 
				setPixel(new Vector(i, startPos_internal.y), text.charAt(i - startPos_internal.x));
		}
	}
	// -------------------------------------------------------------------
	
	private void clear(char background) 
	{
		Arrays.fill(mGrid, background);
	}
		
	private void setPixel(Vector pos, char newValue) 
	{
		if (!withinGrid(pos))
			throw new IllegalArgumentException("ConsoleRasteriser: setPixel() tried to access point outside grid: (" + pos.x + ", " + pos.y + ")");
		else
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
	
	private Vector toInternalSpace(Vector pos_grid) 
		// Each ASCII character is almost twice as high as it is wide.
		// For the grid to contain positions at regular square intervals, each row must be interleaved with spaces.
		// e.g. '%%%%%' becomes '% % % % % %'.
		// This means the size of the implemented grid must be twice as large as it is logically defined to be.
		// e.g. The width of a (logically) 50x50 grid will be 100.
		// This function takes in a position in logical grid space, and returns the correct position in the actual array.
	{
		return new Vector(pos_grid.x * 2, pos_grid.y);
	}
	
}
