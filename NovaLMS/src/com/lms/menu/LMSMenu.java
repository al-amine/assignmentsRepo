package com.lms.menu;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lms.service.AdministratorService;
import com.lms.service.BorrowerService;
import com.lms.service.LibrarianService;

/**
 * A text-based main-menu UI for the library management system.
 *
 */
public final class LMSMenu {
	/**
	 * The helper for interacting with the user.
	 */
	private final MenuHelper mh;
	/**
	 * The means of getting input from the user.
	 */
	private final Scanner stdin;
	/**
	 * The means of sending output to the user.
	 */
	private final PrintWriter stdout;
	/**
	 * Our connection to the database.
	 */

	/**
	 * To initialize the menu, the caller must provide I/O streams and the database
	 * connection. Note that the caller is responsible for ensuring that all of
	 * these resources are properly closed after the menu goes out of scope.
	 *
	 * @param inStream     how to get input from the user
	 * @param outStream    how to send output to the user
	 * @param dbConnection how to connect to the database
	 */
	public LMSMenu(final Reader inStream, final  PrintStream  outStream) {
		
		stdin = new Scanner(inStream);
		stdout = new PrintWriter(outStream);
		 mh = new MenuHelper(stdin, stdout);

	}
	
	private static final Logger LOGGER = Logger.getLogger(LMSMenu.class.getName());
	
	/**
	 * Show the topmost-level menu, solicit user input, and call the appropriate
	 * sub-menu.
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void chooseRole(){
		try {
			mh.println("Welcome to the GCIT Library Management System!");
			mh.println();
			while (true) {
				mh.println("Please choose your role:");
				mh.println("1) Borrower");
				mh.println("2) Librarian");
				mh.println("3) Administrator");
				switch (mh.getString("Role:")) {
				case "0":
	                System.out.println("Thank you, see you later.");
					return;
				case "1":
					new BorrowerMenu(new BorrowerService(), mh).menu();
					continue;
				case "2":
					new LibrarianMenu(new LibrarianService(), mh).menu();
					continue;
				case "3":
					new AdministratorMenu(new AdministratorService(), mh)
							.menu();
					continue;
				default:
					((PrintWriter) stdout).println("Please select role, or type 0 to quit");
					break;
				}
			}
		} catch (final NumberFormatException | IOException | SQLException except) {
			((PrintWriter) stdout).println("Input must be a number");
		}
	}
	
	
	public static void main(String[] args) throws IOException, SQLException {
		

		
	try(InputStreamReader in = new InputStreamReader(System.in);) {
		
		LMSMenu menu = new LMSMenu(in, System.out);
		menu.chooseRole();
		
	} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "I/O error dealing with System input", e);
	}

		


	}



}
