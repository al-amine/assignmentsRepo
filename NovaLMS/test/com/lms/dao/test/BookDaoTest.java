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

import com.lms.dao.AuthorDao;
import com.lms.dao.BookDao;
import com.lms.model.Author;
import com.lms.model.Book;

import com.lms.dao.PublisherDao;
import com.lms.model.Publisher;

public class BookDaoTest {
	
	private Book book;
	private BookDao bookDao;
	
    public BookDaoTest() {

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
    	
		AuthorDao authorDao = new AuthorDao();
		PublisherDao publisherDao = new PublisherDao();
	    bookDao = new BookDao();
    	
    	System.out.println("Create a Book");
    	
    	String str1 = "Title1";

        Author a = authorDao.get(1);
        Publisher p = publisherDao.get(1);

        
		book = bookDao.create(str1, a, p);
		assertTrue(book.getTitle().equals(str1));

	}

    
	@Test
	public void updateTest() throws FileNotFoundException, IOException, SQLException {
		
  		System.out.println("Update a Book");
		AuthorDao authorDao = new AuthorDao();
		PublisherDao publisherDao = new PublisherDao();
	
  		bookDao = new BookDao();
		
  		book = bookDao.get(18);
        Author a = authorDao.get(1);
        Publisher p = publisherDao.get(1);
		
//		book.setAuthor(a);
//		book.setPublisher(p);
//		book.setTitle("hahahaha");
        
        Book update = new Book(18, "hahahaha", a, p);
		
		bookDao.update(update);
		
		assertTrue(book.getTitle() != update.getTitle());
		
	}
	
    
	@Test
	public void deleteTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("Delete a Book");
		
		bookDao = new BookDao();
		
		int cmp = bookDao.getAll().size();
		Book  b = bookDao.get(18);
		bookDao.delete(b);
		
		int postcmp = bookDao.getAll().size();
		assertTrue(cmp > postcmp);
	}

    
    
    
	@Test
	public void findAllTest() throws FileNotFoundException, IOException, SQLException {
		
		System.out.println("find  all Books");
		
		bookDao = new BookDao();
		
		List<Book> la = bookDao.getAll();
      
      for (Book b : la){
    	  
          System.out.println(b.getTitle());
        }
	

	}


	@Test
	public void getTest() throws FileNotFoundException, IOException, SQLException {
		
		AuthorDao authorDao = new AuthorDao();
		PublisherDao publisherDao = new PublisherDao();
		
		System.out.println("Find a Book");
		
      Author a = authorDao.get(1);
      Publisher p = publisherDao.get(1);
		

		bookDao = new BookDao();
	
		Book b = bookDao.create("50 down", a, p);
		
		
		assertEquals(b.getTitle(), bookDao.get(b.getId()).getTitle());
	}
	
	
	
}
