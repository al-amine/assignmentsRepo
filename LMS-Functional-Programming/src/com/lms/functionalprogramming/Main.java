package com.lms.functionalprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;




public class Main {



	public static void main(String[] args) {

		
//		Sort an array of Strings
		
		String[] names = {"Al","AhmedMoussa","Noureddine","Siham","Ziryab","Youssef","Abderrahim","Malouk","Suzan","Jeramy","Tamara"};

	    System.out.println(Arrays.toString(names)); //printing before

        System.out.println("Sorted\n");

	    Arrays.sort(names, (a, b) -> a.length() - b.length());

	    System.out.println(Arrays.toString(names)); //printing after
	    
//		Sort an array of Strings reverse
	    
	    
        System.out.println("Sorted reversly\n");

	    Arrays.sort(names, (a, b) -> b.length() - a.length());

	    System.out.println(Arrays.toString(names)); //printing after
	    
	    
//		Sort an array of Strings alphabetically
	    
	    System.out.println("Sorted alphabetically\n");
	    
	    Arrays.sort(names,(a, b ) ->(a.charAt(0) - b.charAt(0)));
	    
	    System.out.println(Arrays.toString(names)); //printing after

	    
	    
//     assignment 2 
	    
	   System.out.println("separated with a ',' \n");

	    
	    int[] ints = {1,47,89,52,6,4,78,41,10,2,3,65,98,74,14};
	    List<Integer> intList = new ArrayList<Integer>();
	    for (int i : ints)
	    {
	        intList.add(i);
	    }
	    
	    
	    String result = intList.stream().map(i -> i % 2 == 0 ? "e" + i : "o" + i).collect(Collectors.joining(","));	    
	    
        System.out.println(result);
        
        
        
        
        
    //  assignment 3 
        
        System.out.println("strings that start with the letter 'a' \n");
        
	    String[] strs = {"ghty","agde","ajhy","bnk","nbg","acd","gty","abc","aqm","aaa","yta","abf","abs","mlkj","njht"};
	    List<String> strList = new ArrayList<String>();
	    for (String i : strs)
	    {
	    	strList.add(i);
	    }
        
       
	    List<String> result1 = strList.stream().filter(s -> s.startsWith("a")).filter(s -> s.length() == 3).collect(Collectors.toList());
	    
	    System.out.println(result1.toString());

	}

	
	

	
	
	
	

	
	
	
}

