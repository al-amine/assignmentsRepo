package com.lms.service.test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lms.dao.BookDao;
import com.lms.dao.BookLoansDao;
import com.lms.dao.BorrowerDao;
import com.lms.dao.LibraryBranchDao;
import com.lms.model.Book;
import com.lms.model.Borrower;
import com.lms.model.Branch;
import com.lms.model.Loan;
import com.lms.service.BorrowerService;

public class BorrowerServiceTest {

	private BorrowerService borrowerService;
	
    public BorrowerServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
	
	
    @Test
	public void borrowBookTest() throws FileNotFoundException, IOException, SQLException {
    	
		BookDao bookDao = new BookDao();
		BorrowerDao borrowerDao = new BorrowerDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
	
		borrowerService = new BorrowerService();
    	
    	System.out.println("Create a Loan");
    	
    

        Book b = bookDao.get(2);
        Borrower br = borrowerDao.get(5);
        Branch ba = libraryBranchDao.get(1);
        LocalDateTime dout = LocalDateTime.parse("28-01-2019 12:00",DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        LocalDateTime dued = LocalDateTime.parse("10-02-2019 12:00",DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

        
		Loan loan = borrowerService.borrowBook(br, b, ba, dout, dued);
		
		assertTrue(loan.getDateOut().equals(dout) && loan.getDueDate().equals(dued) );

	}
    
    
    
	@Test
	public void AllBranchCopiesTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all copies for a branch \n");
		
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();

		borrowerService = new BorrowerService();
		
		Branch ba = libraryBranchDao.get(3);

		
		Map<Book, Integer> la = borrowerService.getAllBranchCopies(ba) ;
		
		System.out.println("For the branch : " + ba.getName()+"\n"); 
		
        for (Map.Entry<Book,Integer> entry : la.entrySet()) 
            System.out.println("Book title : " + entry.getKey().getTitle() + ", Number of Copies : "+entry.getValue()); 
	
	}
    
	
	
	@Test
	public void returnBookTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("return Book");
	
		BookDao bookDao = new BookDao();
		BorrowerDao borrowerDao = new BorrowerDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		BookLoansDao bookLoansDao = new BookLoansDao();
		
        Book b = bookDao.get(2);
        Branch ba = libraryBranchDao.get(1);
        Borrower br = borrowerDao.get(5);
		
  		Loan loan = bookLoansDao.get(b, br, ba);

  		borrowerService = new BorrowerService();

		
  		borrowerService.returnBook(br, b, ba,
  				LocalDateTime.parse("24-08-2019 12:00",DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
		
		assertTrue(loan.getDueDate() != LocalDateTime.parse("24-08-2019 12:00",DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));

		
	}
	
	
 	@Test
  	public void getAllBranchesWithLoanTest() throws FileNotFoundException, IOException, SQLException {
  		
  		System.out.println("find  all Branches with loans");
  		
  		BorrowerDao borrowerDao = new BorrowerDao();
 		borrowerService = new BorrowerService();
 		
 		
 		Borrower br = borrowerDao.get(5);
  		
  		List<Branch> la = borrowerService.getAllBranchesWithLoan(br);
        
        for (Branch  b : la){
      	  
            System.out.println(b.getName());
          }
  	

  	}
 	
 	
 	
 	@Test
  	public void getAllBorrowedBooksTest() throws FileNotFoundException, IOException, SQLException {
  		
  		System.out.println("find All Borrowed Books");
  		
  		BorrowerDao borrowerDao = new BorrowerDao();
 		borrowerService = new BorrowerService();
 		
 		
 		Borrower br = borrowerDao.get(5);
  		
  		List<Loan> la = borrowerService.getAllBorrowedBooks(br);
        
        for (Loan  b : la){
      	  
            System.out.println(b.getBook().getTitle()+" "+b.getBorrower().getName()+ " " +b.getBranch().getName());
          }
  	

  	}
	
 	
 	
 	@Test
  	public void getAllBranchesTest() throws FileNotFoundException, IOException, SQLException {
  		
  		System.out.println("find  all Branches ");
  		
 
 		borrowerService = new BorrowerService();
 		
 	
  		List<Branch> la = borrowerService.getAllBranches();
        
        for (Branch  b : la){
      	  
            System.out.println(b.getName());
          }
  	

  	}
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
}
