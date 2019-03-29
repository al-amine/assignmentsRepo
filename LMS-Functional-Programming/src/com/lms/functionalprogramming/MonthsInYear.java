package com.lms.functionalprogramming;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Scanner;
import java.time.DateTimeException;


import java.lang.NumberFormatException;

public class MonthsInYear {
	
    public static void main(String[] args) {
    	
	     Scanner reader1 = new Scanner(System.in);
	     
         System.out.print("Enter a Year : ");
         int year = reader1.nextInt();

   


        System.out.printf("For the year : "+ year+"\n");
        
        for (Month month : Month.values()) {
            YearMonth ym = YearMonth.of(year, month);
            System.out.printf("%s: %d days%n", month, ym.lengthOfMonth());
        }
    }
}