import java.util.ArrayList;

import internal.Bar;
import internal.DodgyAlgorithm;
import internal.Envelop;

// temp
import graphics.ConsoleRasteriser;
import internal.Vector;
//

class ProjectedRectangleProblem {

	//ArrayList inputs = new ArrayList();
	//inputs.add(new Bar());
	
	//static Envelop result;
	//DodgyAlgorithm solver = new DodgyAlgorithm();
	
	public static void main(String[] args) {
		//System.out.println("Second commit");
		//result = DodgyAlgorithm.solve(null);
		
		
		// testing console drawing system
		final Vector canvasSize = new Vector(50, 25);
		ConsoleRasteriser c = new ConsoleRasteriser(canvasSize);
		
		c.drawHorizontalLine(new Vector(0, 0), 10, '#');
		c.drawHorizontalLine(new Vector(10, 9), 10, '%');
		c.drawHorizontalLine(new Vector(15, 15), 10, '$');
		
		c.drawVerticalLine(new Vector(0, 0), 3, 'v');		
		c.drawVerticalLine(new Vector(13, 0), 90, 'V');
		
		c.print();
		//
	}
}
