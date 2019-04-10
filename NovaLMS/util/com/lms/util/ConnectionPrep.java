package com.lms.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPrep {
	
	
	private static Connection conn;
	private static BufferedReader br;
	
	
	public static Connection getConnection() {

		
		try {
			
	   		br = new BufferedReader(new FileReader("resources/.config.txt"));
		    List<String> param = new ArrayList<>();
		    String nextLine = "";
		    while((nextLine = br.readLine()) != null) {
		        param.add(nextLine);
		    }

			conn  =  DriverManager.getConnection("jdbc:mysql://localhost:3306/library1",  param.get(0),  param.get(1));
			
			conn.setAutoCommit(false);
			
		} catch (SQLException | IOException e ) {
			e.printStackTrace();
		}


	    	      

	       return conn;
	   }

}
