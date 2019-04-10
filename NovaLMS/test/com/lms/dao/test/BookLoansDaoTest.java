package com.lms.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lms.model.Book;
import com.lms.dao.BookDao;
import com.lms.dao.BookLoansDao;
import com.lms.dao.BorrowerDao;
import com.lms.dao.LibraryBranchDao;
import com.lms.model.Borrower;
import com.lms.model.Branch;
import com.lms.model.Loan;


public class BookLoansDaoTest {

	
	private Loan loan;
	private BookLoansDao bookLoansDao;
	
    public BookLoansDaoTest() {

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
	public void createTest() throws FileNotFoundException, IOException, SQLException {
    	
		BookDao bookDao = new BookDao();
		BorrowerDao borrowerDao = new BorrowerDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
	
	    bookLoansDao = new BookLoansDao();
    	
    	System.out.println("Create a Loan");
    	
    

        Book b = bookDao.get(2);
        Borrower br = borrowerDao.get(5);
        Branch ba = libraryBranchDao.get(1);
        LocalDateTime dout = LocalDateTime.parse("28-01-2019 12:00",DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        LocalDateTime dued = LocalDateTime.parse("10-02-2019 12:00",DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

        
		loan = bookLoansDao.create(b, br, ba, dout , dued);
		
		assertTrue(loan.getDateOut().equals(dout) && loan.getDueDate().equals(dued) );

	}
    
	@Test
	public void updateTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("Update a Loan");
	
		BookDao bookDao = new BookDao();
		BorrowerDao borrowerDao = new BorrowerDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		bookLoansDao = new BookLoansDao();
		
        Book b = bookDao.get(2);
        Branch ba = libraryBranchDao.get(1);
        Borrower br = borrowerDao.get(5);
		
  		loan = bookLoansDao.get(b, br, ba);

  	
//		
//		Loan newUpdate = new Loan(b,br,ba, LocalDateTime.parse("2019-03-14",DateTimeFormatter.ofPattern(("yyyy-MM-dd"))),
//				LocalDateTime.parse("2019-04-25",DateTimeFormatter.ofPattern(("yyyy-MM-dd"))));
		
		Loan newUpdate = new Loan(b,br,ba, LocalDateTime.parse("16-08-2019 12:00",DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
				LocalDateTime.parse("24-08-2019 12:00",DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
		
		bookLoansDao.update(newUpdate);
		
		assertTrue(loan.getDueDate() != newUpdate.getDueDate());

		
	}
    
    
    
	@Test
	public void deleteTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Delete a Loan");
		
		bookLoansDao = new BookLoansDao();
		
		BookDao bookDao = new BookDao();
		BorrowerDao borrowerDao = new BorrowerDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();

		
		
      Book b = bookDao.get(2);
      Borrower br = borrowerDao.get(5);
      Branch ba = libraryBranchDao.get(1);
		
		loan = bookLoansDao.get(b, br, ba);
		
		int cmp = bookLoansDao.getAll().size();
		Loan  l = bookLoansDao.get(b, br, ba);
		bookLoansDao.delete(l);
		
		int postcmp = bookLoansDao.getAll().size();
		
		assertTrue(cmp > postcmp);
	}

    
	
    
	@Test
	public void findAllTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all Loans");
		
		bookLoansDao = new BookLoansDao();
		
		List<Loan> la = bookLoansDao.getAll();
      
      for (Loan l : la){
    	  
          System.out.println(l.getBorrower().getName()+" borrowed the Book : "+l.getBook().getTitle()+" from the branch : "+l.getBranch().getName());
        }
	
	}
    
    
	@Test
	public void getTest() throws FileNotFoundException, IOException, SQLException {
		
		bookLoansDao = new BookLoansDao();
		
		BookDao bookDao = new BookDao();
		BorrowerDao borrowerDao = new BorrowerDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		
		System.out.println("Find a Loan");
		
      Book b = bookDao.get(4);
      Borrower br = borrowerDao.get(3);
      Branch ba = libraryBranchDao.get(1);
		

      Loan lo = bookLoansDao.create(b,br,ba, LocalDateTime.parse("2019-02-10") ,LocalDateTime.parse("2019-02-17"));
		
		
		assertEquals(lo.getDueDate(), bookLoansDao.get(b, br, ba).getDueDate());
	}
    
}
