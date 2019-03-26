import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.BookService;

public class BookServiceTest {
	
	
    public BookServiceTest() {
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
  public void testCreate() throws IOException {
		
  System.out.println("CreateBook");
  int id = 60;
  String title = "How about that as a Speach";
  int authorid = 77;
  int publisherid = 89;
  String authorname = "Al Amine & Siham El ainoug";
  String publishername = "Renard de Chatian";

  
  BookService instance = new BookService();
  
  int expResult = 2;
  int result = instance.createBook(id, title,authorid, publisherid, authorname,publishername);

  assertEquals(expResult, result);
  
  // TODO review the generated test code and remove the default call to fail.
}  
    
    @Test
    public void testUpdate() throws IOException {
  		
    System.out.println("UpdateBook");
    int id = 60;
    String title = "The Erased";
    int authorid = 48;
    int publisherid = 93;
    String authorname = "Jack Burns";
    String publishername = "Robert De Saint Michelle";

    
    BookService instance = new BookService();
    
    int expResult = 2;
    int result = instance.updateBook(id, title,authorid, publisherid, authorname,publishername);

    assertEquals(expResult, result);
    
    // TODO review the generated test code and remove the default call to fail.
  }  

    
    
    
    
  @Test
  public void testDelete() throws IOException {
		
  System.out.println("DeleteBook");
  int id = 60;
  BookService instance = new BookService();

  int expResult = 1;
  int result = instance.deleteBook(id);

  assertEquals(expResult, result);

  // TODO review the generated test code and remove the default call to fail.
}  
  
  
  
@Test
public void testShow() throws IOException {
		
System.out.println("ShowBooks");

BookService instance = new BookService();

int expResult = 1;
int result = instance.showBooks();

assertEquals(expResult, result);

// TODO review the generated test code and remove the default call to fail.
} 


}
