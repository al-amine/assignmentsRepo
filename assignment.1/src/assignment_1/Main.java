package assignment_1;
import java.util.Scanner;

/**
 * @author Al-amine AHMED MOUSSA
 *
 */
public class Main {

    public static void draw1(int n) 
    { 
        int i, j; 
        
        System.out.println("patern 1 :");
        if (n<=0) {
			System.out.println("this n can't be processed ");
		 }
        
        else {
        	
        	for(i=0; i<n; i++) 
            { 
      
                for(j=0; j<=i; j++) 
                { 
                    System.out.print("*"); 
                } 
      
                System.out.println(); 
            } 
		}
 
        
   } 
    
    
    
    
   public static void draw2(int n) 
   { 
        int i, j; 
        
        System.out.println("patern 2 :");
        
        if (n<=0) {
			System.out.println("this n can't be processed ");
		 }
     
        else {
        	for(i=n; i>=0; i--) 
            { 
                for(j=0; j<=i; j++) 
                { 
                    System.out.print("*"); 
                } 
      
                System.out.println(); 
            } 
		}
     
   } 
    
    

    


    
    
	public static void draw3(int n)  {
       int m = 0;
       System.out.println("patern 3 :");
       
       if (n<=0) {
			System.out.println("this n can't be processed ");
		 }

       else {
    	   
    	   for(int i = 1; i <= n; i++, m = 0) {
               for(int j = 1; j <= n - i; j++) {
                   System.out.print("  ");
               }

               while(m != 2 * i - 1) {
                   System.out.print("* ");
                   m++;
               }

               System.out.println();
           }
	}
        
    }
    
    
    
    
    
	public static void draw4(int n)  {
	     int m = 0;
         System.out.println("patern 4 :");
         
         if (n<=0) {
			System.out.println("this n can't be processed ");
		 }
         else {
        	 
 	        for(int i = n; i >= 1; i--, m = 0) {
	            for(int j = 1; j <= n - i; j++) {
	                System.out.print("  ");
	            }

	            while(m != 2 * i - 1) {
	                System.out.print("* ");
	                m++;
	            }

	            System.out.println();
	        } 
         }

	    }
    
    
    
	public static void patern(int n,int methode)  {
		
		
		switch (methode) {
		case 1 :
			draw1(n);
			break;
		case 2 :
			draw2(n);
			break;
		case 3 :
			draw3(n);
			break;
		case 4:
			draw4(n);
			break;	
			
			
		default:
			System.out.println("choose a number ");
			break;
		}
	}
	
	
    
    
  
    
    public static void main(String args[]) 
    { 
//      int n = 5; 
//      draw1(n);
//      draw2(n);
//      draw3(n);
//      draw4(n);
    	
        Scanner reader = new Scanner(System.in); 
        System.out.println("Enter the number of lines n : ");
        
        int n = reader.nextInt(); 
        System.out.println("Enter the number of the patenr p (1,4): ");
        
        int p = reader.nextInt();
        
        reader.close();
        
        patern(n,p);
    } 
    
    
}







