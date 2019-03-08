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
