package com.lms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.lms.dao.BookLoansDao;
import com.lms.dao.BorrowerDao;
import com.lms.dao.CopiesDao;
import com.lms.dao.LibraryBranchDao;
import com.lms.model.Book;
import com.lms.model.Borrower;
import com.lms.model.Branch;
import com.lms.model.Loan;

public class BorrowerService implements Service{
	
	
	public BorrowerService() {
	}
	
	private BorrowerDao borrowerDao;
	private BookLoansDao loanDao;
	private CopiesDao copiesDao;
	private static final Logger LOGGER = Logger.getLogger(LibrarianService.class.getName());

	public BorrowerService(BorrowerDao borrowerDao, BookLoansDao loanDao, CopiesDao copiesDao) {
		this.borrowerDao = borrowerDao;
		this.loanDao = loanDao;
		this.copiesDao = copiesDao;
	}

	
	
	
	public Loan borrowBook(Borrower borrower, Book book, Branch branch, LocalDateTime dateOut, LocalDateTime dueDate){

		Loan newLoan = null;
		try {
			loanDao = new BookLoansDao();
			newLoan = loanDao.create(book, borrower, branch, dateOut, dueDate);
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to create a loan with Borrower CardNo = " + borrower.getCardNo() + " and book Id = " +
						book.getId() + " and branch Id = " + branch.getId());
		}
		System.out.println("Book Borrowed Successfully, Enjoy \n");
		return newLoan;
	}

	
	
	
	public Map<Book, Integer> getAllBranchCopies(Branch branch){
		
		Map<Book, Integer> listAllBranchCopies = new HashMap<>();
		try {
			copiesDao = new CopiesDao();
			listAllBranchCopies = copiesDao.getAllBranchCopies(branch);
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to get all branch copies");
		}
		return listAllBranchCopies;
	}

	
	
	
	public boolean returnBook(Borrower borrower, Book book, Branch branch, LocalDateTime dueDate){
		
		boolean returnedBook = false;
		Loan foundLoan = null;
		try {
			loanDao = new BookLoansDao();
			foundLoan = loanDao.get(book, borrower, branch);
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to get entry in loan with Borrower CardNo = " + borrower.getCardNo() + " and book Id = " +
						book.getId() + " and branch Id = " + branch.getId());
		}

		if(foundLoan != null) {
			if(foundLoan.getDueDate().isAfter(dueDate)) {
				try {
					loanDao.delete(foundLoan);
					returnedBook = true;
				} catch (SQLException e) {
					LOGGER.log(Level.WARNING, "Failed to delete loan entry with Borrower CardNo = " + borrower.getCardNo() + " and book Id = " +
							book.getId() + " and branch Id = " + branch.getId());
				}

			}
			
			else {
				
				try {
					
//					Loan updatedLoan = new Loan(foundLoan.getBook(), foundLoan.getBorrower(), foundLoan.getBranch(),
//					foundLoan.getDateOut(), dueDate);
//					loanDao.update(updatedLoan);
					loanDao.delete(foundLoan);
					returnedBook = true;
					
				} catch (SQLException e) {
					
					LOGGER.log(Level.WARNING, "Failed to update loan entry with Borrower CardNo = " + borrower.getCardNo() + " and book Id = " +
							book.getId() + " and branch Id = " + branch.getId());
				}
				
				
			}
			
		}
		System.out.println("Book Returned Successfully, Thank you \n");
		return returnedBook;
	}

	
	
	
	
	public List<Branch> getAllBranchesWithLoan(Borrower borrower){
		
		List<Branch> listOfAllBranchesWithLoan = new ArrayList<>();
		try {
			loanDao = new BookLoansDao();
			List<Loan> listOfAllLoans = loanDao.getAll();

			List<Loan> listOfAllLoansWithBorrower= listOfAllLoans.parallelStream().filter(l -> l.getBorrower().equals(borrower))
				.collect(Collectors.toList());

			listOfAllBranchesWithLoan = listOfAllLoansWithBorrower.parallelStream().map(l -> l.getBranch())
				.collect(Collectors.toList());
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to get all loans");
		}

		return listOfAllBranchesWithLoan;
	}
	
	
	
	

	public List<Loan> getAllBorrowedBooks(Borrower borrower){
		
		List<Loan> listOfAllLoanFromBorrower = new ArrayList<>();
		try {
			loanDao = new BookLoansDao();
			List<Loan> listOfAllLoans = loanDao.getAll();

			listOfAllLoanFromBorrower = listOfAllLoans.parallelStream()
					.filter(l -> l.getBorrower().equals(borrower))
					.collect(Collectors.toList());
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to get all loans");
		}

		return listOfAllLoanFromBorrower;
	}
	
	
	
	

	public Borrower getBorrower(int cardNo){
		
		
		Borrower borrower = null;
		try {
			
		borrowerDao = new BorrowerDao();	
		 borrower = borrowerDao.get(cardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
			

		return borrower;
	}

	
	public List<Branch> getAllBranches(){
		
		
		List<Branch> listOfBranches = new ArrayList<>();
		
		try {
			LibraryBranchDao branchDao = new LibraryBranchDao();
			listOfBranches = branchDao.getAll();
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to give a list of all branches in the branch table");
		}
		return listOfBranches;
}

	
}

