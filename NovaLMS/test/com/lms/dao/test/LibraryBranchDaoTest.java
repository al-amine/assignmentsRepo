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

import com.lms.dao.LibraryBranchDao;
import com.lms.model.Branch;

public class LibraryBranchDaoTest {

	

	private Branch branch;
	private LibraryBranchDao libraryBranchDao;
	
    public LibraryBranchDaoTest() {
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
    	
    	System.out.println("Create a Branch");
    	String str1 = "Branch1";
    	String str2 = "AddressTest1";
    	
	    branch = new Branch();
        libraryBranchDao = new LibraryBranchDao();
        
		branch = libraryBranchDao.create(str1, str2);
		assertTrue(branch.getName().equals(str1) && branch.getAddress().equals(str2));

	}
    
    
    
    
    
	@Test
	public void updateTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("Update a Branch");
	
  		libraryBranchDao = new LibraryBranchDao();
		
  		branch = libraryBranchDao.get(10);
		
		Branch newUpdate = new Branch(10, "Branch12","Address 14");
		
		libraryBranchDao.update(newUpdate);
		
		assertTrue(branch.getName() != newUpdate.getName());
		
	}
    
  
	@Test
	public void deleteTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Delete a Branch");
		
		libraryBranchDao = new LibraryBranchDao();
		
		int cmp = libraryBranchDao.getAll().size();
		Branch  b = libraryBranchDao.get(10);
		libraryBranchDao.delete(b);
		
		int postcmp = libraryBranchDao.getAll().size();
		assertTrue(cmp > postcmp);
	}
    
    
	@Test
	public void findAllTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all Branches");
		
		libraryBranchDao = new LibraryBranchDao();
		
		List<Branch> la = libraryBranchDao.getAll();
      
      for (Branch  b : la){
    	  
          System.out.println(b.getName());
        }
	

	}
    
    
	@Test
	public void getTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Find a Branch");
		

		libraryBranchDao = new LibraryBranchDao();
	
		Branch p = libraryBranchDao.create("Branch 1457","ADR45");
		
		assertEquals(p.getName(), libraryBranchDao.get(p.getId()).getName());
	}
	
	
	
	
}
