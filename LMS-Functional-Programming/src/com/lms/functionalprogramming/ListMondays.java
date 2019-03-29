package com.lms.functionalprogramming;
import java.time.Month;
import java.time.Year;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.DateTimeException;

import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;
import java.io.PrintStream;
import java.lang.NumberFormatException;

public class ListMondays {
    public static void main(String[] args) {
    	
    	     Scanner reader1 = new Scanner(System.in);
             System.out.print("Enter a Month : ");
             String strMonth = reader1.nextLine();
    	
             Month month = Month.valueOf(strMonth.toUpperCase());

             System.out.printf("For the month of : " +month+" \n");
        
        LocalDate date = Year.now().atMonth(month).atDay(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        Month mon = date.getMonth();
        
        while (mon == month) {
            System.out.printf(" Monday : %s%n", date);
            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            mon = date.getMonth();
        }
    }
}
