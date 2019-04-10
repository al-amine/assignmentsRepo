package com.lms.dao.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
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



public class CopiesDaoTest {
	
	
	private CopiesDao copiesDao;
	
    public CopiesDaoTest() {

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
	public void getTest() throws FileNotFoundException, IOException, SQLException {
    	
		BookDao bookDao = new BookDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
	
	    copiesDao = new CopiesDao();
    	
    	System.out.println("Get the number od Copies");
    	

        Book b = bookDao.get(1);
        Branch ba = libraryBranchDao.get(1);

       int co = copiesDao.getCopies(ba, b);
       
        
	   assertEquals(20, co);
	}
    
    @Test
 	public void createTest() throws FileNotFoundException, IOException, SQLException {
     	
 		BookDao bookDao = new BookDao();
 		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
 	
 		copiesDao = new CopiesDao();
     	
     	System.out.println("Create a Copie Row");
     	
     

         Book b = bookDao.get(3);
         Branch ba = libraryBranchDao.get(3);
         int noU = 36;

         
         copiesDao.create(b,  ba, noU);
         
         assertEquals( 36, copiesDao.getCopies(ba, b));

 	}
    
    
    
	@Test
	public void deleteTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Delete a Copie Row");
		

		
		BookDao bookDao = new BookDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		copiesDao = new CopiesDao();

		
		
        Book b = bookDao.get(3);
        Branch ba = libraryBranchDao.get(3);
		
        copiesDao.delete(b,ba);
		
		
		assertTrue(copiesDao.getCopies(ba, b) == 0);
	}
	
	
	
	@Test
	public void setTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Update a Copie Row");
		

		
		BookDao bookDao = new BookDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		copiesDao = new CopiesDao();

		
		
        Book b = bookDao.get(3);
        Branch ba = libraryBranchDao.get(3);
		
        copiesDao.setCopies(ba, b, 72);
		
		
		assertTrue(copiesDao.getCopies(ba, b) == 72);
	}
	
	
	
	
	
	@Test
	public void AllBookCopiesTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all copies for a branch \n");
		
		BookDao bookDao = new BookDao();

		copiesDao = new CopiesDao();
		
		Book b = bookDao.get(1);

		
		Map<Branch, Integer> la = copiesDao.getAllBookCopies(b) ;
		
		System.out.println("For the Book : " + b.getTitle()+"\n"); 
		
        for (Map.Entry<Branch,Integer> entry : la.entrySet()) 
            System.out.println("Library Branch : " + entry.getKey().getName() + ",has "+entry.getValue()+"  of Copies"); 
	
	}
	
	
	@Test
	public void AllBranchCopiesTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all copies for a branch \n");
		
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();

		copiesDao = new CopiesDao();
		
		Branch ba = libraryBranchDao.get(3);

		
		Map<Book, Integer> la = copiesDao.getAllBranchCopies(ba) ;
		
		System.out.println("For the branch : " + ba.getName()+"\n"); 
		
        for (Map.Entry<Book,Integer> entry : la.entrySet()) 
            System.out.println("Book title : " + entry.getKey().getTitle() + ", Number of Copies : "+entry.getValue()); 
	
	}
	
    
	@Test
	public void AllCopiesTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all copies for all branches \n");
		
		copiesDao = new CopiesDao();
		
		Map<Branch, Map<Book, Integer>> la = copiesDao.getAllCopies() ;
		
		
        for (Entry<Branch, Map<Book, Integer>> entry : la.entrySet()) {
        	
        	
        	System.out.println("Library Name : " + entry.getKey().getName() + " \n "); 
        	
        	
        	   for (Entry<Book, Integer> ent :  entry.getValue().entrySet()) {
        		   
        		   System.out.println("Book Title : " + ent.getKey().getTitle() +", number of Copies : "+ent.getValue()+"\n");
        		   
        	   }
        
        }
            
	
	}
	
	
	

}
