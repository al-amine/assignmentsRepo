package com.lms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lms.dao.BookDao;
import com.lms.dao.CopiesDao;
import com.lms.dao.LibraryBranchDao;
import com.lms.model.Book;
import com.lms.model.Branch;

public class LibrarianService implements Service{
	
	
	public LibrarianService() {
	}
	
	private LibraryBranchDao branchDao;
	private BookDao bookDao;
	private CopiesDao copiesDao;
	private static final Logger LOGGER = Logger.getLogger(LibrarianService.class.getName());

	public LibrarianService(LibraryBranchDao branchDao, BookDao bookDao, CopiesDao copiesDao) {
		this.branchDao = branchDao;
		this.bookDao = bookDao;
		this.copiesDao = copiesDao;
	}

	public void updateBranch(Branch branch){

		try {
			branchDao = new LibraryBranchDao();
			branchDao.update(branch);
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to update a particular branch");
		}
		System.out.println("Branch Updated Successfully \n");
	}
	


	public void setBranchCopies(Branch branch, Book book, int noOfCopies){
		try {
			copiesDao = new CopiesDao();
			copiesDao.setCopies(branch, book, noOfCopies);
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to set copies for book_copies with BranchId = " + branch.getId() + " and BookId = " + book.getId());
		}
		System.out.println("Number of Copies is Updated Successfully \n");
	}

	public List<Book> getAllBooks(){
		
		List<Book> listOfBook = new ArrayList<>();
		try {
			bookDao = new BookDao();
			listOfBook = bookDao.getAll();
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to give a list of all books in the book table");
		}
		return listOfBook;
	}


	public Map<Branch, Map<Book, Integer>> getAllCopies(){
	
		Map<Branch, Map<Book, Integer>> listOfAllCopies = new HashMap<>();
		try {
			copiesDao = new CopiesDao();
			listOfAllCopies = copiesDao.getAllCopies();
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to get a list of all copies in the book_copies table");
		}
		return listOfAllCopies;
	}

	public List<Branch> getAllBranches()  {
		
		List<Branch> listOfBranches = new ArrayList<>();
		try {
			branchDao = new LibraryBranchDao();
			listOfBranches = branchDao.getAll();
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to give a list of all branches in the branch table");
		}
		return listOfBranches;
}
	
}
