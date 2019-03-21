package assignment_4;
import java.util.Scanner;

import assignment_4.Circle;
import assignment_4.Rectangle;
import assignment_4.Triangle;

public class Main {
	
	
	
	
	
	
	 public static void main(String args[]) 
	    { 

		 Circle c = new Circle(3, 6);
		 Rectangle r = new Rectangle(4, 5);
		 Triangle t = new Triangle(4, 3, 5, 3);
		 
		 double cArea = c.CalculateArea(c.getRadius());
		 System.out.println("the area is "+cArea);
		 c.display();
		 double tArea = t.CalculateArea(t.getHeight(),t.getBase());
		 System.out.println("the area is "+tArea);
		 t.display();
		 int rArea = r.CalculateArea(r.getHeight(), r.getWidth());
		 System.out.println("the area is "+rArea);

		 r.display();
		 
		 
		 
				 
	    } 
	 
	 

}
