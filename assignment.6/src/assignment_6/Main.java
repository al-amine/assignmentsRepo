package assignment_6;

import java.io.FileWriter;
import java.io.IOException;



public class Main {
   public static void main(String[] args) {
	   
	      String str = "this is the last thing added to the file sir !";
	      
	      try {
	    	  
	         String filename= "D:/assignment_6.txt";
	         FileWriter w = new FileWriter(filename,true);

	         w.write(str);
	         w.close();

	         
	         System.out.println("it's working ");
	         
	      }
	      catch(IOException ioe) {
	         System.out.println(ioe);
	      }
	   }
   
}
   
