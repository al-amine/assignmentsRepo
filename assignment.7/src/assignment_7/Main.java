package assignment_7;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main{
	
	
    public static void main(String[] args) {
    	
    	try  {
    		
    	
            Scanner reader = new Scanner(System.in); 
            System.out.println("Enter the path of the file : ");
            
            String path = reader.next(); 
            
            System.out.println("Enter the letter : ");
            
           String c =  reader.next();
            
            reader.close();	
    		
    		
//    	String str = args.toString();
    	char[] letter = c.toCharArray();
    	
    	int count = 0;
        Path file = Paths.get(path);
        
//        "D:/assignment_6.txt"
        

      
        
        	
        	BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(file)));
            String line = null;
            
            while((line = br.readLine()) != null){
            	
                for(int i = 0; i < line.length(); i++){
                	
                    if(line.charAt(i) == letter[0])
                        count++;
                    
                }
            }
           
            System.out.println("the number of time that" +c+ " occured is : "+count);
        } 
        
        catch (IOException x)
        {
            System.err.println(x);
        }

    }
        
        

}
    