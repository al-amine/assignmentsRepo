package assignment_5;

import java.io.File;

public class Main {
	
	
	
	public static void main(String args[])
    {
		File file = new File("D:/");
		
        String[] list = file.list();
        
        for(String n:list){
        	
            System.out.println(n);
   }

    }
	}
