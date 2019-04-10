package com.lms.service.test;

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
import com.lms.service.AdministratorService;

public class AdministratorServiceTest {

	private AdministratorService administratorService;
	
    public AdministratorServiceTest() {
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
	public void createBookTest() throws SQLException{
    	
    	System.out.println("Create a Book");
    	
    	String str = "Admin Book Test";
		
		
		AuthorDao authorDao = new AuthorDao();
		PublisherDao publisherDao = new PublisherDao();
		administratorService = new AdministratorService();
		
		Author author = authorDao.get(1);
		Publisher publisher = publisherDao.get(1);
		
		Book b = administratorService.createBook(str, author, publisher);
		
		assertTrue(b.getTitle().equals(str));

	}
    
    
    
	@Test
	public void updateBookTest() throws SQLException, IOException {
		
  		System.out.println("Update a Book");
  		
		AuthorDao authorDao = new AuthorDao();
		PublisherDao publisherDao = new PublisherDao();
		administratorService = new AdministratorService();
  		BookDao bookDao = new BookDao();
		
  		Book book = bookDao.get(21);
        Author a = authorDao.get(1);
        Publisher p = publisherDao.get(1);
		
        
        Book update = new Book(21, "Admin BookUpdate Test", a, p);
		
        administratorService.updateBook(update);
		
		assertTrue(book.getTitle() != update.getTitle());
		
	}
	
	
	@Test
	public void deleteBookTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Delete a Book");
		
		BookDao bookDao = new BookDao();
		administratorService = new AdministratorService();
		
		int cmp = bookDao.getAll().size();
		Book  b = bookDao.get(21);
		administratorService.deleteBook(b);
		
		int postcmp = bookDao.getAll().size();
		assertTrue(cmp > postcmp);
	}
	
	
	@Test
	public void getAllBooksTest() throws FileNotFoundException, IOException, SQLException {
		
		administratorService = new AdministratorService();
		
		System.out.println("find  all Books");
		
		
		List<Book> la = administratorService.getAllBooks();
      
      for (Book b : la){
    	  
          System.out.println(b.getTitle());
        }
	

	}

    @Test
	public void createAuthorTest() throws FileNotFoundException, IOException, SQLException {
    	
    	System.out.println("Create an Author");
    	
    	String str = "Author Admin Test";
		 Author author = new Author();
		 
		administratorService = new AdministratorService();
		 
		
		author = administratorService.createAuthor(str);
		assertTrue(author.getName().equals(str));

	}
    
	@Test
	public void updateAuthorTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("Update an Author");
	
		AuthorDao authorDao = new AuthorDao();
		administratorService = new AdministratorService();
		
		Author author = authorDao.get(27);
		
		Author newUpdate = new Author(27, "AuthorUp Admin Test");
		
		administratorService.updateAuthor(newUpdate);
		
