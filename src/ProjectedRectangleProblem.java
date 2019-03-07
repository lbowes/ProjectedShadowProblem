import java.util.ArrayList;

import internal.Bar;
import internal.DodgyAlgorithm;
import internal.Envelop;

class ProjectedRectangleProblem {

	ArrayList inputs = new ArrayList();
	inputs.add(new Bar());
	
	static Envelop result;
	DodgyAlgorithm solver = new DodgyAlgorithm();
	
	public static void main(String[] args) {
		System.out.println("Second commit");
		result = DodgyAlgorithm.solve(null);
	}
}
