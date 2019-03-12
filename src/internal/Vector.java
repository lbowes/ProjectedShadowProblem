package internal;

public class Vector {
	
	public int x;
	public int y;
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector(int a) 
	{
		this.x = a;
		this.y = a;	
	} 

	public Vector add(Vector other)
	{
		return new Vector(this.x + other.x, this.y + other.y);
	}
	
	public Vector sub(Vector other)
	{
		return new Vector(this.x - other.x, this.y - other.y);
	}

	public Vector mult(int scalar)
	{
		return new Vector(this.x * scalar, this.y * scalar);
	}
	
	public String toString()
	{
		return "(" + this.x + ", " + this.y + ")";
	}
	
}
