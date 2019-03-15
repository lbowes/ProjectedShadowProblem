package internal.algorithms;

import java.util.ArrayList;

import internal.Bar;
import internal.Envelope;
import internal.Vector;

public class DodgyAlgorithm implements IAlgorithm
{
	
	public DodgyAlgorithm() {}
	
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
		//Debug
		for (int i = 0; i < vertsList.size(); i++)
		{
			System.out.println(vertsList.get(i));
		}
		return new Envelope(vertsList);
	}
	
	public Envelope barEnvelopeUnion(Bar bar, Envelope inputEnvelope)
	{
		Envelope outputEnvelope;
		ArrayList<Vector> outputVertsList = new ArrayList<Vector>();
		int runningHeight = 0;
		//boolean addedLeft = false;
		//boolean addedRight = false;
		
		//Case 1, bar is before all envelope nodes
		if (bar.getLeftBound() < inputEnvelope.getVertices().get(0).x)
		{
			outputVertsList.add(new Vector(bar.getLeftBound(),bar.getHeight()));
			//addedLeft = true;
			runningHeight = bar.getHeight();
			if (bar.getRightBound() < inputEnvelope.getVertices().get(0).x)
			{
				outputVertsList.add(new Vector(bar.getRightBound(),0));
				//addedRight = true;
				runningHeight = 0;
			}
		}
		
		//Case 2, bar is within envelope nodes
		for (int i = 0; i < inputEnvelope.getVertices().size() - 1; i++)
		{
			System.out.println("Comparing " + inputEnvelope.getVertices().get(i) + " and " + bar);
			//Case 2.1, left and right bounds are within
			if (((bar.getLeftBound() >= inputEnvelope.getVertices().get(i).x) && (bar.getLeftBound() < inputEnvelope.getVertices().get(i+1).x)) && (((bar.getRightBound() >= inputEnvelope.getVertices().get(i).x) && (bar.getRightBound() < inputEnvelope.getVertices().get(i+1).x))))
			{
				if (bar.getLeftBound() == inputEnvelope.getVertices().get(i).x)
				{
					if (bar.getHeight() > inputEnvelope.getVertices().get(i).y)
					{
						outputVertsList.add(new Vector(bar.getLeftBound(), bar.getHeight()));
						outputVertsList.add(new Vector(bar.getRightBound(), inputEnvelope.getVertices().get(i).y));
					}
					else
					{
						outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, inputEnvelope.getVertices().get(i).y));
					}
				}
				else
				{
					if (bar.getHeight() > inputEnvelope.getVertices().get(i).y)
					{
						outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, inputEnvelope.getVertices().get(i).y));
						outputVertsList.add(new Vector(bar.getLeftBound(), bar.getHeight()));
						outputVertsList.add(new Vector(bar.getRightBound(), inputEnvelope.getVertices().get(i).y));
					}
					else
					{
						outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, inputEnvelope.getVertices().get(i).y));
					}
				}
			}
			//Case 2.2, left bound is within
			else if (((bar.getLeftBound() >= inputEnvelope.getVertices().get(i).x) && (bar.getLeftBound() < inputEnvelope.getVertices().get(i+1).x)))
			{
				if (bar.getLeftBound() == inputEnvelope.getVertices().get(i).x)
				{
					if (bar.getHeight() > inputEnvelope.getVertices().get(i).y)
					{
						outputVertsList.add(new Vector(bar.getLeftBound(), bar.getHeight()));
						runningHeight = bar.getHeight();
					}
					else
					{
						outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, inputEnvelope.getVertices().get(i).y));
						runningHeight = bar.getHeight();
					}
				}
				else
				{
					if (bar.getHeight() > inputEnvelope.getVertices().get(i).y)
					{
						outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, inputEnvelope.getVertices().get(i).y));
						outputVertsList.add(new Vector(bar.getLeftBound(), bar.getHeight()));
						runningHeight = bar.getHeight();
					}
					else
					{
						outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, inputEnvelope.getVertices().get(i).y));
						runningHeight = bar.getHeight();
					}
				}
			}
			//Case 2.3, right bound is within
			else if (((bar.getRightBound() >= inputEnvelope.getVertices().get(i).x) && (bar.getRightBound() < inputEnvelope.getVertices().get(i+1).x)))
			{
				if (bar.getRightBound() == inputEnvelope.getVertices().get(i).x)
				{
					if (inputEnvelope.getVertices().get(i).y > runningHeight)
					{
						outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, inputEnvelope.getVertices().get(i).y));
						runningHeight = 0;
					}
					else
					{
						outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, inputEnvelope.getVertices().get(i).y));
						runningHeight = 0;								//This bit of the loop is actually superfluous, but it is also symmetrical
					}
				}
				else
				{
					if (inputEnvelope.getVertices().get(i).y > runningHeight)
					{
						outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, inputEnvelope.getVertices().get(i).y));
						runningHeight = 0;
					}
					else
					{
						outputVertsList.add(new Vector(bar.getRightBound(), inputEnvelope.getVertices().get(i).y));
						runningHeight = 0;
					}
				}
			}
			//Case 2.4, no bounds are within
			else
			{
				if (inputEnvelope.getVertices().get(i).y > runningHeight)
				{
					outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, inputEnvelope.getVertices().get(i).y));
				}
				else
				{
					outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, runningHeight));
				}
			}
		}
		
		int i = inputEnvelope.getVertices().size()-1;
		
		//Case 3, bar is after all envelope nodes
		if (inputEnvelope.getVertices().get(inputEnvelope.getVertices().size() - 1).x < bar.getLeftBound())
		{
			outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, 0));
			outputVertsList.add(new Vector(bar.getLeftBound(), bar.getHeight()));
			outputVertsList.add(new Vector(bar.getRightBound(), 0));
		}
		else if (inputEnvelope.getVertices().get(inputEnvelope.getVertices().size() - 1).x == bar.getLeftBound())
		{
			if (bar.getHeight() == inputEnvelope.getVertices().get(inputEnvelope.getVertices().size() - 1).y)
			{
				outputVertsList.add(new Vector(bar.getRightBound(), 0));
			}
			else
			{
				outputVertsList.add(new Vector(bar.getLeftBound(), bar.getHeight()));
				outputVertsList.add(new Vector(bar.getRightBound(), 0));
			}
		}
		else if (inputEnvelope.getVertices().get(inputEnvelope.getVertices().size() - 1).x == bar.getRightBound())
		{
			if (bar.getHeight() <= inputEnvelope.getVertices().get(inputEnvelope.getVertices().size() - 1).y)
			{
				outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, 0));
			}
			else
			{
				outputVertsList.add(new Vector(bar.getLeftBound(), bar.getHeight()));
				outputVertsList.add(new Vector(bar.getRightBound(), 0));
			}
		}
		else
		{
			if (bar.getHeight() == inputEnvelope.getVertices().get(inputEnvelope.getVertices().size() - 2).y)
			{
				outputVertsList.add(new Vector(bar.getRightBound(), 0));
			}
			else if (bar.getHeight() < inputEnvelope.getVertices().get(inputEnvelope.getVertices().size() - 2).y)
			{
				outputVertsList.add(new Vector(inputEnvelope.getVertices().get(i).x, bar.getHeight()));
				outputVertsList.add(new Vector(bar.getRightBound(), 0));
			}
			else
			{
				outputVertsList.add(new Vector(bar.getLeftBound(), bar.getHeight()));
				outputVertsList.add(new Vector(bar.getRightBound(), 0));
			}
		}
		
		return new Envelope(outputVertsList);
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
