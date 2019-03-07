package graphics;

import internal.Vector;
import java.util.Arrays;

// -Contains drawing logic used to display the output envelop
// - Bottom left grid position represents (0, 0)
public class ConsoleRasteriser {

	// Internal state of the grid (some big block of characters, 1D array indexed as 2D?)
	private char[] mGridData;  
	private Vector mGridDimensions;
	
	public ConsoleRasteriser(Vector dims_grid) 
	{
		this.mGridDimensions = dims_grid; 
		
		// Grid data will be stored internally in 1D, but should be accessed intuitively with x and y 
		mGridData = new char[dims_grid.x * dims_grid.y];
		clear('.');
	}
	
	// temp
	public void print() 
	{
		System.out.println(this);
	}
	//
	
	public String toString() {
		String output = "";
		
		for (int y = 0; y < mGridDimensions.y; y++) 
		{
			for (int x = 0; x < mGridDimensions.x; x++) 
			{
				// Grid is reflected vertically for drawing so that (0, 0) is at the bottom left
				output += getPixel(new Vector(x, mGridDimensions.y - y - 1));
			}
			output += "\n";
		}
		
		return output;
	}
	
	public void drawVerticalLine(Vector startPos_grid, int expanse_grid, char newValue) 
		// expanse_grid is either positive/negative and indicates how far the line goes up or down
	{
		// TODO: modifies the state of mGridData
		// This class will be responsible for storing the graphical representation, modified from above by Visualisation
		// Once drawing is completed, the result can be extracted with toString()
		
		if (!withinGrid(startPos_grid)) 
			throw new IllegalArgumentException("ConsoleRasteriser: drawVerticalLine() tried to draw from point outside grid: (" + startPos_grid.x + ", " + startPos_grid.y + ")");
		else if(expanse_grid != 0)
		{
			// line should be drawn left
			if(expanse_grid < 0) 
			{
				// Line might collide with edge of grid before covering full length
				final int maxExpanse = Math.max(0, startPos_grid.y - expanse_grid);
				for(int i = startPos_grid.y; i > maxExpanse; i--) 
					setPixel(new Vector(startPos_grid.x, i), newValue);
			}
			// line should be drawn right
			else
			{
				final int maxExpanse = Math.min(mGridDimensions.y, startPos_grid.y + expanse_grid);
				for(int i = startPos_grid.y; i < maxExpanse; i++) 
					setPixel(new Vector(startPos_grid.x, i), newValue);
			}
		}
	}

	// TODO: should be private
	public void drawHorizontalLine(Vector startPos_grid, int expanse_grid, char newValue) 
	{
		if (!withinGrid(startPos_grid)) 
			throw new IllegalArgumentException("ConsoleRasteriser: drawHorizontalLine() tried to draw from point outside grid: (" + startPos_grid.x + ", " + startPos_grid.y + ")");
		else if(expanse_grid != 0)
		{
			// line should be drawn left
			if(expanse_grid < 0) 
			{
				// Line might collide with edge of grid before covering full length
				final int maxExpanse = Math.max(0, startPos_grid.x - expanse_grid);
				for(int i = startPos_grid.x; i > maxExpanse; i--) 
					setPixel(new Vector(i, startPos_grid.y), newValue);
			}
			// line should be drawn right
			else
			{
				final int maxExpanse = Math.min(mGridDimensions.x, startPos_grid.x + expanse_grid);
				for(int i = startPos_grid.x; i < maxExpanse; i++) 
					setPixel(new Vector(i, startPos_grid.y), newValue);
			}
		}
	}

	private void clear(char background) {
		Arrays.fill(mGridData, background);
	}
		
	private void setPixel(Vector pos_grid, char newValue) 
	{
		if (!withinGrid(pos_grid))
			throw new IllegalArgumentException("ConsoleRasteriser: setPixel() tried to access point outside grid: (" + pos_grid.x + ", " + pos_grid.y + ")");
		else
			mGridData[pos_grid.y * mGridDimensions.x + pos_grid.x] = newValue;
	}
	
	private char getPixel(Vector pos_grid) 
	{
		if (!withinGrid(pos_grid))
		{
			throw new IllegalArgumentException("ConsoleRasteriser: getPixel() tried to access point outside grid: (" + pos_grid.x + ", " + pos_grid.y + ")");
		}
		else
			return mGridData[pos_grid.y * mGridDimensions.x + pos_grid.x];
	}
	
	private boolean withinGrid(Vector query) 
	{
		return query.x >= 0 && query.x < mGridDimensions.x && query.y >= 0 && query.y < mGridDimensions.y;
	}
	
}
