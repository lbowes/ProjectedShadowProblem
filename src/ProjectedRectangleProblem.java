import java.util.ArrayList;

import internal.Bar;
import internal.Envelope;

// temp
import graphics.Visualisation;
import internal.Vector;
import internal.algorithms.DodgyAlgorithm;
import internal.algorithms.IAlgorithm;
//

class ProjectedRectangleProblem {
	
	static IAlgorithm solver = new DodgyAlgorithm();
	
	public static void main(String[] args) {
		//Adds first example from table 1 to testInput
		ArrayList<Bar> testInput = new ArrayList<Bar>();
		testInput.add(new Bar(1, 3, 4));
		testInput.add(new Bar(2, 4, 5));
		testInput.add(new Bar(6, 1, 7));
		testInput.add(new Bar(8, 5, 11));
		testInput.add(new Bar(8, 4, 15));
		testInput.add(new Bar(9, 3, 12));
		testInput.add(new Bar(13, 5, 16));
		
		Envelope result = solver.solve(testInput);
				
		// Present results
		Visualisation visualisation = new Visualisation(result);
		System.out.println(visualisation);
	}
}
