package com.lms.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;


import com.lms.model.Author;
import com.lms.model.Loan;
import com.lms.service.AdministratorService;


/**
 * The text-based menu UI for administrators (conflating cataloging and
 * executive responsibilities).
 */
public final class AdministratorMenu {
	/**
	 * The service class we use to handle database interfacing for us.
	 */
	private final AdministratorService service;
	/**
	 * The helper we use for interacting with the user.
	 */
	private final MenuHelper mh;

	/**
	 * To initialize the menu, the caller must provide the service instance and the
	 * menu I/O helper.
	 *
	 * @param service    the service instance to interact with the database
	 * @param menuHelper the helper to interact with the user
	 */
	public AdministratorMenu(final AdministratorService service,
			final MenuHelper menuHelper) {
		this.service = service;
		mh = menuHelper;
	}

	/**
	 * Allow the user to change the due date on a book loan.
	 * @throws IOException 
	 * @throws SQLException 
	 */
	private void changeDueDate(){
		final Optional<Optional<Loan>> chosenLoan = mh.chooseFromList(
				service.getAllLoans(), "Choose loan to change due date for:",
				"Chosen loan:", "Quit to previous menu",
				"No books are checked out in any branch", "No such loan",
				loan -> String.format("%s by %s borrowed from %s by %s",
						loan.getBook().getTitle(),
						Optional.ofNullable(loan.getBook().getAuthor())
								.map(Author::getName).orElse("an unknown author"),
						loan.getBranch().getName(), loan.getBorrower().getName()));
		Loan loan;
		if (chosenLoan.isPresent()) {
			if (chosenLoan.get().isPresent()) {
				loan = chosenLoan.get().get();
			} else { // "Quit" chosen
				return;
			}
		} else { // list empty or input out of range
			return;
		}
		mh.printf("%s was checked out by %s on %s, and due on %s.%n",
				loan.getBook().getTitle(), loan.getBorrower().getName(),
				loan.getDateOut().toLocalDate().toString(), loan.getDueDate().toString());
		try {
			
//			loan.setDueDate(LocalDateTime.parse(mh.getString("New due date (YYYY-MM-DD):")));
//			service.updateLoan(loan); LocalDateTime.parse
//			mh.getString("New due date and Time (dd-MM-yyyy HH:mm):");
//			LocalDateTime.parse(mh.getString("New due date and Time (dd-MM-yyyy HH:mm):"),DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
			
			Loan updatedLoan = new Loan(loan.getBook(), loan.getBorrower(), loan.getBranch(),
					loan.getDateOut(),LocalDateTime.parse(mh.getString("New due date and Time (dd-MM-yyyy HH:mm):"),
							DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
			
			service.overrideDueDateForLoan(updatedLoan.getBook(), updatedLoan.getBorrower(),
					updatedLoan.getBranch(), updatedLoan.getDueDate());
		} 
		catch (final DateTimeParseException except) {
			mh.println("I can't parse that date.");
		}
	}

	/**
	 * Present a list of options to the user and dispatch handling the user's choice
	 * to the appropriate method until the user selects "quit".
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void menu() throws IOException, SQLException {
		while (true) {
			mh.println("Would you like to:");
			mh.println("0) Quit to the previous menu");
			mh.println("1) Add, edit, or remove cataloging data");
			mh.println("2) Manage branch or borrower accounts");
			mh.println("3) Override the due date for a checked-out book");
			switch (mh.getString("Chosen action:")) {
			case "0":
				return;
			case "1":
				new CatalogerMenu(service, mh).menu();
				break;
			case "2":
				new ExecutiveMenu(service, mh).menu();
				break;
			case "3":
				changeDueDate();
				break;
			default:
				mh.println("Unknown action.");
				break;
			}
		}
	}
}
