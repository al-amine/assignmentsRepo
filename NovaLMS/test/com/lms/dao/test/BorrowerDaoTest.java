package com.lms.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lms.dao.BorrowerDao;
import com.lms.model.Borrower;



public class BorrowerDaoTest {
	
	
	
	
	private Borrower borrower;
	private BorrowerDao borrowerDao;
	
    public BorrowerDaoTest() {
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
    	
    	System.out.println("Create a Borrower");
    	String str1 = "Borrower1";
    	String str2 = "AddressTest21";
    	String str3 = "PhoneTest1";
    	
	     borrower = new Borrower();
         borrowerDao = new BorrowerDao();
        
         borrower = borrowerDao.create(str1, str2, str3);
		assertTrue(borrower.getName().equals(str1) && borrower.getAddress().equals(str2) && borrower.getPhone().equals(str3));

	} 
    
	@Test
	public void updateTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("Update a Borrower");
	
  		borrowerDao = new BorrowerDao();
		
  		borrower = borrowerDao.get(12);
		
		Borrower newUpdate = new Borrower(12, "dash dash test01","Address 14","Phone14");
		
		borrowerDao.update(newUpdate);
		
		assertTrue(borrower.getName() != newUpdate.getName());
		
	}
    
    
	@Test
	public void deleteTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Delete a Borrower");
		
		borrowerDao = new BorrowerDao();
		
		int cmp = borrowerDao.getAll().size();
		Borrower  b = borrowerDao.get(12);
		borrowerDao.delete(b);
		
		int postcmp = borrowerDao.getAll().size();
		assertTrue(cmp > postcmp);
	}
    
    
    
    
    
	@Test
	public void findAllTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all Borrowers");
		
	    borrowerDao = new BorrowerDao();
		
		List<Borrower> la = borrowerDao.getAll();
      
      for (Borrower b : la){
    	  
          System.out.println(b.getName());
        }
	

	}
    
   
    
    
	@Test
	public void getTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Find a Barrower");
		

		borrowerDao = new BorrowerDao();
	
		Borrower b = borrowerDao.create("Ibn Khaldoun","ADR45","PHN45");
		
		assertEquals(b.getName(), borrowerDao.get(b.getCardNo()).getName());
	} 
    
    
    
    
}
