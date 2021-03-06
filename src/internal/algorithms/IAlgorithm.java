package internal.algorithms;

import java.util.ArrayList;

import internal.Bar;
import internal.Envelope;

public interface IAlgorithm 
{
	
	//Implements multiple solve method overloads for all specified solutions
	
	//Multiple bars solution
	public Envelope solve(ArrayList<Bar> barList);
	
	//Single envelope single bar union
	public Envelope solve(Envelope envelope, Bar bar);
	
	//Single envelope multiple bar union
	public Envelope solve(Envelope envelope, ArrayList<Bar> barList);
	
	//Two envelope union
	public Envelope solve(Envelope envelopeA, Envelope envelopeB);
}
