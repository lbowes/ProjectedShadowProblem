package internal.algorithms;

import java.util.ArrayList;

import internal.Bar;
import internal.Envelope;
import internal.Vector;

public class DodgyAlgorithm implements IAlgorithm
{
	
	public DodgyAlgorithm()
	{
		
	}
	
	public Envelope barsUnion(Bar bar1, Bar bar2)
	{
		ArrayList<Vector> vertsList = new ArrayList<Vector>();
		
		//Case 1, one envelope is entirely before the other
		if (bar1.getRightBound() < bar2.getLeftBound())
		{
			vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
			vertsList.add(new Vector(bar1.getRightBound(), 0));
			vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
			vertsList.add(new Vector(bar2.getRightBound(), 0));
		}
		else if (bar2.getRightBound() < bar1.getLeftBound())
		{
			vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
			vertsList.add(new Vector(bar2.getRightBound(), 0));
			vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
			vertsList.add(new Vector(bar1.getRightBound(), 0));
		}
		
		//Case 2, one envelope is entirely within the other
		else if (bar1.getLeftBound() <= bar2.getLeftBound() && bar1.getRightBound() >= bar2.getRightBound() && bar1.getHeight() >= bar2.getHeight())
		{
			vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
			vertsList.add(new Vector(bar1.getRightBound(), 0));
		}
		else if (bar2.getLeftBound() <= bar1.getLeftBound() && bar2.getRightBound() >= bar1.getRightBound() && bar2.getHeight() >= bar1.getHeight())
		{
			vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
			vertsList.add(new Vector(bar2.getRightBound(), 0));
		}
		
		//Case 3, the envelopes are adjacent
		else if (bar1.getRightBound() == bar2.getLeftBound())
		{
			if (bar1.getHeight() == bar2.getHeight())
			{
				vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
				vertsList.add(new Vector(bar2.getRightBound(), 0));
			}
			else
			{
				vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
				vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
				vertsList.add(new Vector(bar2.getRightBound(), 0));
			}
		}
		else if (bar2.getRightBound() == bar1.getLeftBound())
		{
			if (bar1.getHeight() == bar2.getHeight())
			{
				vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
				vertsList.add(new Vector(bar1.getRightBound(), 0));
			}
			else
			{
				vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
				vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
				vertsList.add(new Vector(bar1.getRightBound(), 0));
			}
		}
		
		//Case 4, the envelopes intersect
			//Case 4.1, one bounds within the other but the top emerges
			else if (bar1.getLeftBound() < bar2.getLeftBound() && bar1.getRightBound() > bar2.getRightBound())
			{
				vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
				vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
				vertsList.add(new Vector(bar2.getRightBound(), bar1.getHeight()));
				vertsList.add(new Vector(bar1.getRightBound(), 0));
			}
			else if (bar2.getLeftBound() < bar1.getLeftBound() && bar2.getRightBound() > bar1.getRightBound())
			{
				vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
				vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
				vertsList.add(new Vector(bar1.getRightBound(), bar2.getHeight()));
				vertsList.add(new Vector(bar2.getRightBound(), 0));
			}
		
			//Case 4.2, one edge intersects, the other is within the first bar, and emerges
			else if(bar1.getLeftBound() == bar2.getLeftBound())
			{
				if (bar1.getRightBound() > bar2.getRightBound())
				{
					vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
					vertsList.add(new Vector(bar2.getRightBound(), bar1.getHeight()));
					vertsList.add(new Vector(bar1.getRightBound(), 0));
				}
				else
				{
					vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
					vertsList.add(new Vector(bar1.getRightBound(), bar2.getHeight()));
					vertsList.add(new Vector(bar2.getRightBound(), 0));
				}
			}
			else if(bar1.getRightBound() == bar2.getRightBound())
			{
				if (bar1.getLeftBound() < bar2.getLeftBound())
				{
					vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
					vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
					vertsList.add(new Vector(bar2.getLeftBound(), 0));
				}
				else
				{
					vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
					vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
					vertsList.add(new Vector(bar1.getLeftBound(), 0));
				}
			}
			//Case 4.3, one side in the middle, the other outside
			else if (bar1.getLeftBound() < bar2.getLeftBound())
			{
				if (bar1.getHeight() == bar2.getHeight())
				{
					vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
					vertsList.add(new Vector(bar2.getRightBound(), 0));
				}
				else if (bar1.getHeight() > bar2.getHeight())
				{
					vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
					vertsList.add(new Vector(bar1.getRightBound(), bar2.getHeight()));
					vertsList.add(new Vector(bar2.getRightBound(), 0));
				}
				else
				{
					vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
					vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
					vertsList.add(new Vector(bar2.getRightBound(), 0));
				}
			}
			else
			{
				if (bar1.getHeight() == bar2.getHeight())
				{
					vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
					vertsList.add(new Vector(bar1.getRightBound(), 0));
				}
				else if (bar1.getHeight() > bar2.getHeight())
				{
					vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
					vertsList.add(new Vector(bar2.getRightBound(), bar1.getHeight()));
					vertsList.add(new Vector(bar1.getRightBound(), 0));
				}
				else
				{
					vertsList.add(new Vector(bar2.getLeftBound(), bar2.getHeight()));
					vertsList.add(new Vector(bar1.getLeftBound(), bar1.getHeight()));
					vertsList.add(new Vector(bar1.getRightBound(), 0));
				}
			}
		
		return new Envelope(vertsList);
	}
	
	public Envelope barEnvelopeUnion(Bar bar, Envelope envelop)
	{
		return new Envelope(null);
	}

	@Override
	public Envelope solve(ArrayList<Bar> barList) 
	{
		Envelope outputEnvelope;
		
		//Base case
		outputEnvelope = barsUnion(barList.get(0), barList.get(1));
		
		//Recursive case
		for (int i = 2; i < barList.size(); i++)
		{
			outputEnvelope = barEnvelopeUnion(barList.get(i), outputEnvelope);
		}
		
		return outputEnvelope;
	}

	@Override
	public Envelope solve(Envelope envelope, Bar bar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Envelope solve(Envelope envelope, ArrayList<Bar> barList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Envelope solve(Envelope envelopeA, Envelope envelopeB) {
		// TODO Auto-generated method stub
		return null;
	}

}
