package com.lms.service.test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lms.dao.BookDao;
import com.lms.dao.CopiesDao;
import com.lms.dao.LibraryBranchDao;
import com.lms.model.Book;
import com.lms.model.Branch;
import com.lms.service.LibrarianService;

public class LibrarianServiceTest {
	
	
	
	private LibrarianService librarianService;
	
    public LibrarianServiceTest() {
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
	public void updateBranchTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("Update a Branch");
	
  		librarianService = new LibrarianService();
  		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		
  		Branch branch = libraryBranchDao.get(10);
		
		Branch newUpdate = new Branch(10, "Branch12","Address 14");
		
		librarianService.updateBranch(newUpdate);
		
		assertTrue(branch.getName() != newUpdate.getName());
		
	}
    
    
    
	@Test
	public void setBranchCopiesTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Update number of copies");
		

		
		BookDao bookDao = new BookDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
  		librarianService = new LibrarianService();

		CopiesDao copieDao = new CopiesDao();
		
        Book b = bookDao.get(3);
        Branch ba = libraryBranchDao.get(3);
		
        librarianService.setBranchCopies(ba, b, 72);;
		
		
		assertTrue(copieDao.getCopies(ba, b) == 72);
	}
    
  
    
	@Test
	public void AllCopiesTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all copies for all branches \n");
		
  		librarianService = new LibrarianService();
		
		Map<Branch, Map<Book, Integer>> la = librarianService.getAllCopies() ;
		
		
        for (Entry<Branch, Map<Book, Integer>> entry : la.entrySet()) {
        	
        	
        	System.out.println("Library Name : " + entry.getKey().getName() + " \n "); 
        	
        	
        	   for (Entry<Book, Integer> ent :  entry.getValue().entrySet()) {
        		   
        		   System.out.println("Book Title : " + ent.getKey().getTitle() +", number of Copies : "+ent.getValue()+"\n");
        		   
        	   }
        
        }
            
	
	}
    
    
  
    
    
 	@Test
  	public void getAllBranchesTest() throws FileNotFoundException, IOException, SQLException {
  		
  		System.out.println("find  all Branches ");
  		
 
  		librarianService = new LibrarianService();
 		
 	
  		List<Branch> la = librarianService.getAllBranches();
        
        for (Branch  b : la){
      	  
            System.out.println(b.getName());
          }
 	}

}
