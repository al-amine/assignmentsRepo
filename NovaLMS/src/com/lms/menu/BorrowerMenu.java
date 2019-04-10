package com.lms.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;

import com.lms.model.Author;
import com.lms.model.Book;
import com.lms.model.Borrower;
import com.lms.model.Branch;
import com.lms.model.Loan;
import com.lms.service.BorrowerService;

/**
 * The text-based menu UI for borrowers.
 *
 */
public final class BorrowerMenu {
	/**
	 * The service class we use to handle database interfacing for us.
	 */
	private BorrowerService service;
	/**
	 * The helper we use for interacting with the user.
	 */
	private final MenuHelper mh;

	/**
	 * To initialize the menu, the caller must provide the service class and the
	 * menu I/O helper.
	 *
	 * @param service the service class to interact with the database
	 * @param menuHelper the helper to interact with the user
	 */
	public BorrowerMenu(final BorrowerService service, final MenuHelper menuHelper) {
		this.service = service;
		mh = menuHelper;
	}

	/**
	 * Ask the user to choose a branch to check out from and a book to check out,
	 * and create the loan.
	 * @throws IOException 
	 * @throws SQLException 
	 */
	private void checkOutMenu(final Borrower borrower){
		
		service = new BorrowerService();
		
		mh.println();
		final Optional<Optional<Branch>> chosenBranch = mh.chooseFromList(
				service.getAllBranches(), "Choose branch to check out from:",
				"Chosen branch:", "Cancel check-out operation",
				"There are no library branches", "No such branch", Branch::getName);
		final Branch branch;
		if (chosenBranch.isPresent()) {
			if (chosenBranch.get().isPresent()) {
				
				branch = chosenBranch.get().get();
			} else { // "Cancel" chosen
				return;
			}
		} else { // no branches or input out of range
			return;
		}
		mh.println();
		// TODO: Filter out books where all copies are currently out
		final Optional<Optional<Book>> chosenBook = mh.chooseFromList(
				
				new ArrayList<>(service.getAllBranchCopies(branch).keySet()),
				"Choose book to check out:", "Chosen book:", "Cancel check-out operation",
				"No books at that branch", "No such book",
				book -> String.format("%s by %s", book.getTitle(),
						Optional.ofNullable(book.getAuthor()).map(Author::getName)
								.orElse("an unknown author")));
		final Book book;
		if (chosenBook.isPresent()) {
			if (chosenBook.get().isPresent()) {
				book = chosenBook.get().get();
			} else { // "Cancel" chosen
				return;
			}
		} else { // no books at that branch or input out of range
			return;
		}
		service.borrowBook(borrower, book, branch, LocalDateTime.now(),
				LocalDateTime.now().plusWeeks(1));
	}
	/**
	 * Ask the user to choose a book he or she has checked out and return it.
	 * @throws IOException 
	 * @throws SQLException 
	 */
	private void returnBookMenu(final Borrower borrower){
		service = new BorrowerService();
		final Optional<Optional<Loan>> chosenLoan = mh.chooseFromList(
				service.getAllBorrowedBooks(borrower), "Your outstanding loans:",
				"Chosen book:", "Cancel return operation",
				"You have no outstanding loans", "No such book",
				loan -> String.format("%s by %s from %s", loan.getBook().getTitle(),
						Optional.ofNullable(loan.getBook().getAuthor())
								.map(Author::getName).orElse("an unknown author"),
						loan.getBranch().getName()));
		final Loan loan;
		if (chosenLoan.isPresent()) {
			if (chosenLoan.get().isPresent()) {
				loan = chosenLoan.get().get();
			} else { // "Cancel" chosen
				return;
			}
		} else { // no loans or input out of range
			return;
		}
		service.returnBook(borrower, loan.getBook(), loan.getBranch(), LocalDateTime.now());
	}
	/**
	 * Ask the borrower for his or her card number, then present the menu and handle
	 * the user's choices.
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void menu() {
		final OptionalInt cardNo = mh.getNumber("Please enter card number:",
				"Card number must be a number.");
		final Borrower borrower;
		if (cardNo.isPresent()) {
			borrower = service.getBorrower(cardNo.getAsInt());
		} else {
			// already told the user it must be numeric
			return;
		}
		if (borrower == null) {
			mh.println("There is no valid library card with that number");
			return;
		}
		while (true) {
			mh.println();
			mh.println("Would you like to");
			mh.println("0) Quit to previous menu");
			mh.println("1) Check out a book");
			mh.println("2) Return a book");
			switch (mh.getString("Chosen action:")) {
			case "0":
				return;
			case "1":
				checkOutMenu(borrower);
				break;
			case "2":
				returnBookMenu(borrower);
				break;
			default:
				mh.println("Unknown action.");
				break;
			}
		}
	}
}