		assertTrue(author.getName() != newUpdate.getName());
		
	}
	
	
	
	
	
	@Test
	public void deleteAuthorTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Delete an Author");
		
		AuthorDao authorDao = new AuthorDao();
		administratorService = new AdministratorService();
		
		int cmp = authorDao.getAll().size();
		Author d = authorDao.get(27);
		administratorService.deleteAuthor(d);
		
		int postcmp = authorDao.getAll().size();
		assertTrue(cmp > postcmp);
	}	
	
	
	
	@Test
	public void getAllAuthorsTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all Authors");
		
		administratorService = new AdministratorService();
		
		List<Author> la = administratorService.getAllAuthors();
      
      for (Author a : la){
    	  
          System.out.println(a.getName());
        }
	

	}
	
	
    @Test
	public void createPublisherTest() throws FileNotFoundException, IOException, SQLException {
    	
    	System.out.println("Create a Publisher");
    	String str1 = "Publisher2";
    	String str2 = "AddressTest2";
    	String str3 = "PhoneTest2";
    	
	    Publisher publisher = new Publisher();

		
		administratorService = new AdministratorService();
        
		publisher = administratorService.createPublisher(str1, str2, str3);
		assertTrue(publisher.getName().equals(str1) && publisher.getAddress().equals(str2) && publisher.getPhone().equals(str3));

	}
	
	@Test
	public void updatePublisherTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("Update a Publisher");
	
  		PublisherDao publisherDao = new PublisherDao();
  		
  		administratorService = new AdministratorService();
		
  		Publisher publisher = publisherDao.get(12);
		
		Publisher newUpdate = new Publisher(12, "dash dash dash","Address 14","Phone14");
		
		administratorService.updatePublisher(newUpdate);
		
		assertTrue(publisher.getName() != newUpdate.getName());
		
	}

    
    
   
	@Test
	public void deletePublisherTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Delete a Publisher");
		
		administratorService = new AdministratorService();
		
		PublisherDao publisherDao = new PublisherDao();
		
		int cmp = publisherDao.getAll().size();
		Publisher  p = publisherDao.get(12);
		administratorService.deletePublisher(p);
		
		int postcmp = publisherDao.getAll().size();
		assertTrue(cmp > postcmp);
	}   
    
    
    
	@Test
	public void getAllPublisherTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all Publishers");
		
		administratorService = new AdministratorService();
		
		List<Publisher> la = administratorService.getAllPublishers();
      
      for (Publisher p : la){
    	  
          System.out.println(p.getName());
        }
	

	}
    
    
	
    @Test
  	public void createBranchTest() throws FileNotFoundException, IOException, SQLException {
      	
      	System.out.println("Create a Branch");
      	String str1 = "Branch1";
      	String str2 = "AddressTest1";
      	
  	    Branch branch = new Branch();
  	    administratorService = new AdministratorService();
          
  		branch = administratorService.createBranch(str1, str2);
  		assertTrue(branch.getName().equals(str1) && branch.getAddress().equals(str2));

  	}
      
      
      
      
      
  	@Test
  	public void updateBranchTest() throws FileNotFoundException, IOException, SQLException {
  		
    		System.out.println("Update a Branch");
  	
    		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
    		
    	    administratorService = new AdministratorService();
  		
    		Branch branch = libraryBranchDao.get(10);
  		
  		Branch newUpdate = new Branch(10, "Branch12","Address 14");
  		
  		administratorService.updateBranch(newUpdate);
  		
  		assertTrue(branch.getName() != newUpdate.getName());
  		
  	}
      
    
  	@Test
  	public void deleteBranchTest() throws FileNotFoundException, IOException, SQLException {
  		
  		System.out.println("Delete a Branch");
  		
  		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
  		administratorService = new AdministratorService();
  		
  		
  		int cmp = libraryBranchDao.getAll().size();
  		Branch  b = libraryBranchDao.get(10);
  		administratorService.deleteBranch(b);
  		
  		int postcmp = libraryBranchDao.getAll().size();
  		assertTrue(cmp > postcmp);
  	}
      
      
  	@Test
  	public void getAllBranchesTest() throws FileNotFoundException, IOException, SQLException {
  		
  		System.out.println("find  all Branches");
  		
  		administratorService = new AdministratorService();
  		
  		List<Branch> la = administratorService.getAllBranches();
        
        for (Branch  b : la){
      	  
            System.out.println(b.getName());
          }
  	

  	}
    
    
    
  	@Test
	public void createBorrowerTest() throws FileNotFoundException, IOException, SQLException {
    	
    	System.out.println("Create a Borrower");
    	
    	String str1 = "Borrower1";
    	String str2 = "AddressTest21";
    	String str3 = "PhoneTest1";
    	
	     Borrower borrower = new Borrower();
	     
	 	administratorService = new AdministratorService();
        
         borrower = administratorService.createBorrower(str1, str2, str3);
		assertTrue(borrower.getName().equals(str1) && borrower.getAddress().equals(str2) && borrower.getPhone().equals(str3));

	} 
    
	@Test
	public void updateBorrowerTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("Update a Borrower");
	
  		BorrowerDao borrowerDao = new BorrowerDao();
  		
	 	administratorService = new AdministratorService();
		
  		Borrower borrower = borrowerDao.get(12);
		
		Borrower newUpdate = new Borrower(12, "dash dash test01","Address 14","Phone14");
		
		administratorService.updateBorrower(newUpdate);
		
		assertTrue(borrower.getName() != newUpdate.getName());
		
	}
    
    
	@Test
	public void deleteBorrowerTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Delete a Borrower");
		
		BorrowerDao borrowerDao = new BorrowerDao();
	 	administratorService = new AdministratorService();

		
		int cmp = borrowerDao.getAll().size();
		Borrower  b = borrowerDao.get(12);
		administratorService.deleteBorrower(b);
		
		int postcmp = borrowerDao.getAll().size();
		assertTrue(cmp > postcmp);
	}
    
    
    
    
    
	@Test
	public void getAllBorrowersTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all Borrowers");
		
	 	administratorService = new AdministratorService();
		
		List<Borrower> la = administratorService.getAllBorrowers();
      
      for (Borrower b : la){
    	  
          System.out.println(b.getName());
        }
	

	}
    
    
	@Test
	public void getAllLoansTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all Loans");
		
	 	administratorService = new AdministratorService();
		
		List<Loan> la = administratorService.getAllLoans();
      
      for (Loan l : la){
    	  
          System.out.println(l.getBorrower().getName()+" borrowed the Book : "+l.getBook().getTitle()+" from the branch : "+l.getBranch().getName());
        }
	
	}
    
    
    
    
    
	@Test
	public void overrideDueDateForLoanTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("override the Due Date For Loan");
	
		BookDao bookDao = new BookDao();
		BorrowerDao borrowerDao = new BorrowerDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		BookLoansDao bookLoansDao = new BookLoansDao();
		
        Book b = bookDao.get(2);
        Branch ba = libraryBranchDao.get(1);
        Borrower br = borrowerDao.get(5);
		
  		Loan loan = bookLoansDao.get(b, br, ba);

	 	administratorService = new AdministratorService();

		
		administratorService.overrideDueDateForLoan(b, br, ba,
				LocalDateTime.parse("24-08-2019 12:00",DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
		
		assertTrue(loan.getDueDate() != LocalDateTime.parse("24-08-2019 12:00",DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));

		
	}
    
    
    
    
    
    
    
    
}
