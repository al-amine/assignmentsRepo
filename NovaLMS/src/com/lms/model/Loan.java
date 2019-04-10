package com.lms.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Loan {
	
	private Book book;
	private Borrower borrower;
	private Branch branch;
	private LocalDateTime dateOut;
	private LocalDateTime dueDate;


	public Book getBook() {
		return book;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public Branch getBranch() {
		return branch;
	}



	
	
	
	public LocalDateTime getDateOut() {
		return dateOut;
	}

	public void setDateOut(LocalDateTime dateOut) {
		this.dateOut = dateOut;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Loan() {

	}

	

	public Loan(Book book, Borrower borrower, Branch branch, LocalDateTime dateOut, LocalDateTime dueDate) {
		this.book = book;
		this.borrower = borrower;
		this.branch = branch;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
	}
	
	


	@Override
	public int hashCode() {
		return Objects.hash(book, borrower, branch);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Loan) {
			return Objects.equals(book, ((Loan) obj).getBook())
					&& Objects.equals(borrower, ((Loan) obj).getBorrower())
					&& Objects.equals(branch, ((Loan) obj).getBranch())
					&& Objects.equals(dateOut, ((Loan) obj).getDateOut())
					&& Objects.equals(dueDate, ((Loan) obj).getDueDate());
		} else {
			return false;
		}
	}

	
	
	@Override
	public String toString() {
		return "Loan: " + book.getTitle() + " borrowed from " + branch.getName() + " by " +
	Objects.toString(borrower.getName(), Integer.toString(borrower.getCardNo())) + " checkout on " + Objects.toString(dateOut, "No checkout Date") +
	" and due on " + Objects.toString(dueDate, "Never!");
	}
	
	
	
	
}
