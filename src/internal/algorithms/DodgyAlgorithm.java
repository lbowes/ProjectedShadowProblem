package internal.algorithms;

import java.util.ArrayList;

import internal.Bar;
import internal.Envelope;

public class DodgyAlgorithm implements IAlgorithm
{
	public DodgyAlgorithm()
	{
		
	}
	
	public Envelope barsUnion(Bar bar1, Bar bar2)
	{
		return new Envelope(null);
	}
	
	public Envelope barEnvelopUnion(Bar bar, Envelope envelop)
	{
		return new Envelope(null);
	}

	@Override
	public Envelope solve(ArrayList<Bar> barList) 
	{
		for (int i  = 0; i < barList.size(); i++)
		{
			System.out.println(barList.get(i));
		}
		return null;
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
