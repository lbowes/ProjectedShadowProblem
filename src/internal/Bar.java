package internal;

public class Bar 
{
	private int leftBound;
	private int height;
	private int rightBound;
	
	public Bar(int left, int height, int right)
	{
		if (height < 0)
		{
			throw new IllegalArgumentException("Height must be at least zero");
		}
		if (right < left)
		{
			throw new IllegalArgumentException("Right bound must be greater than left bound");
		}
		
		this.leftBound = left;
		this.height = height;
		this.rightBound = right;
		
	}
	
	public int getLeftBound()
	{
		return this.leftBound;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public int getRightBound()
	{
		return this.rightBound;
	}
	
	public String toString()
	{
		return "Bar (" + this.leftBound + ", " + this.height + ", " + this.rightBound + ")";
	}
}
