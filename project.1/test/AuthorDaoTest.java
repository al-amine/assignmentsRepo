import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bean.Author;
import dao.AuthorDao;

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

//	@Test
//  public void testCreate() throws IOException {
//  System.out.println("Create an Author");
//  int id = 17;
//  String name ="Carlos Zafone";
//  Author author = new Author(id, name);
//  String expResult ="17-Carlos Zafone";
//  authorDao.create(author);
//  
//  result.toString();
//  assertEquals(expResult, result);
//  
//  // TODO review the generated test code and remove the default call to fail.
//}
    
    @Test
	public void createTest() throws FileNotFoundException, IOException {
    	
    	System.out.println("Create an Author");
    	
		author = new Author(17, "Carlos Zafone");
		authorDao = new AuthorDao();
		int cmp;
		cmp = authorDao.findAll().size();
		authorDao.create(author);
		int postcmp = authorDao.findAll().size();
		assertTrue( postcmp > cmp);
	}
	
	@Test
	public void deleteTest() throws FileNotFoundException, IOException {
		
		System.out.println("Delete an Author");
		
		author = new Author(22, "Ibn Khaldoun");
		authorDao = new AuthorDao();
		
		authorDao.create(author);
		int cmp = authorDao.findAll().size();
		authorDao.delete(author.getId());
		int postcmp = authorDao.findAll().size();
		assertTrue(cmp > postcmp);
	}
    
	
	@Test
	public void findAuthorTest() throws FileNotFoundException, IOException {
		
		System.out.println("Find an Author");
		
		author = new Author(22, "Ibn Khaldoun");
		authorDao = new AuthorDao();
		
		authorDao.create(author);
		assertEquals(author.getId(), authorDao.find(author.getId()).getId());
	}


	@Test
	public void updateAuthorTest() throws FileNotFoundException, IOException {
		
  		System.out.println("Find an Author");
	
		authorDao = new AuthorDao();
		
		Author author = authorDao.find(46);
		
		Author newUpdate = new Author(46, "Daniel Simpree");
		
		authorDao.update(newUpdate);
		assertTrue(author.getName() != newUpdate.getName());
		
	}
	
}
