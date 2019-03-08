import java.util.ArrayList;

import internal.Bar;
import internal.DodgyAlgorithm;
import internal.Envelope;

// temp
import graphics.Visualisation;
import internal.Vector;
//

class ProjectedRectangleProblem {
	//ArrayList inputs = new ArrayList();
	//inputs.add(new Bar());
	
	//static Envelope result;
	//DodgyAlgorithm solver = new DodgyAlgorithm();
	
	public static void main(String[] args) {
		// 1. Calculate output
		//result = DodgyAlgorithm.solve(null);




		int[] placeholderData = {};
		Envelope placeholderResult = new Envelope(placeholderData);
				
		// 2. Visualise it
		Visualisation visualisation = new Visualisation(placeholderResult, new Vector(50, 50));
		System.out.println(visualisation);
	}
}
