package assignment_3;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	
	

	   public static int getMaxValue(int[][] numbers) {
	        int maxValue = numbers[0][0];
	        int k=0,m=0;
	        for (int j = 0; j < numbers.length; j++) {
	            for (int i = 0; i < numbers[j].length; i++) {
	                if (numbers[j][i] > maxValue) {
	                    maxValue = numbers[j][i];
	                    k=j;
	                    m=i;
	                }
	            }
	        }
	        
	        System.out.println( " the position of the max is :["+k+"]["+m+"]" );
	        return maxValue;
	    }
	   
	   
	   
	   
	    public static void main(String args[]) 
	    { 
	    	
	    	
	        Scanner reader = new Scanner(System.in);
	        System.out.print("Enter lenght of the array:");
	        int l = reader.nextInt();
	        System.out.print("Enter width of the array:");
	        int w =reader.nextInt();
	        int matrix[][]= new int[l][w];
	        
	        System.out.print("Enter the elements of the array :");
	        
	        for(int i = 0; i < l; i++)
	        {
	        	for (int j = 0; j < w; j++) {
	        		
		            matrix[i][j] = reader.nextInt();

				}
	     
	        }

	        
	        
	      
	       int max = getMaxValue(matrix);
	       
	       System.out.print(" and the max is  :"+ max);
	        
	    	
	    }

}
