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
		final Vector canvasSize = new Vector(40, 40);
		ConsoleRasteriser c = new ConsoleRasteriser(canvasSize);
		
		// horizontal lines
		c.drawHorizontalLine(new Vector(0, 0), 100, '%');
		c.drawHorizontalLine(new Vector(2, 2), 100, '$');
		
		// vertical lines
		c.drawVerticalLine(new Vector(0, 0), 3, 'v');		
		c.drawVerticalLine(new Vector(1, 0), 5, 'V');
		
		// points
		for(int i = 0; i < 8; i++)
			c.drawPoint(new Vector(i, i), 'X');
		
		// text
		c.addText(new Vector(10, 10), "hellooo, this is a test to see what text input is like, seems to be alright, clipping works");		
		
		System.out.println(c);
		//
	}
}
