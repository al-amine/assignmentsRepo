package assignment_2;

import java.util.Scanner;



public class Main {
	

//public static int sum(int args[]) 
//
// { 
//
//	int s = 0;
//	for (int i = 0; i < args.length; i++) {
//		s+=args[i];
//		
//		
//	}
//	 
//return s;
//	
// }



public static void main(String[] args) 
{ 
	int  s = 0;

	 int myar[]=new int[args.length];
	 
	       for(String i : args ){
	 
	              s+=Integer.parseInt(i);
	 
}
	       
	       System.out.println(" the Sum of those numbers is :"+s);
}
//	int n, s = 0;
//    Scanner reader = new Scanner(System.in);
//    System.out.print("Enter number of elements you want in array:");
//    n = reader.nextInt();
//    int numbers[] = new int[n];
//    System.out.println("Enter all the elements:");
//    for(int i = 0; i < n; i++)
//    {
//        numbers[i] = reader.nextInt();
//        s = s + numbers[i];
//    }
//    System.out.println(" the Sum of those numbers is :");
//}
	
	}


