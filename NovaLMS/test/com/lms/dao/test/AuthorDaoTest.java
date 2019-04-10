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

import com.lms.model.Author;
import com.lms.dao.AuthorDao;

public class AuthorDaoTest {
	
	private Author author;
	private AuthorDao authorDao;
	
    public AuthorDaoTest() {
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
    	
    	System.out.println("Create an Author");
    	String str = "Najoua Bahba";
		author = new Author();
		authorDao = new AuthorDao();
		author = authorDao.create(str);
		assertTrue(author.getName().equals(str));

	}
    
    
	@Test
	public void updateAuthorTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("Update an Author");
	
		authorDao = new AuthorDao();
		
		Author author = authorDao.get(25);
		
		Author newUpdate = new Author(25, "Najoua The Lovely Bahba");
		
		authorDao.update(newUpdate);
		
		assertTrue(author.getName() != newUpdate.getName());
		
	}
    
    
    
    
	@Test
	public void deleteTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Delete an Author");
		
		authorDao = new AuthorDao();
		
		int cmp = authorDao.getAll().size();
		Author d = authorDao.get(24);
		authorDao.delete(d);
		
		int postcmp = authorDao.getAll().size();
		assertTrue(cmp > postcmp);
	}
    
    
    
	@Test
	public void findAllTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all Authors");
		
		authorDao = new AuthorDao();
		
		List<Author> la = authorDao.getAll();
      
      for (Author a : la){
    	  
          System.out.println(a.getName());
        }
	

	}
	
	
	
	@Test
	public void getTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Find an Author");
		

		authorDao = new AuthorDao();
	
		Author a = authorDao.create("Ibn Khaldoun");
		
		assertEquals(a.getName(), authorDao.get(a.getId()).getName());
	}
	
	
	
}
