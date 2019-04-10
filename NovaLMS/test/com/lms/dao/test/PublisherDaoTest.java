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

import com.lms.dao.PublisherDao;
import com.lms.model.Publisher;





public class PublisherDaoTest {
	
	
	
	private Publisher publisher;
	private PublisherDao publisherDao;
	
    public PublisherDaoTest() {
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
    	
    	System.out.println("Create a Publisher");
    	String str1 = "Publisher2";
    	String str2 = "AddressTest2";
    	String str3 = "PhoneTest2";
    	
	    publisher = new Publisher();
        publisherDao = new PublisherDao();
        
		publisher = publisherDao.create(str1, str2, str3);
		assertTrue(publisher.getName().equals(str1) && publisher.getAddress().equals(str2) && publisher.getPhone().equals(str3));

	}
    
   
    
    
    
	@Test
	public void updateTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("Update a Publisher");
	
  		publisherDao = new PublisherDao();
		
  		publisher = publisherDao.get(10);
		
		Publisher newUpdate = new Publisher(10, "dash dash dash","Address 14","Phone14");
		
		publisherDao.update(newUpdate);
		
		assertTrue(publisher.getName() != newUpdate.getName());
		
	}
    
	@Test
	public void deleteTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Delete a Publisher");
		
		publisherDao = new PublisherDao();
		
		int cmp = publisherDao.getAll().size();
		Publisher  p = publisherDao.get(10);
		publisherDao.delete(p);
		
		int postcmp = publisherDao.getAll().size();
		assertTrue(cmp > postcmp);
	}
    
    
    
	@Test
	public void findAllTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all Publishers");
		
		publisherDao = new PublisherDao();
		
		List<Publisher> la = publisherDao.getAll();
      
      for (Publisher p : la){
    	  
          System.out.println(p.getName());
        }
	

	}
    
	
	
    
	@Test
	public void getTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Find a Publisher");
		

		publisherDao = new PublisherDao();
	
		Publisher p = publisherDao.create("Ibn Khaldoun","ADR45","PHN45");
		
		assertEquals(p.getName(), publisherDao.get(p.getId()).getName());
	}
    
    
   
    
    

}
