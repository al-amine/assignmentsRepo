import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

//import bean.Author;
import service.AuthorService;

public class AuthorServiceTest {
	
	
	
//	private Author author;
//	private AuthorService authorService;
	
    public AuthorServiceTest() {
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
		
  System.out.println("CreateAuthor");
  int id = 46;
  String name ="Daniel Sempree";
  
  AuthorService instance = new AuthorService();
  
  int expResult = 1;
  int result = instance.createAuthor(id, name);

  assertEquals(expResult, result);
  
  // TODO review the generated test code and remove the default call to fail.
}
	
	
@Test
public void testupdate() throws FileNotFoundException, IOException {
	
   System.out.println("updateAuthor");
   int id = 78;
   String newname = "Albert Foustte";
   AuthorService instance = new AuthorService();
   int expResult = 1;
   int result = instance.updateAuthor(id,newname);
   assertEquals(expResult, result);
   // TODO review the generated test code and remove the default call to fail.
   
}
 
@Test
public void testDelete() throws IOException {
		
System.out.println("DeleteAuthor");
int id = 17;
AuthorService instance = new AuthorService();

int expResult = 1;
int result = instance.deleteAuthor(id);

assertEquals(expResult, result);

//TODO review the generated test code and remove the default call to fail.
}


    @Test
    public void testShowAuthorsAndBooks() throws IOException {
    		
    System.out.println("ShowAuthorsAndBooks");
    AuthorService instance = new AuthorService();

    int expResult = 1;
    int result = instance.showAuthorsAndBooks();

    assertEquals(expResult, result);

    //TODO review the generated test code and remove the default call to fail.
    }
}
