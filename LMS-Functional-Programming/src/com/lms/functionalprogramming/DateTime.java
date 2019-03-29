package com.lms.functionalprogramming;


public class DateTime {

	public static void main(String[] args) {
		
		
		
		// question 1
		// the answer is  LocalDateTime class, or  ZonedDateTime class. Both classes track date and time to nanosecond precision and both classes, when used in conjunction with Period, give a result using a combination of human-based units, such as years, months, and days
		
		// question 2
		
		//  You can use the previous method of a TemporalAdjuster:

        //      System.out.printf("The previous Thursday is: %s%n",
        //          date.with(TemporalAdjuster.previous(DayOfWeek.THURSDAY)));
		
		// question 3
		
       // Both ZoneId and ZoneOffset track an offset from Greenwich/UTC time, but the ZoneOffset class tracks only the absolute offset from Greenwich/UTC.
       // The ZoneId class also uses the ZoneRules to determine how an offset varies for a particular time of year and region.
		
		
		// question 4
		
		
//		You can convert an Instant to a ZonedDateTime by using the ZonedDateTime.ofInstant method. You also need to supply a ZoneId:
//
//			ZonedDateTime time = ZonedDateTime.ofInstant(Instant.now(),
//			                                            ZoneId.systemDefault());
//			Alternatively, you could use the Instant.atZone method:
//
//			ZonedDateTime time = Instant.now().atZone(ZoneId.systemDefault());
//			You can use the toInstant method in the ChronoZonedDateTime interface, implemented by the ZonedDateTime class, to convert from a ZonedDateTime to an Instant:
//
//			Instant inst = ZonedDateTime.now().toInstant();
		
		

	}

}
