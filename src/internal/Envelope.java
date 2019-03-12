package internal;

import java.util.ArrayList;

public class Envelope 
{
	ArrayList<Vector> verticesList;
	
	
	public Envelope(ArrayList<Vector> verts)
	{
		verticesList = verts;
	}
	
	public ArrayList<Vector> getVertices()
	{
		return this.verticesList;
	}
}
