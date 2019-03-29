package com.lms.functionalprogramming;
import java.time.Month;
import java.time.Year;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.DateTimeException;

import java.time.temporal.TemporalQuery;
import java.util.Scanner;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

import java.io.PrintStream;

public class Friday{
    
    public static void main(String[] args) {
  
        
         
	     Scanner reader1 = new Scanner(System.in);
	     
         System.out.print("Enter a Month : ");
         String  strmonth = reader1.nextLine();
         
         System.out.print("Enter a Month : ");
         int  day = reader1.nextInt();

         Month month = Month.valueOf(strmonth.toUpperCase());
         LocalDate date = null;
         
            date = Year.now().atMonth(month).atDay(day);

         
        if ((date.get(ChronoField.DAY_OF_MONTH) == 13) && (date.get(ChronoField.DAY_OF_WEEK) == 5)) {
			
            System.out.println("the date you gave happen to be on a Friday");

		}
        else {
			
        	System.out.println("the date you gave is not on a  Friday");
		}

        
    } 
    
   
}