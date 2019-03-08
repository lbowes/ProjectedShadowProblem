package internal;

import internal.algorithms.IAlgorithm;

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
	public Envelope solve(Bar[] barList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Envelope solve(Envelope envelope, Bar bar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Envelope solve(Envelope envelope, Bar[] barList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Envelope solve(Envelope envelopeA, Envelope envelopeB) {
		// TODO Auto-generated method stub
		return null;
	}
}
