package com.lms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lms.dao.AuthorDao;
import com.lms.dao.BookDao;
import com.lms.dao.BookLoansDao;
import com.lms.dao.BorrowerDao;
import com.lms.dao.LibraryBranchDao;
import com.lms.dao.PublisherDao;
import com.lms.model.Author;
import com.lms.model.Book;
import com.lms.model.Borrower;
import com.lms.model.Branch;
import com.lms.model.Loan;
import com.lms.model.Publisher;


public class AdministratorService implements Service{
	
	public AdministratorService() {
	}
	
	private BookDao bookDao;
	private AuthorDao authorDao;
	private PublisherDao publisherDao;
	private LibraryBranchDao branchDao;
	private BorrowerDao borrowerDao;
	private BookLoansDao loanDao;
	
	private static final Logger LOGGER = Logger.getLogger(LibrarianService.class.getName());

	public AdministratorService(BookDao bookDao, AuthorDao authorDao,
			PublisherDao publisherDao, LibraryBranchDao branchDao,
			BorrowerDao borrowerDao, BookLoansDao loanDao) {
		this.bookDao = bookDao;
		this.authorDao = authorDao;
		this.publisherDao = publisherDao;
		this.branchDao = branchDao;
		this.borrowerDao = borrowerDao;
		this.loanDao = loanDao;
	}
	
	
	
	
	
	// the Book Menu	
	
	
	public Book createBook(String title, Author author, Publisher publisher){
		
		Book book = null;
		
		try {
			bookDao = new BookDao();
			authorDao =  new AuthorDao();
			publisherDao = new PublisherDao();
			
			Author auth = authorDao.get(author.getId());
			Publisher pub = publisherDao.get(publisher.getId());
			
			if (auth == null || pub == null) {
				
				if (auth == null) {
					authorDao.create(author.getName());
					
				}
				else if (pub == null) {
					publisherDao.create(publisher.getName(),"update this info", "update this info");
				}
				
				
				book = bookDao.create(title, author, publisher);
			}
			
			else {
				
				book = bookDao.create(title, author, publisher);
				
			} 
			 
			 
			 
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to create new book",e);
		}
		
		System.out.println("Book Created Successfully \n");
		return book;
	}
	
	

	public void updateBook(Book book){
		
		try {
			
			bookDao = new BookDao();
			bookDao.update(book);
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to update Book");
		}
		System.out.println("Book Updated Successfully \n");
	}

	

	public void deleteBook(Book book) {
		
		try {
			bookDao = new BookDao();
			bookDao.delete(book);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to delete Book");
		}
		System.out.println("Book Deleted Successfully \n");
	}

	
	public List<Book> getAllBooks(){
		
		
		List<Book> listOfAllBooks = new ArrayList<>();
		try {
			bookDao = new BookDao();
			listOfAllBooks = bookDao.getAll();
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to get all Book");
		}
		return listOfAllBooks;
	}

	
	
	
// the Author Menu	
	
	
	public Author createAuthor(String name){
		
		
		Author author = null;
		try {
			authorDao =  new AuthorDao();
			author = authorDao.create(name);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to create an Author");
		}
		System.out.println("Author Created Successfully \n");
		return author;
	}

	
	
	public void updateAuthor(Author author){
		
		
		try {
			authorDao =  new AuthorDao();
			authorDao.update(author);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to update an Author");
		}
		System.out.println("Author Updated Successfully \n");
	}
	
	

	public void deleteAuthor(Author author){
		
		
		try {
			authorDao =  new AuthorDao();
			authorDao.delete(author);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to delete an Author");
		}
		System.out.println("Author Deleted Successfully \n");
	}
	

	public List<Author> getAllAuthors(){
		
		
		List<Author> listOfAllAuthors = new ArrayList<>();
		try {
			authorDao =  new AuthorDao();
			listOfAllAuthors = authorDao.getAll();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to get all Authors");
		}
		return listOfAllAuthors;
	}
	
	
	
	
	
	// the Publisher Menu	
	// publisher should not have null columns
	
	
	public Publisher createPublisher(String name) {
		
		
		Publisher publisher = null;
		try {
			publisherDao = new PublisherDao();
			publisher = publisherDao.create(name, "", "");
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to create an Publisher with just name");
		}
		System.out.println("Publisher Created Successfully but will need to Updated Later \n");
		return publisher;
	}

	
	
	
	public Publisher createPublisher(String name, String address, String phone) {
		
		
		Publisher publisher = null;
		try {
			publisherDao = new PublisherDao();
			publisher = publisherDao.create(name, address, phone);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to create an Publisher with all attributes");
		}
		System.out.println("Publisher Created Successfully \n");
		return publisher;
	}
	
	

