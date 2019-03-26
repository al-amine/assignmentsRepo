import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bean.Book;
import dao.BookDao;





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
  	public void createTest() throws FileNotFoundException, IOException {
      	
      	System.out.println("Create a Book");
      	
  		book = new Book(2, "The Shadow of The Wind",17,23);
  		bookDao = new BookDao();
  		int cmp;
  		cmp = bookDao.findAll().size();
  		bookDao.create(book);
  		int postcmp = bookDao.findAll().size();
  		assertTrue( postcmp > cmp);
  	}
    
    
    
    @Test
  	public void deleteTest() throws FileNotFoundException, IOException {
  		
  		System.out.println("Delete a Book");
  		
  		book = new Book(13, "Ibn Khaldoun",22,35);
  		bookDao = new BookDao();
  		
  		bookDao.create(book);
  		int cmp = bookDao.findAll().size();
  		bookDao.delete(book.getId());
  		int postcmp = bookDao.findAll().size();
  		assertTrue(cmp > postcmp);
  	}
    
    
  	@Test
  	public void findAuthorTest() throws FileNotFoundException, IOException {
  		
  		System.out.println("Find a Book");
  		
  		book = new Book(13, " Al Muqadima 2 ",22,35);
  		bookDao = new BookDao();
  		
  		bookDao.create(book);
  		
  		assertEquals(book.getId(), bookDao.find(book.getId()).getId());
  		
  	}
    
    
    
  	@Test
  	public void updateAuthorTest() throws FileNotFoundException, IOException {
  	
  		
  		System.out.println("Update a Book");
  		
  		bookDao = new BookDao();
  		
  		Book book = bookDao.find(13);
  		
  		Book newUpdate = new Book(13, " Al Muqadima ",22,35);
  		
  		bookDao.update(newUpdate);
  		assertTrue(book.getTitle() != newUpdate.getTitle());
  		
  	}
}