	public void updatePublisher(Publisher publisher) {
		
		try {
			publisherDao = new PublisherDao();
			publisherDao.update(publisher);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to update Publisher");
		}
		System.out.println("Publisher Updated Successfully \n");
	}

	
	
	public void deletePublisher(Publisher publisher){
		
		try {
			publisherDao = new PublisherDao();
			publisherDao.delete(publisher);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to delete Publisher");
		}
		System.out.println("Publisher Deleted Successfully \n");
	}
	
	
	

	public List<Publisher> getAllPublishers(){
		
		
		List<Publisher> listOfAllPublishers = new ArrayList<>();
		try {
			publisherDao = new PublisherDao();
			listOfAllPublishers = publisherDao.getAll();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to get all Publisher");
		}
		return listOfAllPublishers;
	}
	
	
	
	// the Branch Menu	
	

	public Branch createBranch(String name, String address) {
		
		
		
		Branch branch = null;
		try {
			branchDao = new LibraryBranchDao();
			branch = branchDao.create(name, address);
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to create Branch");
		}
		System.out.println("Branch Created Successfully \n");
		return branch;
	}
	
	

	public void deleteBranch(Branch branch){

		try {
			branchDao = new LibraryBranchDao();
			branchDao.delete(branch);
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to delete Branch");
		}
		System.out.println("Branch Deleted Successfully \n");
	}
	
	

	public void updateBranch(Branch branch) {
		
	
		try {
			branchDao = new LibraryBranchDao();
			branchDao.update(branch);
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to delete Branch");
		}
		System.out.println("Branch Updated Successfully \n");
	}
	
	
	public List<Branch> getAllBranches() {
		
		List<Branch> listOfAllBranches = new ArrayList<>();
		try {
			branchDao = new LibraryBranchDao();
			listOfAllBranches = branchDao.getAll();
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to get all Branches");
		}
		return listOfAllBranches;
	}
	
	
	
	
	// the Borrower Menu	
	

	public Borrower createBorrower(String name, String address, String phone){
	
		Borrower borrower = null;
		try {
			borrowerDao = new BorrowerDao();
			borrower = borrowerDao.create(name, address, phone);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to create a Borrower");
		}
		System.out.println("Borrower Created Successfully \n");
		return borrower;
	}
	
	

	public void updateBorrower(Borrower borrower) {
		
		try {
			borrowerDao = new BorrowerDao();
			borrowerDao.update(borrower);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to update a Borrower");
		}
		System.out.println("Borrower Updated Successfully \n");
	}
	
	

	public void deleteBorrower(Borrower borrower){
		
		try {
			borrowerDao = new BorrowerDao();
			borrowerDao.delete(borrower);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to delete a Borrower");
		}
		System.out.println("Borrower Deleted Successfully \n");
	}

	
	public List<Borrower> getAllBorrowers(){
	
		List<Borrower> listOfAllBorrowers = new ArrayList<>();
		try {
			borrowerDao = new BorrowerDao();
			listOfAllBorrowers = borrowerDao.getAll();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Failed to get all Borrower");
		}
		return listOfAllBorrowers;
	}
	
	
	// the Loan Menu	
	
	

	public List<Loan> getAllLoans(){
		
		List<Loan> listOfAllLoans = new ArrayList<>();
		try {
			loanDao = new BookLoansDao();
			listOfAllLoans = loanDao.getAll();
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.WARNING, "Failed to get all Book Loans");
		}
		
		return listOfAllLoans;
	}
	

	public boolean overrideDueDateForLoan(Book book, Borrower borrower, Branch branch, LocalDateTime dueDate){
		
		boolean success = false;
		Loan foundLoan = null;
		

		
		try {
			
			loanDao = new BookLoansDao();
			
			foundLoan = loanDao.get(book, borrower, branch);
		} catch (SQLException | IOException e1) {
			LOGGER.log(Level.WARNING, "Failed to get the Book Loans");
		}

		if(foundLoan != null) {
			foundLoan.setDueDate(dueDate);
			try {
				loanDao.update(foundLoan);
				success = true;
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Failed to update due date on a loan");
			}
		}
		System.out.println("Due Date Overridden Successfully \n");
		return success;
	}
	
	
	
	}